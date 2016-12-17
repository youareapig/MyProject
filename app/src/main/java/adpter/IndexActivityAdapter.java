package adpter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.makeramen.roundedimageview.RoundedImageView;
import com.myproject.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bean.IndexBean;
import utils.Global;

/**
 * Created by Administrator on 2016/12/17 0017.
 */
public class IndexActivityAdapter extends BaseAdapter{
    private List<IndexBean.DataBean.ActiveInfoBean> list;
    private LayoutInflater layoutInflater;
    private Global global;
    public IndexActivityAdapter(Context context, List<IndexBean.DataBean.ActiveInfoBean> list) {
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
        IndexBean.DataBean.ActiveInfoBean activeInfoBean=list.get(position);
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.index_activity_item,null);
            holder.imageView= (RoundedImageView) convertView.findViewById(R.id.index_activity_img);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(global.getUrl()+activeInfoBean.getActive_logo(),holder.imageView);
        return convertView;
    }
    private class ViewHolder{
        RoundedImageView imageView;
    }
}
