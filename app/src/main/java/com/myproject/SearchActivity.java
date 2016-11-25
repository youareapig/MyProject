package com.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout search_back, search;
    private EditText search_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search = (RelativeLayout) findViewById(R.id.search);
        search_back = (RelativeLayout) findViewById(R.id.search_back);
        search_text = (EditText) findViewById(R.id.search_text);
        search_back.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String searchText = search_text.getText().toString().trim();
        switch (v.getId()) {
            case R.id.search_back:
                finish();
                break;
            case R.id.search:
                if (TextUtils.isEmpty(searchText)) {
                    Toast.makeText(SearchActivity.this, "请输入关键字", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(SearchActivity.this, GoodsListActivity.class);
                    intent.putExtra("seach", searchText);
                    startActivity(intent);
                }
                break;
        }
    }
}
