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

import java.util.List;

import bean.SalesBean;
import utils.Global;

/**
 * Created by Administrator on 2016/12/17 0017.
 */
public class SalesGridVIewAdapter extends BaseAdapter{
    private List<SalesBean.DataBean.InfoBean> list;
    private LayoutInflater layoutInflater;
    private Global global;
    public SalesGridVIewAdapter(Context context, List<SalesBean.DataBean.InfoBean> list) {
        this.list = list;
        this.layoutInflater=((Activity) context).getLayoutInflater();
        global=new Global();
    }

    @Override
    public int getCount() {
        if (list!=null){
           return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list!=null){
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=new ViewHolder();
        SalesBean.DataBean.InfoBean bean=list.get(position);
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.sales_item,null);
            holder.slaesimage= (ImageView) convertView.findViewById(R.id.sales_item_img);
            holder.oldprice= (TextView) convertView.findViewById(R.id.sales_item_newprice);
            holder.newprice= (TextView) convertView.findViewById(R.id.sales_item_activitprice);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(global.getUrl()+bean.getThumb(),holder.slaesimage);
        holder.oldprice.setText(bean.getShop_price());
        holder.newprice.setText(bean.getActive_price());
        return convertView;
    }
    private class ViewHolder{
        private ImageView slaesimage;
        private TextView oldprice,newprice;
    }
}
