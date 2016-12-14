package com.myproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adpter.SearchHotAdapter;
import utils.Global;
import utils.OpenHelper;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout search_back, search;
    private EditText search_text;
    private GridView hotsearch,historysearch;
    private Global global;
    private String hotUrl;
    private OpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private ContentValues contentValues;
    private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //TODO 创建数据表，数据库
        openHelper=new OpenHelper(this);
        search = (RelativeLayout) findViewById(R.id.search);
        search_back = (RelativeLayout) findViewById(R.id.search_back);
        search_text = (EditText) findViewById(R.id.search_text);
        hotsearch = (GridView) findViewById(R.id.hotsearch);
        historysearch= (GridView) findViewById(R.id.historysearch);
        search_back.setOnClickListener(this);
        search.setOnClickListener(this);
        selectData();
    }

    @Override
    public void onClick(View v) {
        String searchText = search_text.getText().toString().trim();
        sqLiteDatabase = openHelper.getWritableDatabase();
        contentValues = new ContentValues();
        switch (v.getId()) {
            case R.id.search_back:
                finish();
                break;
            case R.id.search:
                if (TextUtils.isEmpty(searchText)) {
                    Toast.makeText(SearchActivity.this, "请输入关键字", Toast.LENGTH_SHORT).show();
                } else {
                    contentValues.put("historyText",searchText);
                    sqLiteDatabase.insert("search",null,contentValues);
                    sqLiteDatabase.close();
                    Intent intent = new Intent(SearchActivity.this, GoodsListActivity.class);
                    intent.putExtra("seach", searchText);
                    startActivity(intent);
                }
                break;
        }
    }
    private void selectData(){
        list=new ArrayList<>();
        sqLiteDatabase = openHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select historyText from search order by _id desc limit 0,8 ;", null);
        while (cursor.moveToNext()) {
            String historyText = cursor.getString(cursor.getColumnIndex("historyText"));
            list.add(historyText);
            Log.i("tag", "历史记录："+historyText );
        }
        historysearch.setAdapter(new SearchHotAdapter(this,list));

    }
}
