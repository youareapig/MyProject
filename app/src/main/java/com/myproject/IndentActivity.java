package com.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class IndentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent);
        Intent intent=this.getIntent();
        String result_goods_num=intent.getStringExtra("goodsnum");
        Log.d("tag",result_goods_num+"商品数量");
    }


}
