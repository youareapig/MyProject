package com.myproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adpter.OrderList_Adapter;
import bean.Order_Bean;
import utils.Global;

public class OrderActivity extends AppCompatActivity {
    private ListView orderListView;
    private TextView order_title;
    private Global global;
    private OrderList_Adapter adapter;
    private String url, userID, urlHalf;
    private SharedPreferences sharedPreferences;
    private List<Order_Bean.DataBean> list;
    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        userID = sharedPreferences.getString("userID", null);
        global = new Global();
        url = global.getUrl() + "api.php/Memberorder/Memorderlst";
        urlHalf = global.getUrl() + "api.php/Memberorder/orderState";
        orderListView = (ListView) findViewById(R.id.order_listview);
        order_title = (TextView) findViewById(R.id.order_title);
        //TODO 设置标题

        String resultTitle = sharedPreferences.getString("title", null);
        order_title.setText(resultTitle);
        String orderType = sharedPreferences.getString("ordertype", null);
        //TODO 查看全部订单
        if (orderType.equals("all")) {
            Log.e("tag","全部");
            getOrder();
        } else if (orderType.equals("0")) {
            Log.e("tag","待付款");
            getHalfOrder("0");

        } else if (orderType.equals("1")) {
            Log.e("tag","待收货");
            getHalfOrder("1");

        } else if (orderType.equals("2")) {
            Log.e("tag","已签收");
            getHalfOrder("2");

        } else if (orderType.equals("4")) {
            Log.e("tag","待评价");
            getHalfOrder("4");

        }

    }

    private void getHalfOrder(String oderTypeTag) {
        RequestParams params = new RequestParams(urlHalf);
        params.addBodyParameter("userid", userID);
        params.addBodyParameter("type", oderTypeTag);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                method(result);
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

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    //TODO 获取当前时间
    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }


    private void getOrder() {
        progressDialog = ProgressDialog.show(this, "请稍后", "获取数据中...", true);
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("userid", userID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                method(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("tag", "订单获取失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                progressDialog.cancel();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    //TODO 请求成功方法体
    private void method(String result) {
        Gson gson = new Gson();
        Order_Bean bean = gson.fromJson(result, Order_Bean.class);
        if (bean.getCode() == 5000) {
            list = bean.getData();
            adapter = new OrderList_Adapter(OrderActivity.this, list);
            orderListView.setAdapter(adapter);
            orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Order_Bean.DataBean bean = (Order_Bean.DataBean) parent.getItemAtPosition(position);
                    Intent intent = new Intent(OrderActivity.this, TransactionActivity.class);
                    intent.putExtra("orderlist_id", bean.getOrderlist_id());
                    startActivityForResult(intent,0);
                }
            });
        } else if (bean.getCode() == -5000) {
            Toast.makeText(OrderActivity.this, "亲，你还没有下单哦！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0&&resultCode==1){
            getOrder();
            adapter.notifyDataSetChanged();
        }
    }
}
