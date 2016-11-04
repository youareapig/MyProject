package com.myproject;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import adpter.GoodsList_ListView_Adapter;
import adpter.Pop_brand_Adapter;
import bean.GoodsList_Bean;

public class GoodsListActivity extends AppCompatActivity implements OnClickListener {
    private RelativeLayout goodslist_brand,goods_sale;
    private PopupWindow popupWindow;
    private ListView goodslist_listview;
    private List<GoodsList_Bean> goodslist_list = new ArrayList<>();
    private List<String> pop_list;
    private String NAME = "姐夫斯卡拉飞机凯撒蓝方块了撒娇分开了房间";
    private TextView  goods_sales;
    private GoodsList_ListView_Adapter goodsListListViewAdapter;
    private boolean bool = false;
    private GridView pop_grid_brand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        goodslist_brand = (RelativeLayout) findViewById(R.id.goodslist_brand);
        goodslist_listview = (ListView) findViewById(R.id.goodslist_listview);
        goods_sale = (RelativeLayout) findViewById(R.id.goods_sale_v);
        goods_sales = (TextView) findViewById(R.id.goods_sales);
        goodslist_brand.setOnClickListener(this);
        goods_sale.setOnClickListener(this);
        goods_sales.setOnClickListener(this);
        /**
         * ListView数据
         */
        GoodsList_Bean goodsListBean1 = new GoodsList_Bean(R.mipmap.listview1, NAME, 9.8, 800);
        GoodsList_Bean goodsListBean2 = new GoodsList_Bean(R.mipmap.listview1, NAME, 8.8, 400);
        GoodsList_Bean goodsListBean3 = new GoodsList_Bean(R.mipmap.listview1, NAME, 16.8, 20);
        GoodsList_Bean goodsListBean4 = new GoodsList_Bean(R.mipmap.listview1, NAME, 5.8, 600);
        goodslist_list.add(goodsListBean1);
        goodslist_list.add(goodsListBean2);
        goodslist_list.add(goodsListBean3);
        goodslist_list.add(goodsListBean4);
        goodsListListViewAdapter = new GoodsList_ListView_Adapter(this, goodslist_list);
        goodslist_listview.setAdapter(goodsListListViewAdapter);
        goodslist_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GoodsListActivity.this, GoodsDetailsActivity.class);
                startActivity(intent);
            }
        });
        /**品牌选择*/
        pop_list=new ArrayList<>();
        pop_list.add("龟牌1");
        pop_list.add("龟牌2");
        pop_list.add("龟牌3");
        pop_list.add("龟牌4");
        pop_list.add("龟牌5");
        pop_list.add("龟牌6");
        pop_list.add("龟牌7");
        pop_list.add("龟牌8");
        pop_list.add("龟牌9");
        pop_list.add("龟牌10");
        pop_list.add("龟牌11");
        pop_list.add("龟牌12");
        pop_list.add("龟牌13");
        pop_list.add("龟牌14");
        pop_list.add("龟牌15");
        pop_list.add("龟牌16");
        pop_list.add("龟牌17");
    }

    /**
     * 价格从小到大
     */
    public void up() {
        Collections.sort(goodslist_list, new Comparator<GoodsList_Bean>() {
            @Override
            public int compare(GoodsList_Bean o1, GoodsList_Bean o2) {
                return (int) (o1.getGoodslist_sale() - o2.getGoodslist_sale());

            }

        });


    }

    /**
     * 价格从大到小
     */
    public void down() {
        Collections.sort(goodslist_list, new Comparator<GoodsList_Bean>() {
            @Override
            public int compare(GoodsList_Bean o1, GoodsList_Bean o2) {
                return (int) (o2.getGoodslist_sale() - o1.getGoodslist_sale());

            }

        });


    }

    /**
     * 销量排序
     */
    public void sales() {
        Collections.sort(goodslist_list, new Comparator<GoodsList_Bean>() {
            @Override
            public int compare(GoodsList_Bean o1, GoodsList_Bean o2) {
                return (o2.getGoodslist_person() - o1.getGoodslist_person());
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
                    initmPopupWindowView(v);
                    popupWindow.showAsDropDown(v, 0, 5);
                }
                break;
            case R.id.goods_sale_v:
                if (bool == false) {
                    up();
                    bool = true;
                Log.d("tag","ture");
                } else if (bool = true) {
                    down();
                    bool = false;
                    Log.d("tag","false");
                }

                goodsListListViewAdapter.notifyDataSetChanged();
                break;
            case R.id.goods_sales:

                sales();
                goodsListListViewAdapter.notifyDataSetChanged();
                break;

        }
    }

    /**
     * PopupWindow品牌选择
     */
    public void initmPopupWindowView(View v) {

        // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.goodslist_brand_pop,
                null, false);
        pop_grid_brand= (GridView) customView.findViewById(R.id.pop_brand);
        // 创建PopupWindow实例,宽度和高度
        popupWindow = new PopupWindow(customView, 720, 160);
        // 设置动画效果
       // popupWindow.setAnimationStyle(R.style.AnimationFade);
       //点击外部消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        pop_grid_brand.setAdapter(new Pop_brand_Adapter(this,pop_list));

    }

}