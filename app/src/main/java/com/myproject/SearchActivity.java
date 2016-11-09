package com.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout search_back,search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search= (RelativeLayout) findViewById(R.id.search);
        search_back= (RelativeLayout) findViewById(R.id.search_back);
        search_back.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_back:
                finish();
                break;
            case R.id.search:
                Intent intent =new Intent(SearchActivity.this,GoodsListActivity.class);
                startActivity(intent);
                break;
        }
    }
}
