package com.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import myview.CountDownTimerUtils;
import utils.ClassPathResource;
import utils.Global;

public class RetrievePasswordActivity extends AppCompatActivity {

    @BindView(R.id.retrievrpasswordactivity_back)
    ImageView mBack;
    @BindView(R.id.retrievrpasswordactivity_telephone)
    EditText mTelephone;
    @BindView(R.id.retrievrpasswordactivity_vercode)
    EditText mVercode;
    @BindView(R.id.retrievrpasswordactivity_getvercode)
    TextView mGetvercode;
    @BindView(R.id.retrievrpasswordactivity_userpassword)
    EditText mUserpassword;
    @BindView(R.id.retrievrpasswordactivity_repeatuserpassword)
    EditText mRepeatuserpassword;
    @BindView(R.id.retrievrpasswordactivity_complete)
    TextView mComplete;
    private Unbinder unbinder;
    private Global global;
    private String verCodeUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_password);
        unbinder = ButterKnife.bind(this);
        global = new Global();
        verCodeUrl = global.getUrl() + "api.php/Member/restPassword";

    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }


    @OnClick({R.id.retrievrpasswordactivity_getvercode, R.id.retrievrpasswordactivity_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.retrievrpasswordactivity_getvercode:
                String phone = mTelephone.getText().toString().trim();
                ClassPathResource classPathResource = new ClassPathResource();
                //TODO 正则表达式判断电话号码
                boolean isPhone = classPathResource.isMobileNO(phone);
                if (isPhone == false) {
                    Toast.makeText(RetrievePasswordActivity.this, "请输入正确电话号码", Toast.LENGTH_SHORT).show();
                } else {
                    getVervode(phone);
                }
                break;
            case R.id.retrievrpasswordactivity_complete:
                String phone1 = mTelephone.getText().toString().trim();
                String getCode = mVercode.getText().toString().trim();
                String getUserpassword = mUserpassword.getText().toString().trim();
                String getRetrieverUserpassword = mRetrieverUserpassword.getText().toString().trim();
                if (TextUtils.isEmpty(getCode) || TextUtils.isEmpty(getUserpassword) || TextUtils.isEmpty(getRetrieverUserpassword)) {
                    Toast.makeText(RetrievePasswordActivity.this, "请输入验证码或新密码", Toast.LENGTH_SHORT).show();
                } else {
                    if (!getUserpassword.equals(getRetrieverUserpassword)) {
                        Toast.makeText(RetrievePasswordActivity.this, "两次密码不一致，请确认", Toast.LENGTH_SHORT).show();
                    } else {
                        if (getUserpassword.length() < 6) {
                            Toast.makeText(RetrievePasswordActivity.this, "密码过于简单", Toast.LENGTH_SHORT).show();
                        } else {
                            sure(phone1, getUserpassword, getCode);
                        }

                    }
                }
                break;
        }
    }

    private void getVervode(String tel) {
        RequestParams params = new RequestParams(verCodeUrl);
        params.addBodyParameter("mobile", tel);
        params.addBodyParameter("type_code", "3");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("tag", "请求成功：" + result);
                try {
                    JSONObject mJson = new JSONObject(result);
                    String mCode = mJson.getString("code");
                    if (mCode.equals("-3007")) {
                        Toast.makeText(RetrievePasswordActivity.this, "手机号还没有进行注册", Toast.LENGTH_SHORT).show();
                    } else {
                        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(mGetvercode, 60000, 1000);
                        mCountDownTimerUtils.start();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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

    private void sure(String tel, String password, String verfiy) {
        RequestParams pamas = new RequestParams(verCodeUrl);
        pamas.addBodyParameter("mobile", tel);
        pamas.addBodyParameter("password", password);
        pamas.addBodyParameter("verfiy", verfiy);
        Log.i("tag", tel + password + verfiy);
        x.http().post(pamas, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("tag", "请求成功：" + result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String mCode = jsonObject.getString("code");
                    if (mCode.equals("3000")) {
                        finish();
                    } else if (mCode.equals("-3001")) {
                        Toast.makeText(RetrievePasswordActivity.this, "验证码错误,请重新输入", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RetrievePasswordActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("tag", "请求错误：");
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
