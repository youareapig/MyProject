package adpter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/10/21 0021.
 */
public class IndexBannerAdapter extends PagerAdapter {
    private ImageView[] imageViews;
    public IndexBannerAdapter(ImageView[] imageViews) {
        this.imageViews=imageViews;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
  //      if (imageViews.length==1){
            return imageViews.length;
//        }else {
//            return Integer.MAX_VALUE;
//        }

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //TODO 如果图片是两张或者三张或出现bug,注释正常
        //container.removeView(imageViews[position % imageViews.length]);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        try {
            container.addView(imageViews[position % imageViews.length], 0);
        }catch (Exception e){

        }
        return imageViews[position % imageViews.length];
    }
}
