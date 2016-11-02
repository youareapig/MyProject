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
public class Index_GridViewActivity_Adpter extends BaseAdapter{
    private List<HashMap<String,Object>> aList;
    private LayoutInflater layoutInflater;
    public Index_GridViewActivity_Adpter(Context context, List<HashMap<String,Object>> gList){
        this.layoutInflater=((Activity) context).getLayoutInflater();
        this.aList=gList;

    }
    @Override
    public int getCount() {
        return aList.size();
    }

    @Override
    public Object getItem(int position) {
        return aList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=new ViewHolder();
        HashMap<String,Object> map=aList.get(position);
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.index_gridviewactivity_item,null);
            holder.imageView= (ImageView) convertView.findViewById(R.id.activity_img);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource((Integer) map.get("img"));
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
    }
}
