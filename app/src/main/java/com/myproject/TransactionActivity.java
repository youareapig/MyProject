package com.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import bean.OrderDetailsBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import utils.Global;

public class TransactionActivity extends AppCompatActivity {
    @BindView(R.id.transaction_id)
    TextView transactionId;
    @BindView(R.id.order_item_tag)
    TextView orderItemTag;
    @BindView(R.id.order_item_name)
    TextView orderItemName;
    @BindView(R.id.order_item_phone)
    TextView orderItemPhone;
    @BindView(R.id.order_item_address)
    TextView orderItemAddress;
    @BindView(R.id.transaction_img)
    ImageView transactionImg;
    @BindView(R.id.order_item_goodsimg)
    TextView orderItemGoodsimg;
    @BindView(R.id.order_item_number)
    TextView orderItemNumber;
    @BindView(R.id.order_item_price)
    TextView orderItemPrice;
    @BindView(R.id.transaction_sale)
    TextView transactionSale;
    @BindView(R.id.order_item_pinglun)
    TextView orderItemPinglun;
    @BindView(R.id.order_item_delete)
    TextView orderItemDelete;
    private String url, orderlist_id;
    private Global global;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        unbinder = ButterKnife.bind(this);
        Intent intent = this.getIntent();
        orderlist_id = intent.getStringExtra("orderlist_id");
        global = new Global();
        url = global.getUrl() + "api.php/Memberorder/Memberorderdetail";
        orderDetail();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    private void orderDetail() {
        Log.i("tag", "订单详情编号：" + orderlist_id);
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("orderlist_id", orderlist_id);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("tag", "订单详情成功：" + result);
                Gson gson = new Gson();
                OrderDetailsBean bean = gson.fromJson(result, OrderDetailsBean.class);
                String isPay = bean.getData().getIs_pay();
                if (bean.getCode() == 5000) {
                    transactionId.setText(bean.getData().getOrder_goods_id());
                    orderItemName.setText(bean.getData().getConsignee());
                    orderItemPhone.setText(bean.getData().getTel());
                    orderItemAddress.setText(bean.getData().getAddress());
                    orderItemGoodsimg.setText(bean.getData().getGoods_name());
                    orderItemPrice.setText(bean.getData().getGoods_price());
                    orderItemNumber.setText(bean.getData().getGoods_count());
                    transactionSale.setText(bean.getData().getGoods_total());
                    ImageLoader.getInstance().displayImage(global.getUrl() + bean.getData().getGoods_image(), transactionImg);
                    if (isPay.equals("0")) {
                        orderItemTag.setText("未支付");
                    } else {
                        if (isPay.equals("0")) {
                            orderItemTag.setText("未发货");
                        } else if (isPay.equals("1")) {
                            orderItemTag.setText("已发货");
                        } else if (isPay.equals("2")) {
                            orderItemTag.setText("已收货");
                        }
                    }

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("tag", "订单详情失败");
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
