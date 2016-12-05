package com.myproject;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import utils.ChooseDate;

public class Regist_PersonalActivity extends AppCompatActivity {
    private TextView personal_finish;
    private ImageView personal_back;
    private TextView editText_date;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private RadioButton bt_boy,bt_girl;
    private EditText carid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist__personal);
        sharedPreferences = getSharedPreferences("userInformation", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        personal_finish= (TextView) findViewById(R.id.personal_finish);
        personal_back= (ImageView) findViewById(R.id.personal_back);
        editText_date= (TextView) findViewById(R.id.regist_date);
        bt_boy= (RadioButton) findViewById(R.id.rdbt_boy);
        bt_girl= (RadioButton) findViewById(R.id.rdbt_girl);
        carid= (EditText) findViewById(R.id.carid);
        personal_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        personal_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender="";
                String groupid=sharedPreferences.getString("groupid",null);
                String mobile=sharedPreferences.getString("mobile",null);
                String password=sharedPreferences.getString("password",null);
                String birthday=editText_date.getText().toString();
                String car_number=carid.getText().toString();
                if (bt_boy.isChecked()){
                    gender=bt_boy.getText().toString();
                }else if (bt_girl.isChecked()){
                    gender=bt_girl.getText().toString();
                }
                if (TextUtils.isEmpty(birthday)||TextUtils.isEmpty(car_number)){
                    Toast.makeText(Regist_PersonalActivity.this,"请完善个人信息",Toast.LENGTH_SHORT).show();
                }else {
                    visit(mobile,password,groupid,birthday,gender,car_number);
                }


            }
        });
        editText_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseDate chooseDate=new ChooseDate(Regist_PersonalActivity.this);
                chooseDate.dateTimePicKDialog(editText_date);
            }
        });

    }
    private void visit(String mobile,String password,String groupid,String birthday,String gender,String car_number){
        RequestParams params=new RequestParams("http://192.168.0.125/api.php/Member/registAdd");
        params.addBodyParameter("mobile",mobile);
        params.addBodyParameter("password",password);
        params.addBodyParameter("groupid",groupid);
        params.addBodyParameter("birthday",birthday);
        params.addBodyParameter("gender",gender);
        params.addBodyParameter("car_type","");
        params.addBodyParameter("car_number",car_number);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
            Log.i("result","请求成功"+result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("result","请求失败");
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
