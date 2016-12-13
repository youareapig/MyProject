package com.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.activity_pay_weixin)
    RelativeLayout mPayWeixin;
    @BindView(R.id.activity_pay_zhifubao)
    RelativeLayout mPayZhifubao;
    @BindView(R.id.activity_pay_yinlian)
    RelativeLayout mPayYinlian;
    private TextView pay_sale;
    private RelativeLayout pay_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        pay_sale = (TextView) findViewById(R.id.pay_sale);
        Intent intent = this.getIntent();
        String getSale = intent.getStringExtra("totalsale");
        Log.d("tag", "价格" + getSale);
        pay_sale.setText(getSale);
        pay_back = (RelativeLayout) findViewById(R.id.pay_back);
        pay_back.setOnClickListener(this);

    }



    @OnClick({R.id.activity_pay_weixin, R.id.activity_pay_zhifubao, R.id.activity_pay_yinlian,R.id.pay_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_pay_weixin:
                break;
            case R.id.activity_pay_zhifubao:
                break;
            case R.id.activity_pay_yinlian:
                Toast.makeText(PayActivity.this,"暂未开通",Toast.LENGTH_LONG).show();
                break;
            case R.id.pay_back:
                finish();
                break;
        }
    }
}

