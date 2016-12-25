package com.myproject;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import myview.CustomDialog;
import utils.DataCleanManager;


public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout setting_address, clearcache, setting_personal,modification_pwd,setting_back;
    private TextView cache;
    private DataCleanManager cleanManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //清除缓存工具
        cleanManager = new DataCleanManager();
        setting_address = (RelativeLayout) findViewById(R.id.setting_address);
        clearcache = (RelativeLayout) findViewById(R.id.clearcache);
        setting_personal = (RelativeLayout) findViewById(R.id.setting_personal);
        cache = (TextView) findViewById(R.id.cache);
        modification_pwd= (RelativeLayout) findViewById(R.id.modification_pwd);
        setting_back= (RelativeLayout) findViewById(R.id.setting_back);
        setting_back.setOnClickListener(this);
        modification_pwd.setOnClickListener(this);
        clearcache.setOnClickListener(this);
        setting_personal.setOnClickListener(this);
        setting_address.setOnClickListener(this);
        try {
            //缓存大小
            String stringCache = cleanManager.getTotalCacheSize(this);
            cache.setText(stringCache);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_address:
                Intent intent = new Intent(SettingActivity.this, ManageAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.clearcache:
                //清除缓存
                CustomDialog.Builder builder = new CustomDialog.Builder(SettingActivity.this);
                builder.setTitle("清除缓存");
                builder.setMessage("是否清除缓存？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cleanManager.clearAllCache(SettingActivity.this);
                        dialog.dismiss();
                        cache.setText("0K");
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();

                break;
            case R.id.setting_personal:
                Intent intent1=new Intent(SettingActivity.this,PersonalInformationActivity.class);
                startActivity(intent1);
                break;
            case R.id.modification_pwd:
                Intent intent2=new Intent(SettingActivity.this,ModificationPasswordActivity.class);
                startActivity(intent2);
                break;
            case R.id.setting_back:
                finish();
                break;
        }
    }




}
