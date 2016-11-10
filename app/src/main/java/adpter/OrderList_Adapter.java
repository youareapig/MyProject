package adpter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myproject.R;

import java.util.List;

import bean.GoodsList_Bean;
import bean.Order_Bean;

/**
 * Created by Administrator on 2016/11/10 0010.
 */
public class OrderList_Adapter extends BaseAdapter {
    private List<Order_Bean> list;
    private LayoutInflater layoutInflater;

    public OrderList_Adapter(Context context, List<Order_Bean> list) {
        this.layoutInflater = ((Activity) context).getLayoutInflater();
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Order_Bean bean = list.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.order_listview_item, null);
            holder.tv_date = (TextView) convertView.findViewById(R.id.order_item_date);
            holder.tv_state = (TextView) convertView.findViewById(R.id.order_item_tag);
            holder.imageView = (ImageView) convertView.findViewById(R.id.order_item_img);
            holder.tv_name = (TextView) convertView.findViewById(R.id.order_item_name);
            holder.tv_goodsnumber = (TextView) convertView.findViewById(R.id.order_item_num);
            holder.tv_goodsprice = (TextView) convertView.findViewById(R.id.order_item_danjia);
            holder.tv_goodstotalprice = (TextView) convertView.findViewById(R.id.order_item_sale);
            holder.tv_serviceprice = (TextView) convertView.findViewById(R.id.order_item_servicesale);
            holder.tv_totalprice = (TextView) convertView.findViewById(R.id.order_item_totalsale);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_date.setText(bean.getDate());
        holder.tv_state.setText(bean.getState());
        holder.imageView.setImageResource(bean.getImg());
        holder.tv_name.setText(bean.getName());
        holder.tv_goodsnumber.setText(bean.getNumber() + "");
        holder.tv_goodsprice.setText(bean.getGoodsprice() + "");
        holder.tv_goodstotalprice.setText(bean.getGoodstotalsale() + "");
        holder.tv_serviceprice.setText(bean.getServiceprice() + "");
        holder.tv_totalprice.setText(bean.getTotalprice() + "");
        return convertView;
    }

    class ViewHolder {
        TextView tv_date, tv_state, tv_name, tv_goodsprice, tv_goodsnumber, tv_goodstotalprice, tv_serviceprice, tv_totalprice;
        ImageView imageView;

    }
}
