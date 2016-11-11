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
import bean.MaintainBean;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
public class Maintain_ListView_Adapter extends BaseAdapter{
    private List<MaintainBean> list;
    private LayoutInflater layoutInflater;

    public Maintain_ListView_Adapter(Context context, List<MaintainBean> list) {
        this.list = list;
        this.layoutInflater=((Activity) context).getLayoutInflater();
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
        ViewHolder holder=null;
        MaintainBean bean=list.get(position);
        if (convertView==null){
            holder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.maintain_listview_item,null);
            holder.storeName= (TextView) convertView.findViewById(R.id.maintain_listview_storename);
            holder.goodsName= (TextView) convertView.findViewById(R.id.maintain_listview_goodsname);
            holder.maintainTime= (TextView) convertView.findViewById(R.id.maintain_listview_time);
            holder.maintainMoney= (TextView) convertView.findViewById(R.id.maintain_listview_money);
            holder.maintainIcon= (ImageView) convertView.findViewById(R.id.maintain_listview_img);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.storeName.setText(bean.getStoreName());
        holder.goodsName.setText(bean.getGoodsName());
        holder.maintainTime.setText(bean.getMaintainTime());
        holder.maintainMoney.setText(bean.getMaintainMoney()+"");
        holder.maintainIcon.setImageResource(bean.getMaintainIcon());
        return convertView;
    }
    class ViewHolder{
        TextView storeName,goodsName,maintainTime,maintainMoney;
        ImageView maintainIcon;
    }
}
