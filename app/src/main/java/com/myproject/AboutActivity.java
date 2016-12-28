package com.myproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.ailunwang.appupdate.service.UpdateService;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import init.Init;
import myview.CustomDialog;
import utils.UpdateVersion;

public class AboutActivity extends AppCompatActivity {
    @BindView(R.id.activity_about_update)
    RelativeLayout mUpdate;
    private RelativeLayout about_back;
    private int locationVersion = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        Init init = (Init) getApplication();
        locationVersion = init.location;
        about_back = (RelativeLayout) findViewById(R.id.about_back);
        about_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            final HashMap<String,String> hashMap = (HashMap<String, String>) message.obj;
            if (hashMap!=null){
                if (Integer.parseInt(hashMap.get("version"))>locationVersion){
                    CustomDialog.Builder builder1 = new CustomDialog.Builder(AboutActivity.this);
                    builder1.setTitle("升级提示");
                    builder1.setMessage("发现新版本，请及时更新");
                    builder1.setPositiveButton("立即升级", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent(AboutActivity.this, UpdateService.class);
                            intent.putExtra("apkUrl", hashMap.get("url"));
                            startService(intent);
                        }
                    });
                    builder1.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder1.create().show();
                }
            }
            return false;
        }
    });
    @OnClick(R.id.activity_about_update)
    public void onClick() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> hashMap = new UpdateVersion().checkVersion(AboutActivity.this, locationVersion);
                if (hashMap != null) {
                    Message message = new Message();
                    message.obj = hashMap;
                    handler.sendMessage(message);
                }

            }
        }).start();


        }

}
