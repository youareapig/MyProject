package com.myproject;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import adpter.Notice_Adapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NoticeActivity extends AppCompatActivity {
    @BindView(R.id.notice_title)
    TabLayout noticeTitle;
    @BindView(R.id.notice_viewpager)
    ViewPager noticeViewpager;
    private Unbinder unbinder;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        unbinder = ButterKnife.bind(this);
        noticeTitle.setupWithViewPager(noticeViewpager);
        manager=this.getSupportFragmentManager();
        noticeViewpager.setAdapter(new Notice_Adapter(manager));
        noticeViewpager.setCurrentItem(0);
        noticeViewpager.setOffscreenPageLimit(4);
        noticeTitle.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
