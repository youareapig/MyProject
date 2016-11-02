package com.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class IndentActivity extends AppCompatActivity {
    private TextView indent_goods_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent);
        indent_goods_num= (TextView) findViewById(R.id.indent_goods_num);
        Intent intent=this.getIntent();
        String result_goods_num=intent.getStringExtra("goodsnum");
        Log.d("tag",result_goods_num+"商品数量");
        indent_goods_num.setText(result_goods_num);
    }

}
