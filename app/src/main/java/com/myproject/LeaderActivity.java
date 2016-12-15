package com.myproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import adpter.LeaderAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import leadfragment.Four;
import leadfragment.One;
import leadfragment.Three;
import leadfragment.Two;

public class LeaderActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.leadviewpager)
    ViewPager leadviewpager;
    @BindView(R.id.rbt1)
    RadioButton rbt1;
    @BindView(R.id.lead_radiogroup)
    RadioGroup leadRadiogroup;
    private Unbinder unbinder;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader);
        unbinder = ButterKnife.bind(this);
        list = new ArrayList<>();
        list.add(new One());
        list.add(new Two());
        list.add(new Three());
        list.add(new Four());
        FragmentManager fm = this.getSupportFragmentManager();
        leadviewpager.setAdapter(new LeaderAdapter(fm, list));
        leadviewpager.addOnPageChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton radioButton = (RadioButton) leadRadiogroup.getChildAt(position);
        radioButton.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
