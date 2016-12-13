package indexfragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.myproject.IndentActivity;
import com.myproject.R;
import com.nostra13.universalimageloader.utils.L;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.util.List;

import adpter.ShopCarAdapter;
import bean.ShopCarBean;
import bean.ShopCarData;
import utils.Format;
import utils.Global;
import utils.SpaceItemDecoration;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class ShopCar extends Fragment  {
    private RecyclerView mRecyclerView;
    private List<ShopCarBean> shopcar_list;
    private ShopCarBean bean1, bean2, bean3, bean4;
    private CheckBox checkBox_all;
    private ShopCarAdapter mShopCarAdapter;
    private TextView total_sale,clearing;
    private LinearLayout shopcar_compile;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ShopCarAdapter.Callback mCallback;
    private final String LOG_TAG="ShopCar";
    private SharedPreferences sp;
    private ShopCarData carData;
    private boolean isCheck=true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopcar, container, false);
        init(view);
        mCallback = new ShopCarAdapter.Callback() {
            @Override
            public void callBackPrice(double price) {
                total_sale.setText(Format.formatDecimal(price));
            }

            @Override
            public void callBackGoodsID(StringBuilder goodsID) {
                reQuestDeleteShopCar(goodsID);
            }

            @Override
            public void callBackisCheckAll(boolean b) {
                if (!b){
                    isCheck=false;
                }
                if (b){
                    isCheck=true;
                }
                checkBox_all.setChecked(b);
            }
        };
        /**添加适配器*/
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mShopCarAdapter = new ShopCarAdapter(mRecyclerView,mCallback,getActivity());
        int spacing = getResources().getDimensionPixelSize(R.dimen.spacing);//为购物车列表没个Item之间加间隔
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(spacing));
        mRecyclerView.setAdapter(mShopCarAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requestShopCarData();
        //下拉刷新组件，下拉从新进行网络请求
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestShopCarData();
            }
        });
        //全选组件或者取消全选
        checkBox_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b){
                mShopCarAdapter.allChecked();
                mShopCarAdapter.calculatePrice();
            }
            if (!b){
                Log.v(LOG_TAG,"---------------->ischeck"+isCheck);
                if (isCheck){
                    isCheck=true;
                    Log.v(LOG_TAG,"---------------->ischeck"+b);
                    mShopCarAdapter.allcancel();
                    mShopCarAdapter.calculatePrice();
                }
            }
        }
    });

        showDialog();

}
    //结算选中的商品
    public void clearingListener(){
        clearing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShopCarAdapter.clearingCommodity();

            }
        });
    }
    //显示购物车界面编辑对话框
    public void showDialog(){
        shopcar_compile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("确认删除");
                builder.setMessage("是否删除选中的商品？");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mShopCarAdapter.deleteCommodity();
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }
    public void requestShopCarData(){
        RequestParams params = new RequestParams(Global.SHOPCARDATA);
        params.addBodyParameter("userid",sp.getString("userID",""));
        x.http().post(params, new Callback.CacheCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.v(LOG_TAG,"------------->"+result);
                //获取到数据之后需要把之前加载的数据删除
                mShopCarAdapter.removeData();
                total_sale.setText("0.00");
                Gson gson = new Gson();
                carData = gson.fromJson(result,ShopCarData.class);
                mShopCarAdapter.addData(carData.getData());
                if (mSwipeRefreshLayout.isRefreshing()){
                    mSwipeRefreshLayout.setRefreshing(false);
                }
                checkBox_all.setChecked(false);
                clearingListener();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "服务器故障,请稍后重试", Toast.LENGTH_SHORT).show();
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
    public void reQuestDeleteShopCar(StringBuilder goodsIDs){
        RequestParams params = new RequestParams(Global.SHOPCARDELETEDATA);
        params.addBodyParameter("userid",sp.getString("userID",""));
        params.addBodyParameter("goods_id",goodsIDs.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mShopCarAdapter.notifyDataSetChanged();
                total_sale.setText("0.00");
                Toast.makeText(getActivity(), "成功删除", Toast.LENGTH_SHORT).show();
                Log.v(LOG_TAG,"------------>"+result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "删除失败,请稍后重试", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void init(View view){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.shopcar_recyclerview);
        checkBox_all = (CheckBox) view.findViewById(R.id.shopcar_all);
        total_sale = (TextView) view.findViewById(R.id.shopcar_totalsale);
        shopcar_compile = (LinearLayout) view.findViewById(R.id.shopcar_compile);
        clearing= (TextView) view.findViewById(R.id.clearing);
        mSwipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.shopcar_swiperefreshlayout);
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED);
        sp=getActivity().getSharedPreferences("userLogin",Context.MODE_PRIVATE);
    }
}
