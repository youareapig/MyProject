package myview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class Index_GrideView extends GridView{
    public Index_GrideView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Index_GrideView(Context context) {
        super(context);
    }

    public Index_GrideView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
