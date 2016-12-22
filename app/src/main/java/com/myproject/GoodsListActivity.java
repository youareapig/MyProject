package com.myproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import adpter.GoodsList_ListView_Adapter;
import adpter.Pop_brand_Adapter;
import bean.GoodsList_Bean;
import utils.Global;
import utils.ToastUtil;

public class GoodsListActivity extends AppCompatActivity implements OnClickListener {
    private RelativeLayout goodslist_brand, goods_sale, goods_details_back, serach;
    private PopupWindow popupWindow;
    private ListView goodslist_listview;
    private List<GoodsList_Bean.DataBean> list;
    private List<GoodsList_Bean.BrandBean> brandList;
    private TextView goods_sales, synthesize,noGoods;
    private GoodsList_ListView_Adapter goodsListListViewAdapter;
    private boolean bool = false;
    private GridView pop_grid_brand;
    private String resultGoodsID, resultSearch, groupID, URL;
    private GoodsList_Bean goodsListBean;
    private EditText edit_search;
    private ProgressDialog progressDialog = null;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        Global global = new Global();
        URL = global.getUrl() + "api.php/Goods/goodsLst";
        Intent intent = getIntent();
        //TODO 分类界面传过来的商品ID
        resultGoodsID = intent.getStringExtra("goodsID");
        //TODO 首页搜索传过来的搜索内容
        resultSearch = intent.getStringExtra("seach");
        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        //TODO 获取用户类型ID
        groupID=sharedPreferences.getString("groupID","0");
        //TODO 获取组件
        initview();
        if (resultSearch != null) {
            internetSearch(resultSearch);
            if (resultSearch.equals("1")){
                edit_search.setText("");
            }else {
                edit_search.setText(resultSearch);
            }


        } else {
            internet(resultGoodsID);
        }

