package com.myproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adpter.Maintain_ListView_Adapter;
import bean.MaintainBean;

public class MaintainRecordActivity extends AppCompatActivity {
    private List<MaintainBean> maintainBeanList;
    private MaintainBean maintainBean;
    private ListView maintainListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain_record);
        maintainListView= (ListView) findViewById(R.id.maintain_listview);
        maintainBeanList=new ArrayList<>();
        maintainBean=new MaintainBean(null,null,null,520,R.mipmap.ic_launcher);
        maintainBeanList.add(maintainBean);
        maintainListView.setAdapter(new Maintain_ListView_Adapter(this,maintainBeanList));
    }
}
