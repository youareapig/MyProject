package com.myproject;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;


public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout setting_address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setting_address = (RelativeLayout) findViewById(R.id.setting_address);
        setting_address.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_address:
                Intent intent = new Intent(SettingActivity.this, CompileAddressActivity.class);
                startActivity(intent);
                break;
        }
    }


}