        goodslist_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GoodsListActivity.this, GoodsDetailsActivity.class);
                GoodsList_Bean.DataBean dataBean = (GoodsList_Bean.DataBean) parent.getItemAtPosition(position);
                intent.putExtra("goodsID", dataBean.getGoods_id());
                startActivity(intent);
            }
        });

    }
    private void initview(){
        goodslist_brand = (RelativeLayout) findViewById(R.id.goodslist_brand);
        goodslist_listview = (ListView) findViewById(R.id.goodslist_listview);
        goods_sale = (RelativeLayout) findViewById(R.id.goods_sale_v);
        goods_sales = (TextView) findViewById(R.id.goods_sales);
        synthesize = (TextView) findViewById(R.id.synthesize);
        serach = (RelativeLayout) findViewById(R.id.serach);
        edit_search = (EditText) findViewById(R.id.edit_search);
        noGoods= (TextView) findViewById(R.id.activity_goods_list_none);
        goods_details_back = (RelativeLayout) findViewById(R.id.goods_details_back);
        goods_details_back.setOnClickListener(this);
        goodslist_brand.setOnClickListener(this);
        goods_sale.setOnClickListener(this);
        goods_sales.setOnClickListener(this);
        synthesize.setOnClickListener(this);
        serach.setOnClickListener(this);
    }

    /**
     * 价格从小到大
     */
    public void up() {
        try {
            Collections.sort(list, new Comparator<GoodsList_Bean.DataBean>() {


                @Override
                public int compare(GoodsList_Bean.DataBean o1, GoodsList_Bean.DataBean o2) {
                    Log.d("sale", "商品价格：" + o1.getShop_price() + "         " + o2.getShop_price());
                    double sale3 = Double.parseDouble(o1.getShop_price());
                    double sale4 = Double.parseDouble(o2.getShop_price());
                    Log.d("sale", "获取商品价格：" + sale3 + "         " + sale4);
                    return (int) (sale4 - sale3);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 价格从大到小
     */
    public void down() {
        try {
            Collections.sort(list, new Comparator<GoodsList_Bean.DataBean>() {

                @Override
                public int compare(GoodsList_Bean.DataBean o1, GoodsList_Bean.DataBean o2) {
                    double sale1 = Double.parseDouble(o1.getShop_price().trim());
                    double sale2 = Double.parseDouble(o2.getShop_price().trim());
                    return (int) (sale1 - sale2);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 销量排序
     */
    public void sales() {
        try {
            Collections.sort(list, new Comparator<GoodsList_Bean.DataBean>() {

                @Override
                public int compare(GoodsList_Bean.DataBean o1, GoodsList_Bean.DataBean o2) {
                    double sales1 = Double.parseDouble(o1.getSales().trim());
                    double sales2 = Double.parseDouble(o2.getSales().trim());
                    return (int) (sales2 - sales1);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.goodslist_brand:
                if (list!=null){
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                        return;
                    } else {
                        initmPopupWindowView(v);
                        popupWindow.showAsDropDown(v, 0, 5);
                    }
                }else {
                    Log.e("tag","没有商品");
                }

                break;
            case R.id.goods_sale_v:
                if (bool == false) {
                    up();
                    bool = true;
                    Log.d("tag", "ture");
                } else if (bool = true) {
                    down();
                    bool = false;
                    Log.d("tag", "false");
                }
                try {
                    goodsListListViewAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.goods_sales:

                sales();
                try {
                    goodsListListViewAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.goods_details_back:
                finish();
                break;
            //TODO 综合显示该分类下所有的商品
            case R.id.synthesize:
                internetSearch("1");
                //internet(resultGoodsID);
                //goodsListListViewAdapter.notifyDataSetChanged();
                break;
            //TODO 搜索
            case R.id.serach:
                String stringSearch = edit_search.getText().toString().trim();
                if (TextUtils.isEmpty(stringSearch)) {
                    ToastUtil.showToast(GoodsListActivity.this,"请输入关键字");
                    return;
                } else {
                    internetSearch(stringSearch);
                }
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
        pop_grid_brand = (GridView) customView.findViewById(R.id.pop_brand);
        // 创建PopupWindow实例,宽度和高度
        popupWindow = new PopupWindow(customView, 720, 500);
        // 设置动画效果
        // popupWindow.setAnimationStyle(R.style.AnimationFade);
        //点击外部消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        pop_grid_brand.setAdapter(new Pop_brand_Adapter(this, brandList));
        //TODO 选择品牌，请求数据
        pop_grid_brand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GoodsList_Bean.BrandBean brandBean = (GoodsList_Bean.BrandBean) parent.getItemAtPosition(position);
                Log.e("id", "品牌ID：" + brandBean.getBrand_id() + "品牌名称:" + brandBean.getBrand_name());
                internetBrand(brandBean.getBrand_id());
            }
        });
    }

    //TODO 商品列表数据请求
    public void internet(String carID) {
        progressDialog = ProgressDialog.show(GoodsListActivity.this, "请稍后", "玩命加载中....", true);
        RequestParams params = new RequestParams(URL);
        params.addBodyParameter("cat_id", carID);
        params.addBodyParameter("groupid",groupID);
        Log.e("tag","车子ID"+carID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("tag", "分类" + result);
                cache(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ToastUtil.showToast(GoodsListActivity.this,"网络请求错误");
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
                cache(result);
                return true;
            }
        });
    }

    public void cache(String result) {
        Gson gson = new Gson();
        goodsListBean = gson.fromJson(result, GoodsList_Bean.class);
        list = goodsListBean.getData();
        Log.d("test", "接受数据:" + result);
        if (goodsListBean.getCode()==1000){
            noGoods.setVisibility(View.GONE);
            goodslist_listview.setVisibility(View.VISIBLE);
            goodsListListViewAdapter = new GoodsList_ListView_Adapter(GoodsListActivity.this, list);
            goodslist_listview.setAdapter(goodsListListViewAdapter);
            Log.d("test", "商品介绍:" + list.get(0).getGoods_name());
            brandList = goodsListBean.getBrand();
        }else {
            noGoods.setVisibility(View.VISIBLE);
            goodslist_listview.setVisibility(View.GONE);
        }


    }

    public void internetBrand(String brandID) {
        RequestParams params = new RequestParams(URL);
        params.addBodyParameter("brand_id", brandID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("tag", "品牌" + result);
                //TODO 通过品牌适配数据
                Gson gson = new Gson();
                goodsListBean = gson.fromJson(result, GoodsList_Bean.class);
                list = goodsListBean.getData();
                if (goodsListBean.getCode() == 1000) {
                    noGoods.setVisibility(View.GONE);
                    goodslist_listview.setVisibility(View.VISIBLE);
                    goodsListListViewAdapter = new GoodsList_ListView_Adapter(GoodsListActivity.this, list);
                    goodslist_listview.setAdapter(goodsListListViewAdapter);
                    goodsListListViewAdapter.notifyDataSetChanged();
                    popupWindow.dismiss();
                } else {
                    noGoods.setVisibility(View.VISIBLE);
                    noGoods.setText("该商品已售罄，敬请期待上架！");
                    goodslist_listview.setVisibility(View.GONE);
                    popupWindow.dismiss();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ToastUtil.showToast(GoodsListActivity.this,"网络请求错误");
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

    public void internetSearch(String word) {
        progressDialog = ProgressDialog.show(GoodsListActivity.this, "请稍后", "玩命加载中....", true);
        RequestParams params = new RequestParams(URL);
        params.addBodyParameter("keword", word);
        params.addBodyParameter("groupid",groupID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("tag", "搜索" + result);
                Gson gson = new Gson();
                goodsListBean = gson.fromJson(result, GoodsList_Bean.class);
                if (goodsListBean.getCode() == 1000) {
                    noGoods.setVisibility(View.GONE);
                    goodslist_listview.setVisibility(View.VISIBLE);
                    list = goodsListBean.getData();
                    goodsListListViewAdapter = new GoodsList_ListView_Adapter(GoodsListActivity.this, list);
                    goodslist_listview.setAdapter(goodsListListViewAdapter);
                    brandList = goodsListBean.getBrand();
                    goodsListListViewAdapter.notifyDataSetChanged();
                } else {
                    noGoods.setText("抱歉，没有符合要求的商品！");
                    noGoods.setVisibility(View.VISIBLE);
                    goodslist_listview.setVisibility(View.GONE);
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ToastUtil.showToast(GoodsListActivity.this,"网络请求错误");
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