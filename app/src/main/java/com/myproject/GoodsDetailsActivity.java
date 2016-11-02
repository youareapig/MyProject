package com.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import myview.Goods_details_Pop;

public class GoodsDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView buy;
    private Goods_details_Pop goodsDetailsPop;
    private LinearLayout goods_main;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        buy = (TextView) findViewById(R.id.buy);
        goods_main = (LinearLayout) findViewById(R.id.main);
        buy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy:
                //实例化SelectPicPopupWindow
                goodsDetailsPop = new Goods_details_Pop(GoodsDetailsActivity.this);
                //显示窗口
                goodsDetailsPop.showAtLocation(GoodsDetailsActivity.this.findViewById(R.id.buy), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                goodsDetailsPop.setOutsideTouchable(false);
                break;
        }
    }

//    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
//        public void onClick(View v) {
//            String number=textView.getText().toString();
//            switch (v.getId()) {
//                case R.id.pop_sure:
//                    Log.d("tag",number+"````````");
//                    Intent intent=new Intent(GoodsDetailsActivity.this,IndentActivity.class);
//                    intent.putExtra("goodsnum",number);
//
//                    startActivity(intent);
//                    break;
//
//            }
//
//
//        }
//
//    };


}
