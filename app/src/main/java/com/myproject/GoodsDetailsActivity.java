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
import init.Init;
import myview.Goods_details_Pop;
import utils.Global;

public class GoodsDetailsActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private TextView buy, goods_details_introduce, goods_details_price, goods_details_brank, addshopcar;
    private String IMG, resultGoodsid, URL, userID, AddURL;
    private Goods_details_Pop goodsDetailsPop;
    private ViewPager goods_details_viewpage;
    private ImageView[] goods_details_pager_image, tips;
    private ViewGroup viewGroup;
    private List<GoodsDetailsBean.DataBean.PicBean> list;
    private RelativeLayout goods_details_shopcar;
    private ProgressDialog progressDialog = null;
    private GoodsDetailsBean goodsdetailsbean;
    private Global global;
    private Init init;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        global = new Global();
        IMG = global.getUrl();
        URL = IMG + "api.php/Goods/goodsDetail";
        AddURL = IMG + "api.php/Cart/carAdd";
        init = (Init) getApplication();
        initview();
        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //TODO 接受列表界面传过来的商品ID，进行网络请求
        Intent intent = this.getIntent();
        resultGoodsid = intent.getStringExtra("goodsID");
        Log.i("goods", "商品详情ID" + resultGoodsid);
        //TODO 获取用户ID
        userID = sharedPreferences.getString("userID", null);
        Log.i("goods", "用户ID" + userID);
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
        addshopcar = (TextView) findViewById(R.id.addshopcar);
        addshopcar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy:
                //实例化SelectPicPopupWindow
                goodsDetailsPop = new Goods_details_Pop(GoodsDetailsActivity.this);
                //显示窗口
                goodsDetailsPop.showAtLocation(GoodsDetailsActivity.this.findViewById(R.id.buy), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                goodsDetailsPop.setOutsideTouchable(true);

                break;
            case R.id.goods_details_shopcar:
                Intent intent = new Intent(GoodsDetailsActivity.this, MainActivity.class);
                intent.putExtra("fragment_tag", 2);
                startActivity(intent);
                finish();
                break;
            case R.id.addshopcar:
                //TODO 获取状态，判断是否登录 1为登录
                String state = sharedPreferences.getString("state", null);
                try {
                    if (state.equals("1")) {
                        addShopcar();
                    }
                } catch (Exception e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GoodsDetailsActivity.this);
                    builder.setTitle("提示");
                    builder.setMessage("您还未登陆，确认登陆?");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(GoodsDetailsActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    builder.show();
                }

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
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                goodsdetailsbean = gson.fromJson(result, GoodsDetailsBean.class);
                goods_details_introduce.setText(goodsdetailsbean.getData().getGoods_name());
                goods_details_price.setText(goodsdetailsbean.getData().getShop_price());
                goods_details_brank.setText(goodsdetailsbean.getData().getBran_name());
                //TODO 把商品图片和价格设置为全局变量,方便popuwindow获取
                init.setPopImg(goodsdetailsbean.getData().getThumb());
                init.setPopPrice(goodsdetailsbean.getData().getShop_price());

                list = goodsdetailsbean.getData().getPic();
                //点点图片数组
                tips = new ImageView[list.size()];
                for (int i = 0; i < tips.length; i++) {
                    ImageView imageView = new ImageView(GoodsDetailsActivity.this);
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(15, 15));
                    tips[i] = imageView;
                    if (i == 0) {
                        tips[i].setBackgroundResource(R.drawable.viewpage_check);
                    } else {
                        tips[i].setBackgroundResource(R.drawable.viewpage_goods);
                    }
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewPager.LayoutParams.WRAP_CONTENT,
                            ViewPager.LayoutParams.WRAP_CONTENT));
                    layoutParams.leftMargin = 10;
                    layoutParams.rightMargin = 10;
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

    public void addShopcar() {
        RequestParams params = new RequestParams(AddURL);
        params.addBodyParameter("userid", userID);
        params.addBodyParameter("goods_id", resultGoodsid);
        params.addBodyParameter("goods_price", goodsdetailsbean.getData().getShop_price());
        Log.i("add", "参数" + userID + resultGoodsid + goodsdetailsbean.getData().getShop_price());
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("add", "请求成功：" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("add", "请求错误");
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
