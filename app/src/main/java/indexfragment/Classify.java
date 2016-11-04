package indexfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.myproject.R;

import java.util.ArrayList;
import java.util.List;

import classifyfragment.Decorate;
import classifyfragment.Equipment;
import classifyfragment.Maintain;
import classifyfragment.Refit;
import classifyfragment.Repair;
import classifyfragment.Safety;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class Classify extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    private Fragment fragment = new Fragment();
    private List<Fragment> list = new ArrayList<>();
    private int currentIndex = 0;
    private FragmentManager fragmentManager;
    private RadioGroup bgp_classfiy;

    //TODO GirdView的适配器没写
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classify, container, false);
        bgp_classfiy = (RadioGroup) view.findViewById(R.id.classfiy_bgp);
        bgp_classfiy.setOnCheckedChangeListener(this);
        fragmentManager = getActivity().getSupportFragmentManager();
        if (savedInstanceState != null) {

            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT, 0);

            list.removeAll(list);
            list.add(fragmentManager.findFragmentByTag(0 + ""));
            list.add(fragmentManager.findFragmentByTag(1 + ""));
            list.add(fragmentManager.findFragmentByTag(2 + ""));
            list.add(fragmentManager.findFragmentByTag(3 + ""));
            list.add(fragmentManager.findFragmentByTag(4 + ""));
            list.add(fragmentManager.findFragmentByTag(5 + ""));

            restoreFragment();


        } else {

            list.add(new Maintain());
            list.add(new Decorate());
            list.add(new Repair());
            list.add(new Equipment());
            list.add(new Refit());
            list.add(new Safety());

            showFragment();
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_FRAGMENT, currentIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.classfiy_maintain:
                currentIndex = 0;
                showFragment();
                break;
            case R.id.classfiy_decorate:
                currentIndex = 1;
                showFragment();
                break;
            case R.id.classfiy_repair:
                currentIndex = 2;
                showFragment();
                break;
            case R.id.classfiy_equipment:
                currentIndex = 3;
                showFragment();
                break;
            case R.id.classfiy_refit:
                currentIndex = 4;
                showFragment();
                break;
            case R.id.classfiy_safety:
                currentIndex = 5;
                showFragment();
                break;
        }

    }

    private void showFragment() {

        FragmentTransaction transaction = fragmentManager.beginTransaction();

//如果之前没有添加过
        if (!list.get(currentIndex).isAdded()) {
            transaction
                    .hide(fragment)
                    .add(R.id.f_layout, list.get(currentIndex), "" + currentIndex);  //第三个参数为添加当前的fragment时绑定一个tag

        } else {
            transaction
                    .hide(fragment)
                    .show(list.get(currentIndex));
        }

        fragment = list.get(currentIndex);

        transaction.commit();

    }

    private void restoreFragment() {
        FragmentTransaction mBeginTreansaction = fragmentManager.beginTransaction();

        for (int i = 0; i < list.size(); i++) {

            if (i == currentIndex) {
                mBeginTreansaction.show(list.get(i));
            } else {
                mBeginTreansaction.hide(list.get(i));
            }

        }

        mBeginTreansaction.commit();

//把当前显示的fragment记录下来
        fragment = list.get(currentIndex);

    }
}
