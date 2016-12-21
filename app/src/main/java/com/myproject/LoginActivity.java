package com.myproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import bean.UserBean;
import utils.Global;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView btn_login, user_regist,forgetpassword;
    private EditText userphone, userpassword;
    private String getUserphone, getUserpassword;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String URL;
    private boolean isLogin;
    private String tel;
    private ProgressDialog progressDialog = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Global global=new Global();
        URL=global.getUrl()+"api.php/Member/login";
        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Intent intent = getIntent();
        Intent intent1 =this.getIntent();
        if (intent1!=null){
            isLogin = intent1.getBooleanExtra("login",false);
        }
        if (intent!=null){
            tel=intent.getStringExtra("tel");
        }
        btn_login = (TextView) findViewById(R.id.login);
        user_regist = (TextView) findViewById(R.id.regist);
        userphone = (EditText) findViewById(R.id.userphone);
        userpassword = (EditText) findViewById(R.id.userpassword);
        forgetpassword= (TextView) findViewById(R.id.forgetpassword);
        forgetpassword.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        user_regist.setOnClickListener(this);

        userphone.setText(tel);
    }

    @Override
    public void onClick(View v) {
        getUserphone = userphone.getText().toString();
        getUserpassword = userpassword.getText().toString();
        switch (v.getId()) {
            case R.id.login:
                if (TextUtils.isEmpty(getUserphone) || TextUtils.isEmpty(getUserpassword)) {
                    Toast.makeText(LoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    visit();
                }

                break;
            case R.id.regist:
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.forgetpassword:
                Intent intent1=new Intent(LoginActivity.this,RetrievePasswordActivity.class);
                startActivity(intent1);
                break;
        }
    }

    public void visit() {
        progressDialog = ProgressDialog.show(this, "请稍后", "登录中....", true);
        RequestParams params = new RequestParams(URL);
        params.addBodyParameter("mobile", getUserphone);
        params.addBodyParameter("password", getUserpassword);
        Log.i("test","参数"+getUserphone+"      "+getUserpassword);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("test","返回"+result);
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(result, UserBean.class);
                if (userBean.getCode() == 3000) {
                    //TODO 是否登录状态
                    editor.putString("state","1").apply();
                    //TODO 保存用户ID
                    editor.putString("userID",userBean.getData().getUserid()).apply();
                    if (isLogin){
                        setResult(RESULT_OK);
                        finish();
                    }else {
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                } else if (userBean.getCode() == -3000) {
                    Toast.makeText(LoginActivity.this, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(LoginActivity.this, "服务器故障，请稍后重试！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                progressDialog.cancel();
            }
        });

    }
}
