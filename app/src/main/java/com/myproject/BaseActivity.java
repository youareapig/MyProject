package com.myproject;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;
import utils.AppManager;

/**
 * Created by Administrator on 2016/12/5.
 */
public class BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        AppManager.getSingleInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getSingleInstance().finishActivity(this);
    }
}
