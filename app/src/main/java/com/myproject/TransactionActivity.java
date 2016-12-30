package com.myproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import bean.OrderDetailsBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.order_back1)
    RelativeLayout orderBack1;
    private String url, orderlist_id, delteUrl, orderID;
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
        delteUrl = global.getUrl() + "api.php/Memberorder/delorderlist";
        orderDetail();

    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    private void orderDetail() {
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("orderlist_id", orderlist_id);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("tag", "订单详情成功：" + result);
                Gson gson = new Gson();
                OrderDetailsBean bean = gson.fromJson(result, OrderDetailsBean.class);
                orderID = bean.getData().getOrderlist_id();
                String isPay = bean.getData().getState();
                if (bean.getCode() == 5000) {

                    if (isPay.equals("0")) {
                        orderItemTag.setText("未支付");
                    } else {
                        if (isPay.equals("0")) {
                            orderItemTag.setText("未发货");
                        } else if (isPay.equals("1")) {
                            orderItemTag.setText("已发货");
                        } else if (isPay.equals("2")) {
                            orderItemTag.setText("已收货");
                        } else if (isPay.equals("3")) {
                            orderItemTag.setText("交易成功");
                        } else if (isPay.equals("4")) {
                            orderItemTag.setText("未评论");
                        } else if (isPay.equals("5")) {
                            orderItemTag.setText("已评论");
                        } else if (isPay.equals("6")) {
                            orderItemTag.setText("交易关闭");
                        }
                    }
                    transactionId.setText(bean.getData().getOrder_goods_id());
                    orderItemName.setText(bean.getData().getConsignee());
                    orderItemPhone.setText(bean.getData().getTel());
                    orderItemAddress.setText(bean.getData().getAddress());
                    orderItemGoodsimg.setText(bean.getData().getGoods_name());
                    orderItemPrice.setText(bean.getData().getGoods_price());
                    orderItemNumber.setText(bean.getData().getGoods_count());
                    transactionSale.setText(bean.getData().getGoods_total());
                    ImageLoader.getInstance().displayImage(global.getUrl() + bean.getData().getGoods_image(), transactionImg);

                    orderItemDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(TransactionActivity.this);
                            builder.setTitle("提示");
                            builder.setMessage("确定删除该订单？");
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    orderDetailDelete();
                                }
                            });
                            builder.setNegativeButton("取消", null);
                            builder.show();

                        }
                    });


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

    private void orderDetailDelete() {
        Log.i("tag", "删除订单编号:" + orderID);
        RequestParams params = new RequestParams(delteUrl);
        params.addBodyParameter("orderlist_id", orderID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("tag", "删除订单成功" + result);
                try {
                    JSONObject mJson = new JSONObject(result);
                    String mCode = mJson.getString("code");
                    if (mCode.equals("5000")) {
                        setResult(1);
                        finish();
                    } else if (mCode.equals("-5000")) {
                        Toast.makeText(TransactionActivity.this, "订单删除失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("tag", "删除订单失败");
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

    @OnClick(R.id.order_back1)
    public void onClick() {
        finish();
    }
}
