package com.myproject;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import adpter.TabAdapter;

public class WashActivity extends AppCompatActivity {
    private ViewPager pager;
    private TabLayout tabLayout;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash);
        init();
        getTab();
    }
    public void init(){
        pager= (ViewPager) findViewById(R.id.wash_viewpage);
        tabLayout= (TabLayout) findViewById(R.id.wash_tab);

    }
    public void getTab(){
        tabLayout.setupWithViewPager(pager);
        fragmentManager=this.getSupportFragmentManager();
        pager.setAdapter(new TabAdapter(fragmentManager));
        pager.setCurrentItem(0);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
