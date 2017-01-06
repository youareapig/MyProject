package com.myproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.pay.PayResult;
import com.google.gson.Gson;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

import bean.WXpayBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.Global;
import utils.ToastUtil;

public class PayActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.activity_pay_weixin)
    RelativeLayout mPayWeixin;
    @BindView(R.id.activity_pay_zhifubao)
    RelativeLayout mPayZhifubao;
    @BindView(R.id.activity_pay_yinlian)
    RelativeLayout mPayYinlian;
    private TextView pay_sale;
    private RelativeLayout pay_back;
    private String OrderNumber;
    private String mOrder_id;
    private String mTotal_Price;
    private static final int SDK_PAY_FLAG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        pay_sale = (TextView) findViewById(R.id.pay_sale);
        Intent intent = getIntent();
        setResult(1);
        mTotal_Price = intent.getStringExtra("total_price");
        OrderNumber=intent.getStringExtra("order_number");
        mOrder_id=intent.getStringExtra("order_id");
        pay_sale.setText(mTotal_Price);
        pay_back = (RelativeLayout) findViewById(R.id.pay_back);
        pay_back.setOnClickListener(this);

    }

    @OnClick({R.id.activity_pay_weixin, R.id.activity_pay_zhifubao, R.id.activity_pay_yinlian,R.id.pay_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_pay_weixin:
                ToastUtil.showToast(PayActivity.this,"暂未开通");
                RequestWXpay();
                break;
            case R.id.activity_pay_zhifubao:
                RequestZFBKey();
                break;
            case R.id.activity_pay_yinlian:
                ToastUtil.showToast(PayActivity.this,"暂未开通");
                break;
            case R.id.pay_back:
                finish();
                break;
        }
    }
    public void payV2(final String orderInfo) {

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
    @SuppressLint("HandlerLeak")
    private Handler mHandler =  new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtil.showToast(PayActivity.this,"支付成功");
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtil.showToast(PayActivity.this,"支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    public void RequestZFBKey(){
        RequestParams params = new RequestParams(Global.ZFBSIGNATRRE);
        params.addBodyParameter("total_fee",mTotal_Price);
        params.addBodyParameter("partner","2088812636250223");
        params.addBodyParameter("out_trade_no",mOrder_id);
        params.addBodyParameter("service","mobile.securitypay.pay");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.v("PayActivity","----------->"+result);
                try {
                    JSONObject object = new JSONObject(result);
                    String orderinfo= object.getString("data");
                    payV2(orderinfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.v("PayActivity","---------------->"+"错误了");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    public void RequestWXpay(){
        RequestParams params = new RequestParams(Global.WXPAYURL);
        params.addBodyParameter("osn",mOrder_id);
        params.addBodyParameter("total_fee","0.01");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.v("PAYACTIVITY","---------------->"+result);
                Gson gson = new Gson();
                WXpayBean bean = gson.fromJson(result,WXpayBean.class);
                WeChatPay(bean);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.v("PAYACTIVITY","---------------->"+"错误");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private void WeChatPay(WXpayBean data){
        IWXAPI api = WXAPIFactory.createWXAPI(PayActivity.this, Global.APP_ID);
        api.registerApp(Global.APP_ID);
        PayReq req = new PayReq();
        req.appId			= data.getAppid();
        req.partnerId		= data.getPartnerid();
        req.prepayId		= data.getPrepayid();
        req.nonceStr		= data.getNoncestr();
        req.timeStamp		= data.getTimestamp();
        req.packageValue	= "Sign=WXPay";
        req.sign			= data.getSign();
        //req.extData			= "app data"; // optional
        Log.e("tag","参数说明------->"+req.appId+"  "+req.partnerId+"  "+req.prepayId+"   "+req.nonceStr+"  "+req.timeStamp+"  "+req.sign);
        api.sendReq(req);
    }
}

