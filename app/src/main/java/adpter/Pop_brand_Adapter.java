package adpter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myproject.R;

import java.util.List;

import bean.GoodsList_Bean;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class Pop_brand_Adapter extends BaseAdapter{
    private List<GoodsList_Bean.BrandBean> gList;
    private LayoutInflater layoutInflater;
    public Pop_brand_Adapter(Context context, List<GoodsList_Bean.BrandBean> gList){
        this.layoutInflater=((Activity) context).getLayoutInflater();
        this.gList=gList;

    }
    @Override
    public int getCount() {
        if (gList!=null){
            return gList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (gList!=null){
            return gList.get(position);
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
        GoodsList_Bean.BrandBean brandBean=gList.get(position);
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.pop_brand_item,null);
            holder.textView= (TextView) convertView.findViewById(R.id.pop_brand_text);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(brandBean.getBrand_name());
        return convertView;
    }
    class ViewHolder{
        TextView textView;
    }
}
