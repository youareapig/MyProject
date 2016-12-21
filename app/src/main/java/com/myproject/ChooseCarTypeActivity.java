package com.myproject;

import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adpter.ChooseCarType_Adapter;
import bean.CarBean;
import myview.QuickIndexBar;

public class ChooseCarTypeActivity extends AppCompatActivity {
    private ListView car_listview;
    private QuickIndexBar car_bar;
    private List<CarBean> carBeenList;
    private CarBean carBean1, carBean2, carBean3, carBean4, carBean5;
    private Handler handler = new Handler();
    private TextView tv_center;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_car_type);
        car_listview = (ListView) findViewById(R.id.choosecar_listview);
        car_bar = (QuickIndexBar) findViewById(R.id.bar);
        tv_center = (TextView) findViewById(R.id.show);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        //TODO 关闭手势
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        carBeenList = new ArrayList<>();
        carBean1 = new CarBean("路虎", R.mipmap.ic_launcher);
        carBean2 = new CarBean("林肯", R.mipmap.ic_launcher);
        carBean3 = new CarBean("宝马", R.mipmap.ic_launcher);
        carBean4 = new CarBean("大众", R.mipmap.ic_launcher);
        carBean5 = new CarBean("法拉利", R.mipmap.ic_launcher);
        carBeenList.add(carBean1);
        carBeenList.add(carBean2);
        carBeenList.add(carBean3);
        carBeenList.add(carBean4);
        carBeenList.add(carBean5);
        //TODO 按首字母排序
        Collections.sort(carBeenList);
        car_listview.setAdapter(new ChooseCarType_Adapter(this, carBeenList));
        car_bar.setListener(new QuickIndexBar.OnLetterUpdateListener() {
            @Override
            public void onLetterUpdate(String letter) {
                showLetter(letter);
                for (int i = 0; i < carBeenList.size(); i++) {
                    CarBean carBean = carBeenList.get(i);
                    String l = carBean.getPinyin().charAt(0) + "";
                    if (TextUtils.equals(letter, l)) {
                        car_listview.setSelection(i);
                        break;
                    }
                }
            }
        });
        car_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO 打开侧拉
                drawer.openDrawer(Gravity.RIGHT);
            }
        });
    }

    private void showLetter(String letter) {
        tv_center.setVisibility(View.VISIBLE);
        tv_center.setText(letter);
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_center.setVisibility(View.GONE);
            }
        }, 2000);
    }

}
