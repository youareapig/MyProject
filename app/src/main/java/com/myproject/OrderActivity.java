package com.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adpter.OrderList_Adapter;
import bean.Order_Bean;

public class OrderActivity extends AppCompatActivity {
    private Order_Bean orderBean1, orderBean2, orderBean3, orderBean4;
    private List<Order_Bean> orderBeanList;
    private ListView orderListView;
    private TextView order_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderListView= (ListView) findViewById(R.id.order_listview);
        order_title= (TextView) findViewById(R.id.order_title);
        //TODO 设置标题
        Intent intent=this.getIntent();
        String resultTitle=intent.getStringExtra("title");
        order_title.setText(resultTitle);
        //TODO 数据
        orderBeanList = new ArrayList<>();
        orderBean1 = new Order_Bean(getDate(), 650, 0, "已完成", R.mipmap.shopcar_01, "米其林轮胎", 650, 1, 650);
        orderBean2 = new Order_Bean(getDate(), 650, 0, "交易关闭", R.mipmap.shopcar_01, "米其林轮胎", 650, 1, 650);
        orderBean3 = new Order_Bean(getDate(), 650, 0, "未付款", R.mipmap.shopcar_01, "米其林轮胎", 650, 1, 650);
        orderBean4 = new Order_Bean(getDate(), 650, 0, "交易中", R.mipmap.shopcar_01, "米其林轮胎", 650, 1, 650);
        orderBeanList.add(orderBean1);
        orderBeanList.add(orderBean2);
        orderBeanList.add(orderBean3);
        orderBeanList.add(orderBean4);
        orderListView.setAdapter(new OrderList_Adapter(this,orderBeanList));

    }


    //TODO 获取当前时间
    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }
}
