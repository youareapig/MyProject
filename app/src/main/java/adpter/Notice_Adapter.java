package adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import noticfragment.Consult;
import noticfragment.Knowledge;
import noticfragment.Notice;
import noticfragment.Welfare;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public class Notice_Adapter extends FragmentPagerAdapter {
    private List<String> listName = new ArrayList<>();
    private List<Fragment> listFragment = new ArrayList<>();

    public Notice_Adapter(FragmentManager fm) {
        super(fm);
        listName.add("公告");
        listName.add("福利");
        listName.add("咨询");
        listName.add("知识");
        listFragment.add(new Notice());
        listFragment.add(new Welfare());
        listFragment.add(new Consult());
        listFragment.add(new Knowledge());
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return listName.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
