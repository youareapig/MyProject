package adpter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myproject.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bean.ClassifyBean;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class ClassfiyGrid_Adapter extends BaseAdapter {
    private static final String HTTP="http://192.168.0.108/";
    private LayoutInflater layoutInflater;
    private List<ClassifyBean.DataBean.ChildrenBean.Children1Bean> list ;

    public ClassfiyGrid_Adapter(Context context, List<ClassifyBean.DataBean.ChildrenBean.Children1Bean> list) {
        this.list=list;
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
        ClassifyBean.DataBean.ChildrenBean.Children1Bean children1Bean=list.get(position);
        if (convertView==null){
            holder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.classfiy_item,null);
            holder.icon= (ImageView) convertView.findViewById(R.id.classfiy_item_icon);
            holder.name= (TextView) convertView.findViewById(R.id.classfiy_item_name);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
            holder.name.setText(children1Bean.getCat_name());

        ImageLoader.getInstance().displayImage(HTTP+children1Bean.getPic(),holder.icon);
        return convertView;
    }
    class ViewHolder{
        ImageView icon;
        TextView name;

    }

}