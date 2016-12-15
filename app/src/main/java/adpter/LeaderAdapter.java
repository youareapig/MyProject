package adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/15 0015.
 */
public class LeaderAdapter extends FragmentPagerAdapter{
    private List<Fragment> list;
    public LeaderAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }
}
