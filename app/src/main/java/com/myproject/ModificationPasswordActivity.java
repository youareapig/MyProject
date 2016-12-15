package com.myproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import utils.Global;

public class ModificationPasswordActivity extends AppCompatActivity {
    @BindView(R.id.former_password)
    EditText formerPassword;
    @BindView(R.id.newpassword)
    EditText newpassword;
    @BindView(R.id.save_password)
    TextView savePassword;
    @BindView(R.id.formerpwd_back)
    RelativeLayout formerpwdBack;
    private Unbinder unbinder;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String userID, url;
    private Global global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification_password);
        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userID = sharedPreferences.getString("userID", null);
        global = new Global();
        url = global.getUrl() + "api.php/Member/memberSavepwd";
        unbinder = ButterKnife.bind(this);
        savePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpwd = formerPassword.getText().toString().trim();
                String newpwd = newpassword.getText().toString().trim();
                if (TextUtils.isEmpty(oldpwd) || TextUtils.isEmpty(newpwd)) {
                    Toast.makeText(ModificationPasswordActivity.this, "请输入正确密码", Toast.LENGTH_SHORT).show();
                } else {
                    if (newpwd.length() < 6) {

                        Toast.makeText(ModificationPasswordActivity.this, "密码至少6位数字或字母组成", Toast.LENGTH_SHORT).show();
                    }
                    modificationPassword(oldpwd, newpwd);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    private void modificationPassword(String oldPwd, String newPwd) {
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("userid", userID);
        params.addBodyParameter("password", oldPwd);
        params.addBodyParameter("pwd", newPwd);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("tag", result);
                try {
                    JSONObject mJson = new JSONObject(result);
                    String mCode = mJson.getString("code");
                    if (mCode.equals("3000")) {
                        editor.putString("state", "2").apply();
                        //TODO 跳转到登录界面，并且讲栈中所有Activity清空
                        Intent intent = new Intent(ModificationPasswordActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else if (mCode.equals("-3000")) {
                        Toast.makeText(ModificationPasswordActivity.this, "新密码和旧密码不能一致", Toast.LENGTH_SHORT).show();
                    } else if (mCode.equals("-3001")) {
                        Toast.makeText(ModificationPasswordActivity.this, "密码错误，请重新输入...", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(ModificationPasswordActivity.this, "服务器故障", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }


    @OnClick(R.id.formerpwd_back)
    public void onClick() {
        finish();
    }
}
