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
public class Pop_brand_Adapter extends BaseAdapter{
    private List<String> gList;
    private LayoutInflater layoutInflater;
    public Pop_brand_Adapter(Context context, List<String> gList){
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
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.pop_brand_item,null);
            holder.textView= (TextView) convertView.findViewById(R.id.pop_brand_text);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(gList.get(position));
        return convertView;
    }
    class ViewHolder{
        TextView textView;
    }
}
