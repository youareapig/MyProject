package com.myproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import adpter.IndentActivityAdapter;
import bean.DataBean;
import bean.GoodsDetailsBean;
import bean.OrderMakeSureBean;
import butterknife.BindView;
import butterknife.ButterKnife;


public class IndentActivity extends AppCompatActivity {
    @BindView(R.id.activity_indent_customer)
    TextView mCustomer;
    @BindView(R.id.activity_indent_telephone)
    TextView mTelephone;
    @BindView(R.id.activity_indent_address)
    TextView mAddress;
    @BindView(R.id.activity_indent_addressmanmger)
    RelativeLayout mAddressmanmger;
    @BindView(R.id.buyatonce)
    TextView buyatonce;
    @BindView(R.id.activity_indent_price)
    TextView mPrice;
    @BindView(R.id.activity_indent_totalprice)
    TextView mTotalprice;
    private RecyclerView mRecyclerView;
    private IndentActivityAdapter adapter;
    private String result_goods_num;
    private List<OrderMakeSureBean> list = new ArrayList<>();
    private static final String LOG_TAG = "IndentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent);
        ButterKnife.bind(this);
        adapter = new IndentActivityAdapter();
        buyatonce = (TextView) findViewById(R.id.buyatonce);
        init();
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_indent_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        Intent intent = this.getIntent();
        result_goods_num = intent.getStringExtra("goodsnum");
        EventBus.getDefault().register(this);//注册事件总线
        buyatonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(IndentActivity.this, PayActivity.class);
                startActivity(intent1);
            }
        });
        mAddressmanmger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndentActivity.this, ManageAddressActivity.class);
                intent.putExtra("isBack", true);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v(LOG_TAG, "------------------>" + resultCode);
        if (requestCode == 0 && resultCode == 0) {
            Log.v(LOG_TAG, "------------------>" + "is return data");
            if (data != null) {
                DataBean dataBean = data.getParcelableExtra("address");
                mCustomer.setText(dataBean.getShr_name());
                mTelephone.setText(dataBean.getShr_phone());
                if (dataBean.getShr_province().equals(dataBean.getShr_city())) {
                    mAddress.setText(dataBean.getShr_city() + dataBean.getShr_area() + dataBean.getShr_address() + dataBean.getAddr_id());
                } else {
                    mAddress.setText(dataBean.getShr_province() + dataBean.getShr_city() + dataBean.getShr_area() + dataBean.getShr_address() + dataBean.getAddr_id());
                }
            }
        }
    }

    public void init() {
        SharedPreferences sp = getSharedPreferences("defaultaddress", MODE_PRIVATE);
        if (sp != null) {
            mCustomer.setText(sp.getString("shr_name", ""));
            mTelephone.setText(sp.getString("shr_phone", ""));
            if (sp.getString("shr_province", "").equals(sp.getString("shr_city", ""))) {
                mAddress.setText(sp.getString("shr_city", "") + sp.getString("shr_area", "") + sp.getString("shr_address", "") + sp.getString("addr_id", ""));
            } else {
                mAddress.setText(sp.getString("shr_province", "") + sp.getString("shr_city", "") + sp.getString("shr_area", "") + sp.getString("shr_address", "") + sp.getString("addr_id", ""));
            }
        }
    }
    //EventBus订阅者，接收商品详情传递过来的对象
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetOrderData(GoodsDetailsBean.DataBean orderDataBean) {
        OrderMakeSureBean orderMakeSureBean = new OrderMakeSureBean();
        if (orderDataBean != null) {
            orderMakeSureBean.setCommdityprice(orderDataBean.getShop_price());
            orderMakeSureBean.setCommodityname(orderDataBean.getGoods_name());
            orderMakeSureBean.setImage(orderDataBean.getThumb());
            orderMakeSureBean.setCommoditynumber(result_goods_num);
            double price = Double.parseDouble(orderDataBean.getShop_price())*Integer.parseInt(result_goods_num);
            mPrice.setText(price+"");
            mTotalprice.setText(price+"");
            list.add(orderMakeSureBean);
        }
        if (list != null) {
            adapter.addData(list);
        }
    }
    //接收购物车传递过来的对象
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onGetShopCar(List<OrderMakeSureBean> orderMakeSureBeen){
        if (orderMakeSureBeen!=null){
            double price = 0;
            adapter.addData(orderMakeSureBeen);
            for (OrderMakeSureBean orderMakeSureBean:orderMakeSureBeen){

                price+=Double.parseDouble(orderMakeSureBean.getCommdityprice());
            }
            mPrice.setText(price+"");
            mTotalprice.setText(price+"");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();//移除所有粘性事件
        EventBus.getDefault().unregister(this);//取消注册事件总线
    }
}
