package com.myproject;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import adpter.GoodsDetails_Viewpage_Adapter;
import bean.GoodsDetailsBean;
import myview.CustomDialog;
import myview.GoodsDetailAdd;
import myview.Goods_details_Pop;
import utils.Global;

public class GoodsDetailsActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private TextView buy, goods_details_introduce, goods_details_price, goods_details_brank, addshopcar,goods_details_salenum;
    private String IMG, resultGoodsid, URL, userID,state,groupID;
    private Goods_details_Pop goodsDetailsPop;
    private ViewPager goods_details_viewpage;
    private ImageView[] goods_details_pager_image, tips;
    private ViewGroup viewGroup;
    private WebView mWebView;
    private DialogInterface mDialogInterface;
    private List<GoodsDetailsBean.DataBean.PicBean> list;
    private RelativeLayout goods_details_shopcar,goods_details_back;
    private ProgressDialog progressDialog = null;
    private GoodsDetailsBean goodsdetailsbean;
    private Global global;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String LOG_TAG = "GoodsDetailsActivity";
    private GoodsDetailsBean.DataBean mDataBean;
    private GoodsDetailAdd mGoodsDetailAdd;
    private Goods_details_Pop.DissPupWindw mDisMiss = new Goods_details_Pop.DissPupWindw() {
        @Override
        public void dismiss() {
            if (goodsDetailsPop != null) {
                goodsDetailsPop.dismiss();
            }
        }
    };
    private GoodsDetailAdd.DissPupWindw dissPupWindw = new GoodsDetailAdd.DissPupWindw() {
        @Override
        public void dismiss() {
            if (mGoodsDetailAdd != null) {
                mGoodsDetailAdd.dismiss();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        global = new Global();
        IMG = global.getUrl();
        URL = IMG + "api.php/Goods/goodsDetail";
        initview();
        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //TODO 接受列表界面传过来的商品ID，进行网络请求
        Intent intent = this.getIntent();
        resultGoodsid = intent.getStringExtra("goodsID");
        Log.i("goods", "商品详情ID" + resultGoodsid);
        //TODO 获取用户ID
        userID = sharedPreferences.getString("userID", null);
        groupID=sharedPreferences.getString("groupID","0");
        Log.e("tag", "用户ID---------->" + userID+"类型ID："+groupID);
        internet(resultGoodsid);

    }

    public void initview() {
        buy = (TextView) findViewById(R.id.buy);
        goods_details_viewpage = (ViewPager) findViewById(R.id.goods_details_viewpager);
        viewGroup = (ViewGroup) findViewById(R.id.viewGroup);
        buy.setOnClickListener(this);
        goods_details_viewpage.addOnPageChangeListener(this);
        goods_details_shopcar = (RelativeLayout) findViewById(R.id.goods_details_shopcar);
        goods_details_shopcar.setOnClickListener(this);
        goods_details_introduce = (TextView) findViewById(R.id.goods_details_introduce);
        goods_details_price = (TextView) findViewById(R.id.goods_details_price);
        goods_details_brank = (TextView) findViewById(R.id.goods_details_brank);
        goods_details_back= (RelativeLayout) findViewById(R.id.goods_details_back);
        goods_details_back.setOnClickListener(this);
        addshopcar = (TextView) findViewById(R.id.addshopcar);
        goods_details_salenum= (TextView) findViewById(R.id.goods_details_salenum);
        mWebView = (WebView) findViewById(R.id.activity_goods_detail_webview);
        addshopcar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //TODO 获取状态，判断是否登录 1为登录
        state = sharedPreferences.getString("state", null);
        switch (v.getId()) {
            case R.id.buy:
                try {
                    if (state.equals("1")){
                        //实例化SelectPicPopupWindow
                        goodsDetailsPop = new Goods_details_Pop(GoodsDetailsActivity.this, mDisMiss, mDataBean);
                        //显示窗口
                        goodsDetailsPop.showAtLocation(GoodsDetailsActivity.this.findViewById(R.id.buy), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                        goodsDetailsPop.setOutsideTouchable(true);
                    }else if (state.equals("2")){
                        hintLogin();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    hintLogin();
                }

                break;
            case R.id.goods_details_shopcar:
                try {
                    if (state.equals("1")){
                        Intent intent = new Intent(GoodsDetailsActivity.this, MainActivity.class);
                        intent.putExtra("fragment_tag", 2);
                        startActivity(intent);
                        finish();
                    }else if (state.equals("2")){
                        hintLogin();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    hintLogin();
                }

                break;
            case R.id.addshopcar:

                try {
                    if (state.equals("1")) {
                        mGoodsDetailAdd = new GoodsDetailAdd(GoodsDetailsActivity.this, dissPupWindw, mDataBean, userID);
                        //显示窗口
                        mGoodsDetailAdd.showAtLocation(GoodsDetailsActivity.this.findViewById(R.id.addshopcar), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                        mGoodsDetailAdd.setOutsideTouchable(true);
                    }else if (state.equals("2")){
                        hintLogin();
                    }
                } catch (Exception e) {
                   e.printStackTrace();
                    hintLogin();
                }

                break;
            case R.id.goods_details_back:
                finish();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setImageBackground(position % goods_details_pager_image.length);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void internet(String goodsId) {
        progressDialog = ProgressDialog.show(GoodsDetailsActivity.this, "请稍后", "玩命加载中....", true);
        RequestParams params = new RequestParams(URL);
        params.addBodyParameter("goods_id", goodsId);
        params.addBodyParameter("groupid",groupID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                goodsdetailsbean = gson.fromJson(result, GoodsDetailsBean.class);
                Log.i("tag", result);
                Log.i("tag", "HTML地址：" + goodsdetailsbean.getData().getGoods_desc());
                goods_details_introduce.setText(goodsdetailsbean.getData().getGoods_name());
                goods_details_price.setText(goodsdetailsbean.getData().getShop_price());
                goods_details_brank.setText(goodsdetailsbean.getData().getBran_name());
                goods_details_salenum.setText(goodsdetailsbean.getData().getSales());
                mDataBean = goodsdetailsbean.getData();
                list = goodsdetailsbean.getData().getPic();
                //点点图片数组
                tips = new ImageView[list.size()];
                for (int i = 0; i < tips.length; i++) {
                    ImageView imageView = new ImageView(GoodsDetailsActivity.this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(15,15);
                    layoutParams.leftMargin = 10;
                    layoutParams.rightMargin = 10;
                    imageView.setLayoutParams(layoutParams);
                    tips[i] = imageView;
                    if (i == 0) {
                        tips[i].setBackgroundResource(R.drawable.viewpage_check);
                    } else {
                        tips[i].setBackgroundResource(R.drawable.viewpage_goods);
                    }

                    viewGroup.addView(imageView);
                }
                //图片数组
                goods_details_pager_image = new ImageView[list.size()];
                for (int i = 0; i < goods_details_pager_image.length; i++) {
                    ImageView imageView = new ImageView(GoodsDetailsActivity.this);
                    goods_details_pager_image[i] = imageView;
                    ImageLoader.getInstance().displayImage(IMG + list.get(i).getImg_url(), imageView);
                }

                goods_details_viewpage.setAdapter(new GoodsDetails_Viewpage_Adapter(goods_details_pager_image));
                goods_details_viewpage.setOnPageChangeListener(GoodsDetailsActivity.this);
                goods_details_viewpage.setCurrentItem((goods_details_pager_image.length) * 100);
                //Log.i("goods", result);
                mWebView.loadUrl(global.getUrl()+goodsdetailsbean.getData().getGoods_desc());
                mWebView.setWebViewClient(new WebViewClient());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("goods", "请求错误");
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

    //TODO 切换点点
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.drawable.viewpage_check);
            } else {
                tips[i].setBackgroundResource(R.drawable.viewpage_goods);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mDialogInterface.dismiss();
    }

    private void hintLogin() {
        CustomDialog.Builder builder = new CustomDialog.Builder(GoodsDetailsActivity.this);
        builder.setTitle("提示");
        builder.setMessage("您还未登陆，确认登陆?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDialogInterface=dialog;
                Intent intent = new Intent(GoodsDetailsActivity.this, LoginActivity.class);
                intent.putExtra("login",true);
                startActivityForResult(intent,4);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


}
