package com.myproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.utils.L;

import fragment.Index;

public class RegistActivity extends AppCompatActivity {
    private CheckBox checkBox_deal;
    private TextView textView_regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        checkBox_deal = (CheckBox) findViewById(R.id.regist_deal);
        textView_regist = (TextView) findViewById(R.id.regist);
        checkBox_deal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    textView_regist.setEnabled(true);
                    textView_regist.setBackgroundResource(R.drawable.btn_login_dot);
                    textView_regist.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(RegistActivity.this,Regist_UserTypeActivity.class);
                            startActivity(intent);
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
}
