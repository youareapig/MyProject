package com.myproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ailunwang.appupdate.service.UpdateService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Manifest;

import indexfragment.Store;
import indexfragment.Personal;
import indexfragment.ShopCar;
import indexfragment.Classify;
import init.Init;
import myview.CustomDialog;
import utils.UpdateVersion;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentManager fragmentManager;
    private Fragment fragment = new Fragment();
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    private RelativeLayout btn_index, btn_store, btn_shopcar, btn_personal;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ImageView index_img, store_img, shopcar_img, personal_img;
    private TextView index_name, store_name, shopcar_name, personal_name;
    private int currentIndex = 0;
    private SharedPreferences sharedPreferences;
    private DialogInterface mDialogInterface;
    private String state;
    private Bundle savedInstanceState;
    private int locationVersion = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init init = (Init) getApplication();
        locationVersion = init.location;
        this.savedInstanceState=savedInstanceState;
        //危险权限申请
        checkAndApplyPermission();

    }
    public void normal(){
        initView();
        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        Intent intent = this.getIntent();
        currentIndex = intent.getIntExtra("fragment_tag", 0);
        if (currentIndex == 2) {
            index_img.setImageResource(R.mipmap.store_uncheck);
            index_name.setTextColor(this.getResources().getColor(R.color.c_black));
            store_img.setImageResource(R.mipmap.classify_uncheck);
            store_name.setTextColor(this.getResources().getColor(R.color.c_black));
            shopcar_img.setImageResource(R.mipmap.shopcar_check);
            shopcar_name.setTextColor(this.getResources().getColor(R.color.c_blue));
            personal_img.setImageResource(R.mipmap.person_uncheck);
            personal_name.setTextColor(this.getResources().getColor(R.color.c_black));
        }
        Log.d("tag", "传过来" + currentIndex);
        fragmentManager = this.getSupportFragmentManager();
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT, 0);
            fragmentList.removeAll(fragmentList);
            fragmentList.add(fragmentManager.findFragmentByTag(0 + ""));
            fragmentList.add(fragmentManager.findFragmentByTag(1 + ""));
            fragmentList.add(fragmentManager.findFragmentByTag(2 + ""));
            fragmentList.add(fragmentManager.findFragmentByTag(3 + ""));
            restoreFragment();
        } else {
            fragmentList.add(new Store());
            fragmentList.add(new Classify());
            fragmentList.add(new ShopCar());
            fragmentList.add(new Personal());
            showFragment();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String,String> hashMap =new UpdateVersion().checkVersion(MainActivity.this,locationVersion);
                if (hashMap!=null){
                    Message message = new Message();
                    message.obj = hashMap;
                    handler.sendMessage(message);
                }

            }
        }).start();
    }
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            final HashMap<String,String> hashMap = (HashMap<String, String>) message.obj;
            if (hashMap!=null){
                if (Integer.parseInt(hashMap.get("version"))>locationVersion){
                    CustomDialog.Builder builder1 = new CustomDialog.Builder(MainActivity.this);
                    builder1.setTitle("升级提示");
                    builder1.setMessage("发现新版本，请及时更新");
                    builder1.setPositiveButton("立即升级", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent(MainActivity.this, UpdateService.class);
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
    private void initView() {
        btn_index = (RelativeLayout) findViewById(R.id.index);
        btn_store = (RelativeLayout) findViewById(R.id.store);
        btn_shopcar = (RelativeLayout) findViewById(R.id.shopcar);
        btn_personal = (RelativeLayout) findViewById(R.id.personal);
        index_img = (ImageView) findViewById(R.id.index_img);
        index_name = (TextView) findViewById(R.id.index_name);
        store_img = (ImageView) findViewById(R.id.store_img);
        store_name = (TextView) findViewById(R.id.store_name);
        shopcar_img = (ImageView) findViewById(R.id.shopcar_img);
        shopcar_name = (TextView) findViewById(R.id.shopcar_name);
        personal_img = (ImageView) findViewById(R.id.personal_img);
        personal_name = (TextView) findViewById(R.id.personal_name);
        btn_index.setOnClickListener(this);
        btn_store.setOnClickListener(this);
        btn_shopcar.setOnClickListener(this);
        btn_personal.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_FRAGMENT, currentIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.index:
                currentIndex = 0;
                showFragment();
                index_img.setImageResource(R.mipmap.store_check);
                index_name.setTextColor(this.getResources().getColor(R.color.c_blue));
                store_img.setImageResource(R.mipmap.classify_uncheck);
                store_name.setTextColor(this.getResources().getColor(R.color.c_black));
                shopcar_img.setImageResource(R.mipmap.shopcar_uncheck);
                shopcar_name.setTextColor(this.getResources().getColor(R.color.c_black));
                personal_img.setImageResource(R.mipmap.person_uncheck);
                personal_name.setTextColor(this.getResources().getColor(R.color.c_black));
                break;
            case R.id.store:
                currentIndex = 1;
                showFragment();
                index_img.setImageResource(R.mipmap.store_uncheck);
                index_name.setTextColor(this.getResources().getColor(R.color.c_black));
                store_img.setImageResource(R.mipmap.classify_checked);
                store_name.setTextColor(this.getResources().getColor(R.color.c_blue));
                shopcar_img.setImageResource(R.mipmap.shopcar_uncheck);
                shopcar_name.setTextColor(this.getResources().getColor(R.color.c_black));
                personal_img.setImageResource(R.mipmap.person_uncheck);
                personal_name.setTextColor(this.getResources().getColor(R.color.c_black));
                break;
            case R.id.shopcar:
                state = sharedPreferences.getString("state", null);
                try {
                    if (state.equals("1")) {
                        currentIndex = 2;
                        showFragment();
                        index_img.setImageResource(R.mipmap.store_uncheck);
                        index_name.setTextColor(this.getResources().getColor(R.color.c_black));
                        store_img.setImageResource(R.mipmap.classify_uncheck);
                        store_name.setTextColor(this.getResources().getColor(R.color.c_black));
                        shopcar_img.setImageResource(R.mipmap.shopcar_check);
                        shopcar_name.setTextColor(this.getResources().getColor(R.color.c_blue));
                        personal_img.setImageResource(R.mipmap.person_uncheck);
                        personal_name.setTextColor(this.getResources().getColor(R.color.c_black));
                    } else if (state.equals("2")) {
                        hintLogin(2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    hintLogin(2);
                }


                break;
            case R.id.personal:

                //TODO 获取状态，判断是否登录 1为登录
                state = sharedPreferences.getString("state", null);
                try {
                    if (state.equals("1")) {
                        currentIndex = 3;
                        showFragment();
                        index_img.setImageResource(R.mipmap.store_uncheck);
                        index_name.setTextColor(this.getResources().getColor(R.color.c_black));
                        store_img.setImageResource(R.mipmap.classify_uncheck);
                        store_name.setTextColor(this.getResources().getColor(R.color.c_black));
                        shopcar_img.setImageResource(R.mipmap.shopcar_uncheck);
                        shopcar_name.setTextColor(this.getResources().getColor(R.color.c_black));
                        personal_img.setImageResource(R.mipmap.person_check);
                        personal_name.setTextColor(this.getResources().getColor(R.color.c_blue));
                    } else if (state.equals("2")) {
                        hintLogin(3);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    hintLogin(3);

                }
                break;
        }
    }

    private void showFragment() {
        if (fragment==null)return;
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //如果之前没有添加过
        if (!fragmentList.get(currentIndex).isAdded()) {
            transaction
                    .hide(fragment)
                    .add(R.id.index_fragment, fragmentList.get(currentIndex), "" + currentIndex);  //第三个参数为添加当前的fragment时绑定一个tag

        } else {
            transaction
                    .hide(fragment)
                    .show(fragmentList.get(currentIndex));
        }

        fragment = fragmentList.get(currentIndex);

        transaction.commitAllowingStateLoss();

    }

    private void restoreFragment() {


        FragmentTransaction mBeginTreansaction = fragmentManager.beginTransaction();

        for (int i = 0; i < fragmentList.size(); i++) {

            if (i == currentIndex) {
                mBeginTreansaction.show(fragmentList.get(i));
            } else {
                mBeginTreansaction.hide(fragmentList.get(i));
            }

        }

        mBeginTreansaction.commitAllowingStateLoss();

        //把当前显示的fragment记录下来
        fragment = fragmentList.get(currentIndex);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mDialogInterface.dismiss();
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case 2:
                    currentIndex = 2;
                    showFragment();
                    index_img.setImageResource(R.mipmap.store_uncheck);
                    index_name.setTextColor(this.getResources().getColor(R.color.c_black));
                    store_img.setImageResource(R.mipmap.classify_uncheck);
                    store_name.setTextColor(this.getResources().getColor(R.color.c_black));
                    shopcar_img.setImageResource(R.mipmap.shopcar_check);
                    shopcar_name.setTextColor(this.getResources().getColor(R.color.c_blue));
                    personal_img.setImageResource(R.mipmap.person_uncheck);
                    personal_name.setTextColor(this.getResources().getColor(R.color.c_black));
                    break;
                case 3:
                    currentIndex = 3;
                    showFragment();
                    index_img.setImageResource(R.mipmap.store_uncheck);
                    index_name.setTextColor(this.getResources().getColor(R.color.c_black));
                    store_img.setImageResource(R.mipmap.classify_uncheck);
                    store_name.setTextColor(this.getResources().getColor(R.color.c_black));
                    shopcar_img.setImageResource(R.mipmap.shopcar_uncheck);
                    shopcar_name.setTextColor(this.getResources().getColor(R.color.c_black));
                    personal_img.setImageResource(R.mipmap.person_check);
                    personal_name.setTextColor(this.getResources().getColor(R.color.c_blue));
                    break;
            }
        }
    }

    private void hintLogin(final int flag) {
        CustomDialog.Builder builder = new CustomDialog.Builder(MainActivity.this);
        builder.setTitle("提示");
        builder.setMessage("您还未登陆，确认登陆?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDialogInterface=dialog;
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("login",true);
                startActivityForResult(intent,flag);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
    //申请危险权限
    public void checkAndApplyPermission(){
        List<String> permissions = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissions.add(android.Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
            permissions.add(android.Manifest.permission.CALL_PHONE);
        }
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            permissions.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            permissions.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissions.size()>0){
            ActivityCompat.requestPermissions(this, permissions.toArray(new String[permissions.size()]), 0);
        }else {
            normal();
        }
     }
    //危险权限申请回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==0){
            boolean isPermission = true;
            for (int i =0;i<grantResults.length;i++){
                if (grantResults[i]!=PackageManager.PERMISSION_GRANTED){
                    isPermission=false;
                }
            }
            if (isPermission){
                normal();
            }else {
                finish();
            }
        }
    }

}
