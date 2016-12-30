package adpter;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myproject.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import bean.Order_Bean;
import myview.CustomDialog;
import utils.Global;

/**
 * Created by Administrator on 2016/11/10 0010.
 */
public class OrderList_Adapter extends BaseAdapter {
    private List<Order_Bean.DataBean> list;
    private LayoutInflater layoutInflater;
    private Global global;
    private String delteUrl;
    private Activity context;

    public OrderList_Adapter(Activity context, List<Order_Bean.DataBean> list) {
        this.layoutInflater = context.getLayoutInflater();
        this.list = list;
        global = new Global();
        delteUrl=global.getUrl()+"api.php/Memberorder/delorderlist";
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Order_Bean.DataBean bean = list.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.order_listview_item, null);
            holder.tv_date = (TextView) convertView.findViewById(R.id.order_item_date);
            holder.tv_state = (TextView) convertView.findViewById(R.id.order_item_tag);
            holder.imageView = (ImageView) convertView.findViewById(R.id.order_item_img);
            holder.tv_name = (TextView) convertView.findViewById(R.id.order_item_name);
            holder.tv_goodsnumber = (TextView) convertView.findViewById(R.id.order_item_num);
            holder.tv_goodsprice = (TextView) convertView.findViewById(R.id.order_item_danjia);
            holder.tv_totalprice = (TextView) convertView.findViewById(R.id.order_item_totalsale);
            holder.order_delete= (TextView) convertView.findViewById(R.id.order_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String orderTag=bean.getIs_pay();
        String orderTag1=bean.getState();
        String gPrice=bean.getGoods_total();

        holder.tv_date.setText(getDateFromSeconds(String.valueOf(bean.getAddtime())));

        ImageLoader.getInstance().displayImage(global.getUrl() + bean.getGoods_image(), holder.imageView);
        holder.tv_name.setText(bean.getGoods_name());
        holder.tv_goodsnumber.setText(bean.getGoods_count());
        holder.tv_goodsprice.setText(bean.getGoods_price());
        holder.tv_totalprice.setText(gPrice);
        Log.i("tag","订单状态"+orderTag);
        if (orderTag.equals("0")){
            holder.tv_state.setText("未支付");
        }else {
            if (orderTag1.equals("0")){
                holder.tv_state.setText("未发货");
            }else if (orderTag1.equals("1")){
                holder.tv_state.setText("已发货");
            }else if (orderTag1.equals("2")){
                holder.tv_state.setText("已收货");
            }else if (orderTag1.equals("3")){
                holder.tv_state.setText("交易成功");
            }else if (orderTag1.equals("4")){
                holder.tv_state.setText("未评论");
            }else if (orderTag1.equals("5")){
                holder.tv_state.setText("已评论");
            }else if (orderTag1.equals("6")){
                holder.tv_state.setText("交易关闭");
            }else if (orderTag1.equals("7")){
                holder.tv_state.setText("已删除");
            }
        }
        holder.order_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final String orderID=bean.getOrderlist_id();
                CustomDialog.Builder builder = new CustomDialog.Builder(v.getContext());
                builder.setTitle("提示");
                builder.setMessage("确定删除该订单？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteOrder(orderID,position);
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();

            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView tv_date, tv_state, tv_name, tv_goodsprice, tv_goodsnumber, order_delete, tv_totalprice;
        ImageView imageView;

    }
    //TODO 将秒数转换成日期格式
    private String getDateFromSeconds(String seconds) {
        if (seconds == null)
            return null;
        else {
            Date date = new Date();
            try {
                date.setTime(Long.parseLong(seconds) * 1000);
            } catch (NumberFormatException nfe) {

            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(date);
        }
    }
    //TODO 删除订单
    private void deleteOrder(String orderID, final int position){
        Log.i("tag","删除订单编号："+orderID+delteUrl);
        RequestParams params=new RequestParams(delteUrl);
        params.addBodyParameter("orderlist_id",orderID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("tag","删除订单成功"+result);
                try {
                    JSONObject mJson=new JSONObject(result);
                    String mCode=mJson.getString("code");
                    if (mCode.equals("5000")){
                        list.remove(position);
                        notifyDataSetChanged();
                    }else if (mCode.equals("-5000")){
                        Toast.makeText(context,"订单删除失败",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("tag","删除订单失败");
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
