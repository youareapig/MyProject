package com.myproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import adpter.GoodsDetails_Viewpage_Adapter;
import bean.GoodsDetailsBean;
import indexfragment.ShopCar;
import myview.Goods_details_Pop;
import utils.MyUrl;

public class GoodsDetailsActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private TextView buy,goods_details_introduce,goods_details_price,goods_details_brank;
    private  String IMG;
    private Goods_details_Pop goodsDetailsPop;
    private ViewPager goods_details_viewpage;
    private ImageView[] goods_details_pager_image;
    private ImageView[] tips;
    private ViewGroup viewGroup;
    private List<GoodsDetailsBean.DataBean.PicBean> list;
    private RelativeLayout goods_details_shopcar;
    private String resultGoodsid;
    private ProgressDialog progressDialog = null;
    private String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        MyUrl myUrl=new MyUrl();
        IMG=myUrl.getUrl();
        URL=IMG+"api.php/Goods/goodsDetail";
        init();
        Intent intent=this.getIntent();
        resultGoodsid=intent.getStringExtra("goodsID");
        Log.i("goods","商品详情ID"+resultGoodsid);
        internet(resultGoodsid);
        //setGoods_details_viewpage();
    }

    public void init() {
        buy = (TextView) findViewById(R.id.buy);
        goods_details_viewpage = (ViewPager) findViewById(R.id.goods_details_viewpager);
        viewGroup= (ViewGroup) findViewById(R.id.viewGroup);
        buy.setOnClickListener(this);
        goods_details_viewpage.addOnPageChangeListener(this);
        goods_details_shopcar = (RelativeLayout) findViewById(R.id.goods_details_shopcar);
        goods_details_shopcar.setOnClickListener(this);
        goods_details_introduce= (TextView) findViewById(R.id.goods_details_introduce);
        goods_details_price= (TextView) findViewById(R.id.goods_details_price);
        goods_details_brank= (TextView) findViewById(R.id.goods_details_brank);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy:
                //实例化SelectPicPopupWindow
                goodsDetailsPop = new Goods_details_Pop(GoodsDetailsActivity.this);
                //显示窗口
                goodsDetailsPop.showAtLocation(GoodsDetailsActivity.this.findViewById(R.id.buy), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                goodsDetailsPop.setOutsideTouchable(false);
                break;
            case R.id.goods_details_shopcar:
                Log.d("tag", "加入购物车");
                Intent intent = new Intent(GoodsDetailsActivity.this, MainActivity.class);
                intent.putExtra("fragment_tag", 2);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setImageBackground(position%goods_details_pager_image.length);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    public void internet(String goodsId){
        progressDialog = ProgressDialog.show(GoodsDetailsActivity.this, "请稍后", "玩命加载中....", true);
        RequestParams params=new RequestParams(URL);
        params.addBodyParameter("goods_id",goodsId);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                GoodsDetailsBean goodsdetailsbean=gson.fromJson(result,GoodsDetailsBean.class);
                goods_details_introduce.setText(goodsdetailsbean.getData().getGoods_name());
                goods_details_price.setText(goodsdetailsbean.getData().getShop_price());
                goods_details_brank.setText(goodsdetailsbean.getData().getBran_name());
                list=goodsdetailsbean.getData().getPic();
                //点点图片数组
                tips=new ImageView[list.size()];
                for (int i=0;i<tips.length;i++){
                    ImageView imageView = new ImageView(GoodsDetailsActivity.this);
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(20,20));
                    tips[i] = imageView;
                    if(i == 0){
                        tips[i].setBackgroundResource(R.drawable.viewpage_check);
                    }else{
                        tips[i].setBackgroundResource(R.drawable.viewpage_goods);
                    }
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewPager.LayoutParams.WRAP_CONTENT,
                            ViewPager.LayoutParams.WRAP_CONTENT));
                    layoutParams.leftMargin = 20;
                    layoutParams.rightMargin = 20;
                    viewGroup.addView(imageView);
                }
                //图片数组
                goods_details_pager_image=new ImageView[list.size()];
                for(int i=0; i<goods_details_pager_image.length; i++){
                    ImageView imageView = new ImageView(GoodsDetailsActivity.this);
                    goods_details_pager_image[i] = imageView;
                    ImageLoader.getInstance().displayImage(IMG+list.get(i).getImg_url(),imageView);
                }

                goods_details_viewpage.setAdapter(new GoodsDetails_Viewpage_Adapter(goods_details_pager_image));
                goods_details_viewpage.setOnPageChangeListener(GoodsDetailsActivity.this);
                goods_details_viewpage.setCurrentItem((goods_details_pager_image.length) * 100);
                Log.i("goods",result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("goods","请求错误");
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
    private void setImageBackground(int selectItems){
        for(int i=0; i<tips.length; i++){
            if(i == selectItems){
                tips[i].setBackgroundResource(R.drawable.viewpage_check);
            }else{
                tips[i].setBackgroundResource(R.drawable.viewpage_goods);
            }
        }
    }
}
