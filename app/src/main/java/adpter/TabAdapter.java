package adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import indexfragment.WashServiceIntroduce;
import indexfragment.WashServiceRange;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
public class TabAdapter extends FragmentPagerAdapter{
    List<String> str_list=new ArrayList<String>();
    List<Fragment> fragment_mlist=new ArrayList<>();
    public TabAdapter(FragmentManager fm) {
        super(fm);
        str_list.add("服务介绍");
        str_list.add("预约指南");
        fragment_mlist.add(new WashServiceIntroduce());
        fragment_mlist.add(new WashServiceRange());
    }

    @Override
    public Fragment getItem(int position) {
        return fragment_mlist.get(position);
    }

    @Override
    public int getCount() {
        return fragment_mlist.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return str_list.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
