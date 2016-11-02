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

import java.util.HashMap;
import java.util.List;

import bean.GoodsList_Bean;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class GoodsList_ListView_Adapter extends BaseAdapter{
    private List<GoodsList_Bean> list;
    private LayoutInflater layoutInflater;
    public GoodsList_ListView_Adapter(Context context, List<GoodsList_Bean> list){
        this.layoutInflater=((Activity) context).getLayoutInflater();
        this.list=list;

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
        ViewHolder holder=new ViewHolder();
        GoodsList_Bean bean=list.get(position);
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.goodslist_list_item,null);
            holder.imageView= (ImageView) convertView.findViewById(R.id.goodslist_listview_item_img);
            holder.tv_introduce= (TextView) convertView.findViewById(R.id.goodslist_listview_item_introduce);
            holder.tv_person= (TextView) convertView.findViewById(R.id.goodslist_listview_item_person);
            holder.tv_sale= (TextView) convertView.findViewById(R.id.goodslist_listview_item_sale);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource(bean.getGoodslist_img());
        holder.tv_introduce.setText(bean.getGoodslist_img_introduce());
        holder.tv_sale.setText(bean.getGoodslist_sale()+"");
        holder.tv_person.setText(bean.getGoodslist_person()+"");
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView tv_introduce,tv_sale,tv_person;
    }
}
