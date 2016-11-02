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
public class Index_GridView_Adpter extends BaseAdapter{
    private List<HashMap<String,Object>> gList;
    private LayoutInflater layoutInflater;
    public Index_GridView_Adpter(Context context, List<HashMap<String,Object>> gList){
        this.layoutInflater=((Activity) context).getLayoutInflater();
        this.gList=gList;

    }
    @Override
    public int getCount() {
        return gList.size();
    }

    @Override
    public Object getItem(int position) {
        return gList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=new ViewHolder();
        HashMap<String,Object> map=gList.get(position);
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.index_gridview_item,null);
            holder.imageView= (ImageView) convertView.findViewById(R.id.index_gridview_item_img);
            holder.textView= (TextView) convertView.findViewById(R.id.index_gridview_item_id);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource((Integer) map.get("img"));
        holder.textView.setText(map.get("id").toString());
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
