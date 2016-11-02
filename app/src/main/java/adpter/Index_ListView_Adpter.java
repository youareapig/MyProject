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

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class Index_ListView_Adpter extends BaseAdapter{
    private List<HashMap<String,Object>> l_List;
    private LayoutInflater layoutInflater;
    public Index_ListView_Adpter(Context context, List<HashMap<String,Object>> gList){
        this.layoutInflater=((Activity) context).getLayoutInflater();
        this.l_List=gList;

    }
    @Override
    public int getCount() {
        return l_List.size();
    }

    @Override
    public Object getItem(int position) {
        return l_List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=new ViewHolder();
        HashMap<String,Object> map=l_List.get(position);
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.index_listview_item,null);
            holder.imageView= (ImageView) convertView.findViewById(R.id.index_listview_img);
            holder.textView= (TextView) convertView.findViewById(R.id.index_listview_details);
            holder.textView1= (TextView) convertView.findViewById(R.id.index_listview_sale);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource((Integer) map.get("img"));
        holder.textView.setText(map.get("details").toString());
        holder.textView1.setText(map.get("sale").toString());
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView textView,textView1;
    }
}
