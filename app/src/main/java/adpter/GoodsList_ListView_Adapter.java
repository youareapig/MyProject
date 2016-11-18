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
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;
import java.util.List;

import bean.GoodsList_Bean;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class GoodsList_ListView_Adapter extends BaseAdapter{
    private static final String HTTP="http://192.168.0.108/";
    private List<GoodsList_Bean.DataBean> list;
    private LayoutInflater layoutInflater;
    public GoodsList_ListView_Adapter(Context context, List<GoodsList_Bean.DataBean> list){
        this.layoutInflater=((Activity) context).getLayoutInflater();
        this.list=list;

    }
    @Override
    public int getCount() {
        if (list!=null){
            return list.size();
        }else {
            return 0;
        }

    }

    @Override
    public Object getItem(int position) {
        if (list!=null){
            return list.get(position);
        }else {
            return null;
        }

    }

    @Override
    public long getItemId(int position) {
            return position;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=new ViewHolder();
        GoodsList_Bean.DataBean bean=list.get(position);
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
        ImageLoader.getInstance().displayImage(HTTP+bean.getThumb(),holder.imageView);
        holder.tv_introduce.setText(bean.getGoods_name());
        holder.tv_sale.setText(bean.getShop_price());
        holder.tv_person.setText(bean.getSales()+"");
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView tv_introduce,tv_sale,tv_person;
    }
}
