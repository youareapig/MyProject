package com.myproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import bean.UserBean;

public class RegistActivity extends AppCompatActivity {
    private CheckBox checkBox_deal;
    private TextView textView_regist;
    private EditText regist_phone, regist_code, regist_pwd;
    private TextView regist_getVercode;
    private String r_getPhone, r_getCode, r_getPassword, r1_getPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        checkBox_deal = (CheckBox) findViewById(R.id.regist_deal);
        textView_regist = (TextView) findViewById(R.id.regist);
        regist_phone = (EditText) findViewById(R.id.regist_phone);
        regist_code = (EditText) findViewById(R.id.regist_vercode);
        regist_pwd = (EditText) findViewById(R.id.regist_pwd);
        regist_getVercode = (TextView) findViewById(R.id.regist_getvercode);


        //TODO 获取验证码
        regist_getVercode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                r1_getPhone = regist_phone.getText().toString();
                getCodeVisit();
            }
        });
        //TODO 判断是否勾选用户协议
        checkBox_deal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    textView_regist.setEnabled(true);
                    textView_regist.setBackgroundResource(R.drawable.btn_login_dot);
                    textView_regist.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            r_getPhone = regist_phone.getText().toString();
                            r_getCode = regist_code.getText().toString();
                            r_getPassword = regist_pwd.getText().toString();
//                            Intent intent=new Intent(RegistActivity.this,Regist_UserTypeActivity.class);
//                            startActivity(intent);
                            if (TextUtils.isEmpty(r_getPhone) || TextUtils.isEmpty(r_getCode) || TextUtils.isEmpty(r_getPassword)) {
                                Toast.makeText(RegistActivity.this, "请完整填写信息", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                visit();
                            }
                        }
                    });
                }
                if (isChecked == false) {
                    Log.d("tag", "111111");
                    textView_regist.setEnabled(false);
                    textView_regist.setBackgroundColor(Color.parseColor("#878a8d"));
                    return;
                }
            }
        });

    }

    public void visit() {

        RequestParams params = new RequestParams("http://192.168.0.108/api.php/Member/regist");
        params.addBodyParameter("mobile", r_getPhone);
        params.addBodyParameter("verfiy", r_getCode);
        params.addBodyParameter("password", r_getPassword);

        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                // Log.d("tag", "注册返回数据" + result);
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(result, UserBean.class);
                if (userBean.getCode() == 3000) {
                    Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                } else if (userBean.getCode() == -3000) {
                    Toast.makeText(RegistActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                } else if (userBean.getCode() == -3001) {
                    Toast.makeText(RegistActivity.this, "验证码不正确", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegistActivity.this, "不明异常", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("tag", "访问出错");
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

    public void getCodeVisit() {
        RequestParams params = new RequestParams("http://192.168.0.108/api.php/Member/regist");
        params.addBodyParameter("mobile", r1_getPhone);
        params.addBodyParameter("type_code", "3");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("tag", "验证码:" + result);
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(result, UserBean.class);

                Log.d("tag", "验证码:" + userBean.getMessage());

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("tag", "验证码访问出错");
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
}
