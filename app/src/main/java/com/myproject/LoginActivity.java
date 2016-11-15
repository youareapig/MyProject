package com.myproject;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView btn_login, user_regist;
    private EditText userphone, userpassword;
    private String getUserphone, getUserpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = (TextView) findViewById(R.id.login);
        user_regist = (TextView) findViewById(R.id.regist);
        userphone = (EditText) findViewById(R.id.userphone);
        userpassword = (EditText) findViewById(R.id.userpassword);
        btn_login.setOnClickListener(this);
        user_regist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        getUserphone = userphone.getText().toString();
        getUserpassword = userpassword.getText().toString();
        switch (v.getId()) {
            case R.id.login:
                if (TextUtils.isEmpty(getUserphone) || TextUtils.isEmpty(getUserpassword)) {
                    Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    visit();
                }

                break;
            case R.id.regist:
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
        }
    }

    public void visit() {
        RequestParams params = new RequestParams("http://192.168.0.108/api.php/Member/login");
        params.addBodyParameter("mobile", getUserphone);
        params.addBodyParameter("password", getUserpassword);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("tag", "接受数据:" + result);
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(result, UserBean.class);
                Log.d("tag", "返回码:" + userBean.getCode());
                if (userBean.getCode() == 3000) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                } else if (userBean.getCode() == -3000) {
                    Toast.makeText(LoginActivity.this, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("tag", "请求错误");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}
