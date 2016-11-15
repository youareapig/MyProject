package adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import helpfragment.Help;
import helpfragment.Ideal;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class Helper_Adapter extends FragmentPagerAdapter {
    private List<String> list = new ArrayList<>();
    private List<Fragment> flist = new ArrayList<>();

    public Helper_Adapter(FragmentManager fm) {
        super(fm);
        list.add("常见问题");
        list.add("意见与反馈");
        flist.add(new Help());
        flist.add(new Ideal());
    }

    @Override
    public Fragment getItem(int position) {
        return flist.get(position);
    }

    @Override
    public int getCount() {
        return flist.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
