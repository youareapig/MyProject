package com.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import adpter.SalesGridVIewAdapter;
import bean.SalesBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import utils.Global;

public class SalesActivity extends AppCompatActivity {
    @BindView(R.id.salesback)
    RelativeLayout salesback;
    @BindView(R.id.salesgridview)
    GridView salesgridview;
    @BindView(R.id.activity_banner)
    ImageView activityBanner;
    @BindView(R.id.activity_halftitle)
    TextView activityHalftitle;
    private Unbinder unbinder;
    private String goodsActivityID, activityUrl;
    private Global global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        goodsActivityID = intent.getStringExtra("activeid");
        global = new Global();
        activityUrl = global.getUrl() + "api.php/Index/ActiveLst";
        Log.e("tag", "-------->" + goodsActivityID);
        visit();
        salesback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();

    }

    private void visit() {
        RequestParams params = new RequestParams(activityUrl);
        params.addBodyParameter("active_id", goodsActivityID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("tag", "请求成功-------->" + result);
                Gson gson = new Gson();
                SalesBean bean = gson.fromJson(result, SalesBean.class);
                List<SalesBean.DataBean.InfoBean> list = bean.getData().getInfo();
                ImageLoader.getInstance().displayImage(global.getUrl() + bean.getData().getTitle().getActive_logo(), activityBanner);
                activityHalftitle.setText(bean.getData().getTitle().getActive_name());
                salesgridview.setAdapter(new SalesGridVIewAdapter(SalesActivity.this, list));
                salesgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SalesBean.DataBean.InfoBean infoBean= (SalesBean.DataBean.InfoBean) parent.getItemAtPosition(position);
                        Intent intent=new Intent(SalesActivity.this,GoodsDetailsActivity.class);
                        intent.putExtra("goodsID",infoBean.getGoods_id());
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("tag", "-------->请求错误");
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
