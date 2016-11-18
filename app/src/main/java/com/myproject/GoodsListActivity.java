package com.myproject;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import adpter.GoodsList_ListView_Adapter;
import adpter.Pop_brand_Adapter;
import bean.GoodsList_Bean;

public class GoodsListActivity extends AppCompatActivity implements OnClickListener {
    private RelativeLayout goodslist_brand,goods_sale,goods_details_back;
    private PopupWindow popupWindow;
    private ListView goodslist_listview;
    private List<GoodsList_Bean.DataBean> list;
    private List<String> pop_list;
    private TextView  goods_sales;
    private GoodsList_ListView_Adapter goodsListListViewAdapter;
    private boolean bool = false;
    private GridView pop_grid_brand;
    private static final String URL="http://192.168.0.108/api.php/Goods/goodsLst";
    private String resultGoodsID;
    private GoodsList_Bean goodsListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        Intent intent=getIntent();
        resultGoodsID=intent.getStringExtra("goodsID");
        Log.d("test","接受的商品ID："+resultGoodsID);
        goodslist_brand = (RelativeLayout) findViewById(R.id.goodslist_brand);
        goodslist_listview = (ListView) findViewById(R.id.goodslist_listview);
        goods_sale = (RelativeLayout) findViewById(R.id.goods_sale_v);
        goods_sales = (TextView) findViewById(R.id.goods_sales);
        goods_details_back= (RelativeLayout) findViewById(R.id.goods_details_back);
        goods_details_back.setOnClickListener(this);
        goodslist_brand.setOnClickListener(this);
        goods_sale.setOnClickListener(this);
        goods_sales.setOnClickListener(this);
        internet(resultGoodsID);

        goodslist_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GoodsListActivity.this, GoodsDetailsActivity.class);
                startActivity(intent);
            }
        });
        /**品牌选择*/
        pop_list=new ArrayList<>();
        pop_list.add("龟牌1");
        pop_list.add("龟牌2");
        pop_list.add("龟牌3");
        pop_list.add("龟牌4");
        pop_list.add("龟牌5");
        pop_list.add("龟牌6");
        pop_list.add("龟牌7");
        pop_list.add("龟牌8");
        pop_list.add("龟牌9");
        pop_list.add("龟牌10");
        pop_list.add("龟牌11");
        pop_list.add("龟牌12");
        pop_list.add("龟牌13");
        pop_list.add("龟牌14");
        pop_list.add("龟牌15");
        pop_list.add("龟牌16");
        pop_list.add("龟牌17");
    }

    /**
     * 价格从小到大
     */
    public void up() {
        Collections.sort(list, new Comparator<GoodsList_Bean.DataBean>() {


            @Override
            public int compare(GoodsList_Bean.DataBean o1, GoodsList_Bean.DataBean o2) {
                int sale3= (int) o1.getSales();
                int sale4= (int) o2.getSales();
                return sale4-sale3;
            }
        });


    }

    /**
     * 价格从大到小
     */
    public void down() {

            Collections.sort(list, new Comparator<GoodsList_Bean.DataBean>() {

                @Override
                public int compare(GoodsList_Bean.DataBean o1, GoodsList_Bean.DataBean o2) {
                    int sale1= (int) o1.getSales();
                    int sale2= (int) o2.getSales();
                    return  sale1-sale2;
                }
            });




    }

    /**
     * 销量排序
     */
//    public void sales() {
//        Collections.sort(goodslist_list, new Comparator<GoodsList_Bean>() {
//            @Override
//            public int compare(GoodsList_Bean o1, GoodsList_Bean o2) {
//                return (o2.getGoodslist_person() - o1.getGoodslist_person());
//            }
//        });
//    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.goodslist_brand:
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    return;
                } else {
                    initmPopupWindowView(v);
                    popupWindow.showAsDropDown(v, 0, 5);
                }
                break;
            case R.id.goods_sale_v:
                if (bool == false) {
                    up();
                    bool = true;
                Log.d("tag","ture");
                } else if (bool = true) {
                    down();
                    bool = false;
                    Log.d("tag","false");
                }

                goodsListListViewAdapter.notifyDataSetChanged();
                break;
//            case R.id.goods_sales:
//
//                sales();
//                goodsListListViewAdapter.notifyDataSetChanged();
//                break;
            case R.id.goods_details_back:
                finish();
                break;

        }
    }

    /**
     * PopupWindow品牌选择
     */
    public void initmPopupWindowView(View v) {

        // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.goodslist_brand_pop,
                null, false);
        pop_grid_brand= (GridView) customView.findViewById(R.id.pop_brand);
        // 创建PopupWindow实例,宽度和高度
        popupWindow = new PopupWindow(customView, 720, 500);
        // 设置动画效果
       // popupWindow.setAnimationStyle(R.style.AnimationFade);
       //点击外部消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        pop_grid_brand.setAdapter(new Pop_brand_Adapter(this,pop_list));

    }
    public void internet(String carID){
        RequestParams params=new RequestParams(URL);
        params.addBodyParameter("cat_id",carID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("test","result:"+result);
                Gson gson=new Gson();
                goodsListBean=gson.fromJson(result,GoodsList_Bean.class);
                list=goodsListBean.getData();
                goodsListListViewAdapter = new GoodsList_ListView_Adapter(GoodsListActivity.this,list);
                goodslist_listview.setAdapter(goodsListListViewAdapter);
                Log.d("test","商品介绍:"+list.get(0).getGoods_name());

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("test","访问服务器出错");
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