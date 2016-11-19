package adpter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.myproject.R;

import java.util.List;

import bean.ClassifyBean;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class Classfiy_Adapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<ClassifyBean.DataBean> list ;

    public Classfiy_Adapter(Context context,List<ClassifyBean.DataBean> list) {
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
        ClassifyBean.DataBean dataBean=list.get(position);
        if (convertView==null){
            holder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.left_listview_item,null);
            holder.left_text= (TextView) convertView.findViewById(R.id.left_listview_item_name);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        Log.d("tag",dataBean.getCat_name()+"111111111");
        holder.left_text.setText(dataBean.getCat_name());
        //选中状态
        if (position == selectItem) {
            convertView.setBackgroundColor(Color.parseColor("#00000000"));
            holder.left_text.setTextColor(Color.parseColor("#449df5"));
        }
        else {
            convertView.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.left_text.setTextColor(Color.parseColor("#121212"));
        }
        return convertView;
    }
    class ViewHolder{
        TextView left_text;

    }
    //TODO 选中状态变化, selectItem=0表示默认选中第一项
    private int  selectItem=0;
    public  void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

}
