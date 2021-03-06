package com.myproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import adpter.ManageAddress_Adapter;
import bean.AddressBean;
import bean.DataBean;
import utils.Global;
import utils.ToastUtil;

public class ManageAddressActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout addsite, manageaddress_back;
    private SharedPreferences sharedPreferences;
    private String userID;
    private Global global;
    private String AddressUrl;
    private List<DataBean> mlist;
    private ListView manageaddress_listview;
    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_address);
        //TODO 获取用户ID
        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        userID = sharedPreferences.getString("userID", null);
        global = new Global();
        AddressUrl = global.getUrl() + "api.php/Member/memberLstaddress";
        addsite = (RelativeLayout) findViewById(R.id.addsite);
        addsite.setOnClickListener(this);
        manageaddress_back = (RelativeLayout) findViewById(R.id.manageaddress_back);
        manageaddress_back.setOnClickListener(this);
        manageaddress_listview = (ListView) findViewById(R.id.manageaddress_listview);
        getAddress();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addsite:
                Intent intent = new Intent(ManageAddressActivity.this, CompileAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.manageaddress_back:
                finish();
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getAddress();
    }

    private void getAddress() {
        progressDialog = ProgressDialog.show(this, "请稍后", "获取数据中...", true);
        RequestParams params = new RequestParams(AddressUrl);
        params.addBodyParameter("userid", userID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("abc", "请求成功" + result);
                Gson gson = new Gson();
                AddressBean addressBean = gson.fromJson(result, AddressBean.class);
                mlist = addressBean.getData();
                ManageAddress_Adapter adapter = new ManageAddress_Adapter(ManageAddressActivity.this, mlist);
                if (addressBean.getCode() == 3000) {
                    manageaddress_listview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else if (addressBean.getCode() == -3000) {
                    ToastUtil.showToast(ManageAddressActivity.this,"亲，你还没有添加收货地址哟");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("abc", "请求失败");
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
}
