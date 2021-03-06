package myview;

import android.app.Activity;
import android.content.Context;
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
import android.widget.Toast;

import com.myproject.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import bean.GoodsDetailsBean;
import utils.Global;
import utils.ToastUtil;

/**
 * Created by Administrator on 2016/12/12.
 */
public class GoodsDetailAdd extends PopupWindow implements View.OnClickListener {
    private View mMenuView;
    private Button btn_sure;
    private TextView tv_down, tv_up, tv_num, pop_price, kucun;
    private ImageView pop_img;
    private String IMG;
    private Global global;
    private Context mContext;
    private DissPupWindw mDismiss;
    private GoodsDetailsBean.DataBean mDataBean;
    private String userid;

    public GoodsDetailAdd(Activity context, DissPupWindw mDismiss, GoodsDetailsBean.DataBean mDataBean, String userid) {
        super(context);
        mContext = context;
        this.userid = userid;
        this.mDataBean = mDataBean;
        this.mDismiss = mDismiss;
        global = new Global();
        IMG = global.getUrl();
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.goods_details_pop, null);
        btn_sure = (Button) mMenuView.findViewById(R.id.pop_sure);
        tv_up = (TextView) mMenuView.findViewById(R.id.pop_up);
        tv_down = (TextView) mMenuView.findViewById(R.id.pop_down);
        tv_num = (TextView) mMenuView.findViewById(R.id.pop_num);
        pop_img = (ImageView) mMenuView.findViewById(R.id.pop_img);
        pop_price = (TextView) mMenuView.findViewById(R.id.pop_price);
        kucun = (TextView) mMenuView.findViewById(R.id.number);
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
        if (mDataBean != null) {
            ImageLoader.getInstance().displayImage(IMG + mDataBean.getThumb(), pop_img);
            pop_price.setText(mDataBean.getShop_price());
            kucun.setText(mDataBean.getAmount());
            Log.e("tag", "库存：------------->" + mDataBean.getAmount());
        }
    }

    @Override
    public void onClick(View v) {
        String t_num = tv_num.getText().toString();
        int num = Integer.parseInt(t_num);
        switch (v.getId()) {
            case R.id.pop_sure:
                addShopcar(t_num);
                mDismiss.dismiss();
                break;
            case R.id.pop_up:
                if (num == (Integer.parseInt(mDataBean.getAmount()) - 1)) {
                    tv_up.setEnabled(false);
                }
                ++num;
                Log.d("num", num + "个");
                tv_num.setText(num + "");
                break;
            case R.id.pop_down:
                tv_up.setEnabled(true);
                if (num <= 1) {
                    num = 1;
                } else {
                    --num;
                }
                tv_num.setText(num + "");
                break;

        }
    }

    public interface DissPupWindw {
        void dismiss();
    }

    public void addShopcar(String num) {
        RequestParams params = new RequestParams(Global.SHOPCARADDDATA);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("goods_id", mDataBean.getGoods_id());
        params.addBodyParameter("goods_price", mDataBean.getShop_price());
        params.addBodyParameter("goods_number", num);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("add", "----------------请求成功：" + result);
                try {
                    JSONObject object = new JSONObject(result);
                    int rs = object.getInt("code");
                    if (rs == -1000) {
                        //ToastUtil.showToast(mContext,"该商品已存在购物车");
                        Toast.makeText(mContext, "该商品已存在购物车", Toast.LENGTH_SHORT).show();
                    } else {
                        //ToastUtil.showToast(mContext,"已添加到购物车");
                        Toast.makeText(mContext, "已添加到购物车", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("add", "---------请求错误");
                ToastUtil.showToast(mContext,"网络请求错误！");

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
}
