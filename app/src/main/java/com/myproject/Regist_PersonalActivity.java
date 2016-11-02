package com.myproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import utils.ChooseDate;

public class Regist_PersonalActivity extends AppCompatActivity {
    private TextView personal_finish;
    private ImageView personal_back;
    private TextView editText_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist__personal);
        personal_finish= (TextView) findViewById(R.id.personal_finish);
        personal_back= (ImageView) findViewById(R.id.personal_back);
        editText_date= (TextView) findViewById(R.id.regist_date);
        personal_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        personal_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Regist_PersonalActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
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
}
