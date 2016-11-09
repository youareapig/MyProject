package com.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PayActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView pay_sale;
    private RelativeLayout pay_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        pay_sale= (TextView) findViewById(R.id.pay_sale);
        Intent intent=this.getIntent();
        String getSale=intent.getStringExtra("totalsale");
        Log.d("tag","价格"+getSale);
        pay_sale.setText(getSale);
        pay_back= (RelativeLayout) findViewById(R.id.pay_back);
        pay_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pay_back:
                finish();
                break;
        }
    }
}

