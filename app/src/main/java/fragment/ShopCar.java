package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myproject.R;

import java.util.ArrayList;
import java.util.List;

import adpter.ShopCar_ListView_Adapter;
import bean.ShopCar_ListBean;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class ShopCar extends Fragment implements ShopCar_ListView_Adapter.OnCountChangeListener, ShopCar_ListView_Adapter.OnItemCheckedChangeListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    private ListView shopcar_listview;
    private List<ShopCar_ListBean> shopcar_list;
    private ShopCar_ListBean bean1, bean2, bean3, bean4;
    private CheckBox checkBox_all, checkBox_compile;
    private ShopCar_ListView_Adapter shopCar_listView_adapter;
    private TextView total_sale, title_compile, shopcar_delete;
    private LinearLayout shopcar_compile;
    private RelativeLayout view_total, view_delete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopcar, container, false);
        shopcar_listview = (ListView) view.findViewById(R.id.shopcar_listview);
        checkBox_all = (CheckBox) view.findViewById(R.id.shopcar_all);
        checkBox_all.setOnCheckedChangeListener(this);
        total_sale = (TextView) view.findViewById(R.id.shopcar_totalsale);
        shopcar_compile = (LinearLayout) view.findViewById(R.id.shopcar_compile);
        view_total = (RelativeLayout) view.findViewById(R.id.view_total);
        view_delete = (RelativeLayout) view.findViewById(R.id.view_delete);
        title_compile = (TextView) view.findViewById(R.id.title_compile);
        checkBox_compile = (CheckBox) view.findViewById(R.id.shopcar_all_compile);
        checkBox_compile.setOnCheckedChangeListener(this);
        shopcar_delete = (TextView) view.findViewById(R.id.text_delete);
        shopcar_delete.setOnClickListener(this);
        /**编辑*/
        shopcar_compile.setOnClickListener(new View.OnClickListener() {
            boolean bool = false;

            @Override
            public void onClick(View v) {
                if (bool == false) {
                    view_total.setVisibility(View.GONE);
                    view_delete.setVisibility(View.VISIBLE);
                    title_compile.setText("完成");
                    bool = true;
                } else {
                    view_total.setVisibility(View.VISIBLE);
                    view_delete.setVisibility(View.GONE);
                    title_compile.setText("编辑");
                    bool = false;
                }

            }
        });

        /**购物车数据*/
        shopcar_list = new ArrayList<>();
        bean1 = new ShopCar_ListBean("米其林轮胎", "200.00", R.mipmap.shopcar_01);
        bean2 = new ShopCar_ListBean("米其林轮胎", "200.00", R.mipmap.shopcar_01);
        bean3 = new ShopCar_ListBean("米其林轮胎", "200.03", R.mipmap.shopcar_01);
        bean4 = new ShopCar_ListBean("米其林轮胎", "200.08", R.mipmap.shopcar_01);
        shopcar_list.add(bean1);
        shopcar_list.add(bean2);
        shopcar_list.add(bean3);
        shopcar_list.add(bean4);
        /**添加适配器*/
        shopCar_listView_adapter = new ShopCar_ListView_Adapter(getActivity(), this, shopcar_list);
        shopcar_listview.setAdapter(shopCar_listView_adapter);
        return view;
    }

    /**
     * 实现adapter接口
     */
    @Override
    public void onCountChangeListener(int count, int position) {
        ShopCar_ListBean shopCar_listBean = shopcar_list.get(position);
        shopCar_listBean.setEt_count(count);
        shopCar_listView_adapter.setlistGoodsBean(shopcar_list);
        float totalPrize = getTotalPrize();
        total_sale.setText(totalPrize + "");
    }

    @Override
    public void onItemCheckedChangeListener(boolean isCheck, int position) {
        ShopCar_ListBean shopCar_listBean = shopcar_list.get(position);
        shopCar_listBean.setIsCheck(isCheck);
        shopCar_listView_adapter.setlistGoodsBean(shopcar_list);

        boolean tag = isAllChecked(shopcar_list);
        checkBox_all.setOnCheckedChangeListener(null);
        checkBox_all.setChecked(tag);
        checkBox_all.setOnCheckedChangeListener(this);

        checkBox_compile.setOnCheckedChangeListener(null);
        checkBox_compile.setChecked(tag);
        checkBox_compile.setOnCheckedChangeListener(this);
        float totalPrize = getTotalPrize();
        total_sale.setText(totalPrize + "");
    }

    /**
     * 判断是否全选
     */
    public boolean isAllChecked(List<ShopCar_ListBean> shopcar_list) {
        for (int i = 0; i < shopcar_list.size(); i++) {
            if (shopcar_list.get(i).getIsCheck()) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算价格
     */
    public float getTotalPrize() {
        float totalPrize = 0;
        for (int i = 0; i < shopcar_list.size(); i++) {
            ShopCar_ListBean shopCar_listBean = shopcar_list.get(i);
            boolean isCheck = shopCar_listBean.getIsCheck();
            if (isCheck) {
                Log.d("tag", shopCar_listBean.getPrize() + "*******************");
                Log.d("tag", shopCar_listBean.getShopcar_name() + "*******************");
                totalPrize += Float.parseFloat(shopCar_listBean.getPrize()) * (shopCar_listBean.getEt_count());

            }
        }
        return totalPrize;
    }

    /**
     * 全选事件
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.shopcar_all:
                for (int i = 0; i < shopcar_list.size(); i++) {
                    shopcar_list.get(i).setIsCheck(isChecked);
                    Log.i("tag", isChecked + "" + buttonView + "............");
                }
                shopCar_listView_adapter.setlistGoodsBean(shopcar_list);
                float totalPrize = getTotalPrize();
                total_sale.setText(totalPrize + "");
                break;
            case R.id.shopcar_all_compile:
                for (int i = 0; i < shopcar_list.size(); i++) {
                    shopcar_list.get(i).setIsCheck(isChecked);
                    Log.i("tag", isChecked + "" + buttonView + "............");
                }
                shopCar_listView_adapter.setlistGoodsBean(shopcar_list);
                break;

        }

    }

    /**
     * 删除，支付，收藏
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_delete:
                for (int i=0;i<shopcar_list.size();i++){
                    ShopCar_ListBean shopCar_listBean=shopcar_list.get(i);
                    boolean isCheck = shopCar_listBean.getIsCheck();
                    if (isCheck){
                        shopcar_list.remove(shopCar_listBean);
                        i--;
                        /**更新数据*/
                        shopCar_listView_adapter.setlistGoodsBean(shopcar_list);
                        total_sale.setText("0");
                    }
                }

                break;
        }
    }
}
