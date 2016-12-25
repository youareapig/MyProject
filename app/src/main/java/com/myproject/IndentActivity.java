package com.myproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import adpter.IndentActivityAdapter;
import bean.AddressBean;
import bean.DataBean;
import bean.GoodsDetailsBean;
import bean.OrderData;
import bean.OrderMakeSureBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.Format;
import utils.Global;
import utils.ToastUtil;


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
    private String mAddressID;
    private StringBuilder builder = new StringBuilder();
    private StringBuilder builder1 = new StringBuilder();
    private StringBuilder builder2 = new StringBuilder();
    private StringBuilder builder3 = new StringBuilder();
    private SharedPreferences sp1;
    public double price=0;
    private List<DataBean> mList;
    private List<OrderMakeSureBean> list = new ArrayList<>();
    private OrderData mOrderData = new OrderData();
    private static final String LOG_TAG = "IndentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent);
        ButterKnife.bind(this);
        adapter = new IndentActivityAdapter();
        buyatonce = (TextView) findViewById(R.id.buyatonce);
        sp1 = getSharedPreferences("userLogin", MODE_PRIVATE);
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_indent_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        RequestDefaultAddress();
        Intent intent = this.getIntent();
        result_goods_num = intent.getStringExtra("goodsnum");
        EventBus.getDefault().register(this);//注册事件总线
        buyatonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mCustomer.getText().toString().trim();
                String address=mAddress.getText().toString().trim();
                String tel=mTelephone.getText().toString().trim();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(address)||TextUtils.isEmpty(tel)){
                    Log.v(LOG_TAG,"-------------->"+mOrderData.getAddr());
                    ToastUtil.showToast(IndentActivity.this,"请选择收货地址");
                }else {
                    placeOrder(mOrderData);
                }
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
                mAddressID = dataBean.getAddr_id();
                mOrderData.setAddr(mAddressID);
                Log.e("test","地址id："+mAddressID);
                if (dataBean.getShr_province().equals(dataBean.getShr_city())) {
                    mAddress.setText(dataBean.getShr_city() + dataBean.getShr_area() + dataBean.getShr_address() );
                } else {
                    mAddress.setText(dataBean.getShr_province() + dataBean.getShr_city() + dataBean.getShr_area() + dataBean.getShr_address());
                }
            }
        }
        if (requestCode==1&&resultCode==1){

            Log.v(LOG_TAG, "------------------>" + "is return data become gray");
            buyatonce.setEnabled(false);
            buyatonce.setBackgroundColor(Color.GRAY);
        }
    }

    public void init(DataBean databean) {
        if (databean != null) {
            mOrderData.setAddr(databean.getAddr_id());
            mOrderData.setMessage("");
            mCustomer.setText(databean.getShr_name());
            mTelephone.setText(databean.getShr_phone());
            if (databean.getShr_province().equals(databean.getShr_city())) {
                mAddress.setText(databean.getShr_city() + databean.getShr_area() + databean.getShr_address());
            } else {
                mAddress.setText(databean.getShr_province() + databean.getShr_city() + databean.getShr_area() + databean.getShr_address());
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
            price = Double.parseDouble(orderDataBean.getShop_price())*Integer.parseInt(result_goods_num);
            mOrderData.setGoods_id(orderDataBean.getGoods_id());
            mOrderData.setGoods_image(orderDataBean.getThumb());
            mOrderData.setGoods_number(result_goods_num);
            mOrderData.setGoods_total(price+"");
            mOrderData.setTotal_price(price+"");
            mOrderData.setSend_price("0.00");
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
            adapter.addData(orderMakeSureBeen);
            builder.delete(0,builder.length());
            builder1.delete(0,builder1.length());
            builder2.delete(0,builder2.length());
            builder3.delete(0,builder3.length());
            for (OrderMakeSureBean orderMakeSureBean:orderMakeSureBeen){
                double commedityprice=0;
                price+=(Double.parseDouble(orderMakeSureBean.getCommdityprice())*Integer.parseInt(orderMakeSureBean.getCommoditynumber()));
                commedityprice=Double.parseDouble(orderMakeSureBean.getCommdityprice())*Integer.parseInt(orderMakeSureBean.getCommoditynumber());
                builder.append(",");
                builder.append(orderMakeSureBean.getCommdityId());
                builder1.append(",");
                builder1.append(orderMakeSureBean.getCommoditynumber());
                builder2.append(",");
                builder2.append(orderMakeSureBean.getImage());
                builder3.append(",");
                builder3.append((commedityprice+""));
            }
            builder.delete(0,1);
            builder1.delete(0,1);
            builder2.delete(0,1);
            builder3.delete(0,1);
            mOrderData.setSend_price("0");
            mOrderData.setTotal_price(price+"");
            mOrderData.setGoods_total(builder3.toString());
            mOrderData.setGoods_number(builder1.toString());
            mOrderData.setGoods_image(builder2.toString());
            mOrderData.setGoods_id(builder.toString());
            mPrice.setText(Format.formatDecimal(price));
            mTotalprice.setText(Format.formatDecimal(price));
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
    public void placeOrder(OrderData data){
        RequestParams params = new RequestParams(Global.SHOPCARORDER);
        params.addBodyParameter("userid",sp1.getString("userID",""));
        params.addBodyParameter("goods_id",data.getGoods_id());
        params.addBodyParameter("addr",data.getAddr());
        params.addBodyParameter("message","");
        params.addBodyParameter("goods_number",data.getGoods_number());
        params.addBodyParameter("send_price",data.getSend_price());
        params.addBodyParameter("total_price",data.getTotal_price());
        params.addBodyParameter("goods_total",data.getGoods_total());
        params.addBodyParameter("goods_image",data.getGoods_image());
        Log.v(LOG_TAG,"-------->"+sp1.getString("userID","")+"&"+data.getGoods_id()+"&"+"&"+data.getAddr()+"&"+data.getGoods_number()+"&"
                +data.getSend_price()+"&"+data.getTotal_price()+"&"+data.getGoods_total()+"&"+data.getGoods_image());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.v(LOG_TAG,"----------->"+result);
                try {
                    JSONObject object = new JSONObject(result);
                    JSONObject jo = object.getJSONObject("data");
                    String result1 = jo.getString("total_price");
                    String order_number = jo.getString("order_id");
                    Intent intent1 = new Intent(IndentActivity.this, PayActivity.class);
                    intent1.putExtra("total_price",result1);
                    intent1.putExtra("order_id",order_number);
                    startActivityForResult(intent1,1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ToastUtil.showToast(IndentActivity.this,"创建订单失败,请稍后重试");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    public void RequestDefaultAddress(){
        RequestParams params = new RequestParams(Global.GETADDRESS);
        params.addBodyParameter("userid",sp1.getString("userID",""));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.v(LOG_TAG,"--------------->"+result);
                Gson gson = new Gson();
                AddressBean add = gson.fromJson(result,AddressBean.class);
                mList=add.getData();
                for (DataBean databean:mList){
                    if (databean.getStatus().equals("1")){
                        init(databean);
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    @OnClick(R.id.indent_back)
    public void onClick() {
        finish();
    }
}
