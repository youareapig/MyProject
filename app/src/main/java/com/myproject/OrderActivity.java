package com.myproject;

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
    private String url,userID;
    private SharedPreferences sharedPreferences;
    private List<Order_Bean.DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        userID = sharedPreferences.getString("userID", null);
        global=new Global();
        url=global.getUrl()+"api.php/Memberorder/Memorderlst";
        orderListView= (ListView) findViewById(R.id.order_listview);
        order_title= (TextView) findViewById(R.id.order_title);
        //TODO 设置标题
        Intent intent=this.getIntent();
        String resultTitle=intent.getStringExtra("title");
        order_title.setText(resultTitle);

        getOrder();
    }


    //TODO 获取当前时间
    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }
    private void getOrder(){
        RequestParams params=new RequestParams(url);
        params.addBodyParameter("userid",userID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("tag","订单获取成功："+result);
                Gson gson=new Gson();
                Order_Bean bean=gson.fromJson(result,Order_Bean.class);
                if (bean.getCode()==5000){
                    list=bean.getData();
                    OrderList_Adapter adapter=new OrderList_Adapter(OrderActivity.this,list);
                    orderListView.setAdapter(adapter);
                    orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Order_Bean.DataBean bean= (Order_Bean.DataBean) parent.getItemAtPosition(position);
                            Intent intent=new Intent(OrderActivity.this,TransactionActivity.class);
                            intent.putExtra("orderlist_id",bean.getOrderlist_id());
                            startActivity(intent);
                        }
                    });
                }else if (bean.getCode()==-5000){
                    Toast.makeText(OrderActivity.this,"亲，你还没有下单哦！",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("tag","订单获取失败");
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
}
