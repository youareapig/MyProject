package com.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.utils.L;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import adpter.IndentActivityAdapter;
import bean.DataBean;
import bean.OrderDataBean;
import bean.orderBean;
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
    private RecyclerView mRecyclerView;
    private IndentActivityAdapter adapter;
    private static final String LOG_TAG = "IndentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        buyatonce = (TextView) findViewById(R.id.buyatonce);
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_indent_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        adapter = new IndentActivityAdapter();
        mRecyclerView.setAdapter(adapter);
        Intent intent = this.getIntent();
        String result_goods_num = intent.getStringExtra("goodsnum");
        Log.d("tag", result_goods_num + "商品数量");
        // indent_goods_num.setText(result_goods_num);
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
                Intent intent = new Intent(IndentActivity.this,ManageAddressActivity.class);
                intent.putExtra("isBack",true);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v(LOG_TAG,"------------------>"+resultCode);
        if (requestCode==0&&resultCode==0){
            Log.v(LOG_TAG,"------------------>"+"is return data");
            if (data!=null){
                DataBean dataBean =data.getParcelableExtra("address");
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

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetOrderData(OrderDataBean orderDataBean) {
        Log.v(LOG_TAG, "----------->>" + orderDataBean.getCode());
        List<orderBean> orderBeanList = new ArrayList<>();
        List<DataBean> datasBeens = new ArrayList<>();
        //datasBeens = orderDataBean.getData().getMember();
        orderBeanList = orderDataBean.getData().getGoods();
        adapter.addData(orderBeanList);
        if (datasBeens != null || datasBeens.isEmpty()) {
            DataBean dataBean = datasBeens.get(0);
            mCustomer.setText(dataBean.getShr_name());
            mTelephone.setText(dataBean.getShr_phone());
            if (dataBean.getShr_province().equals(dataBean.getShr_city())) {
                mAddress.setText(dataBean.getShr_city() + dataBean.getShr_area() + dataBean.getShr_address() + dataBean.getAddr_id());
            } else {
                mAddress.setText(dataBean.getShr_province() + dataBean.getShr_city() + dataBean.getShr_area() + dataBean.getShr_address() + dataBean.getAddr_id());
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }
}
