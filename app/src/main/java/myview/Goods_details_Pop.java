package myview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.myproject.IndentActivity;
import com.myproject.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import bean.GoodsDetailsBean;
import utils.Global;

/**
 * Created by Administrator on 2016/11/1 0001.
 */
public class Goods_details_Pop extends PopupWindow implements View.OnClickListener {
    private View mMenuView;
    private Button btn_sure;
    private TextView tv_down, tv_up, tv_num,pop_price,kucun;
    private ImageView pop_img;
    private String IMG;
    private Global global;
    private Context mContext;
    private DissPupWindw mDismiss;
    private GoodsDetailsBean.DataBean mDataBean;
    public Goods_details_Pop(Activity context, DissPupWindw mDismiss, GoodsDetailsBean.DataBean mDataBean) {
        super(context);
        mContext=context;
        this.mDataBean=mDataBean;
        this.mDismiss=mDismiss;
        global =new Global();
        IMG= global.getUrl();
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.goods_details_pop, null);
        btn_sure = (Button) mMenuView.findViewById(R.id.pop_sure);
        tv_up = (TextView) mMenuView.findViewById(R.id.pop_up);
        tv_down = (TextView) mMenuView.findViewById(R.id.pop_down);
        tv_num = (TextView) mMenuView.findViewById(R.id.pop_num);
        pop_img= (ImageView) mMenuView.findViewById(R.id.pop_img);
        kucun= (TextView) mMenuView.findViewById(R.id.number);
        pop_price= (TextView) mMenuView.findViewById(R.id.pop_price);
        tv_up.setOnClickListener(this);
        tv_down.setOnClickListener(this);
        btn_sure.setOnClickListener(this);
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationFadeGoods);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        //TODO 获取全局变量的图片和价格
        if (mDataBean!=null){
            ImageLoader.getInstance().displayImage(IMG+mDataBean.getThumb(),pop_img);
            pop_price.setText(mDataBean.getShop_price());
            kucun.setText(mDataBean.getAmount());
        }

    }

    @Override
    public void onClick(View v) {
       String t_num = tv_num.getText().toString();
        int num = Integer.parseInt(t_num);
        switch (v.getId()) {
            case R.id.pop_sure:
                if (mDataBean!=null){
                    Intent intent=new Intent(v.getContext(),IndentActivity.class);
                    intent.putExtra("goodsnum",num+"");
                    Log.d("num", num + "------------------------------个");
                    EventBus.getDefault().postSticky(mDataBean);
                    v.getContext().startActivity(intent);
                    mDismiss.dismiss();
                }

                break;
            case R.id.pop_up:
                if (num==(Integer.parseInt(mDataBean.getAmount())-1)){
                    tv_up.setEnabled(false);

                }
                ++num;
                Log.d("num", num + "个");
                tv_num.setText(num + "");

                break;
            case R.id.pop_down:
                tv_up.setEnabled(true);
                if (num<=1){
                    num=1;
                }else {
                    --num;
                }
                tv_num.setText(num+"");
                break;

        }
    }
    public interface DissPupWindw{
        void dismiss();
    }
}
