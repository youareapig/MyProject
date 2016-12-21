package com.myproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import indexfragment.Store;
import indexfragment.Personal;
import indexfragment.ShopCar;
import indexfragment.Classify;

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
    private String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    }

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

                }
                break;
        }
    }

    private void showFragment() {

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

        transaction.commit();

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

        mBeginTreansaction.commit();

        //把当前显示的fragment记录下来
        fragment = fragmentList.get(currentIndex);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case 2:
                    currentIndex = 2;
                    showFragment();
                    break;
                case 3:
                    currentIndex = 3;
                    showFragment();
                    break;
            }
        }
    }

    private void hintLogin(final int flag) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("提示");
        builder.setMessage("您还未登陆，确认登陆?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("login",true);
                startActivityForResult(intent,flag);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }


}
