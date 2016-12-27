package com.myproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import init.Init;
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

    @OnClick(R.id.activity_about_update)
    public void onClick() {
        UpdateVersion.checkVersion(this,locationVersion);
    }
}
