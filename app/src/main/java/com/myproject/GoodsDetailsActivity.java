package com.myproject;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adpter.GoodsDetails_Viewpage_Adapter;
import indexfragment.ShopCar;
import myview.Goods_details_Pop;

public class GoodsDetailsActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private TextView buy;
    private Goods_details_Pop goodsDetailsPop;
    private ViewPager goods_details_viewpage;
    private ImageView[] goods_details_pager_image;
    private List<Integer> imageList;
    private RadioGroup radioGroup;
    private RelativeLayout goods_details_shopcar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        init();
        setGoods_details_viewpage();
    }

    public void init() {
        buy = (TextView) findViewById(R.id.buy);
        goods_details_viewpage = (ViewPager) findViewById(R.id.goods_details_viewpager);
        radioGroup = (RadioGroup) findViewById(R.id.goods_group);
        buy.setOnClickListener(this);
        goods_details_viewpage.addOnPageChangeListener(this);
        goods_details_shopcar = (RelativeLayout) findViewById(R.id.goods_details_shopcar);
        goods_details_shopcar.setOnClickListener(this);
    }

    /**
     * 商品详情viewpager
     */
    public void setGoods_details_viewpage() {
        imageList = new ArrayList<>();
        imageList.add(R.mipmap.listview1);
        imageList.add(R.mipmap.listview1);
        imageList.add(R.mipmap.listview1);
        goods_details_pager_image = new ImageView[imageList.size()];
        for (int i = 0; i < imageList.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageList.get(i));
            goods_details_pager_image[i] = imageView;
        }
        goods_details_viewpage.setAdapter(new GoodsDetails_Viewpage_Adapter(goods_details_pager_image));
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
        RadioButton dot = (RadioButton) radioGroup.getChildAt(position);
        dot.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
