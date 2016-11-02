package com.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import adpter.GoodsList_ListView_Adapter;
import bean.GoodsList_Bean;

public class GoodsListActivity extends AppCompatActivity implements OnClickListener {
    private RelativeLayout goodslist_brand;
    private PopupWindow popupWindow;
    private ListView goodslist_listview;
    private List<GoodsList_Bean> goodslist_list;
    private String NAME = "姐夫斯卡拉飞机凯撒蓝方块了撒娇分开了房间";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        goodslist_brand = (RelativeLayout) findViewById(R.id.goodslist_brand);
        goodslist_listview = (ListView) findViewById(R.id.goodslist_listview);
        goodslist_brand.setOnClickListener(this);
        /**
         * ListView数据
         */
        GoodsList_Bean goodsListBean1 = new GoodsList_Bean(R.mipmap.listview1, NAME, 9.8, 265);
        GoodsList_Bean goodsListBean2 = new GoodsList_Bean(R.mipmap.listview1, NAME, 9.8, 265);
        GoodsList_Bean goodsListBean3 = new GoodsList_Bean(R.mipmap.listview1, NAME, 9.8, 265);
        GoodsList_Bean goodsListBean4 = new GoodsList_Bean(R.mipmap.listview1, NAME, 9.8, 265);
        goodslist_list=new ArrayList<>();
        goodslist_list.add(goodsListBean1);
        goodslist_list.add(goodsListBean2);
        goodslist_list.add(goodsListBean3);
        goodslist_list.add(goodsListBean4);
        goodslist_listview.setAdapter(new GoodsList_ListView_Adapter(this,goodslist_list));
        goodslist_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(GoodsListActivity.this,GoodsDetailsActivity.class);
                startActivity(intent);
            }
        });
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goodslist_brand:
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    return;
                } else {
                    initmPopupWindowView();
                    popupWindow.showAsDropDown(v, 0, 5);
                }
                break;

        }
    }

    /**
     * PopupWindow
     */
    public void initmPopupWindowView() {

        // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.goodslist_brand_pop,
                null, false);
        // 创建PopupWindow实例,宽度和高度
        popupWindow = new PopupWindow(customView, 720, 146);
        // 设置动画效果
        popupWindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }

                return false;
            }
        });
    }
}