package adpter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myproject.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bean.NoticeBean;
import utils.Global;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public class NoticeList_Adapter extends BaseAdapter{
    private List<NoticeBean.DataBean.ChildrenoneBean> list;
    private LayoutInflater layoutInflater;
    private Global global=new Global();
    private String Pic=global.getUrl();
    public NoticeList_Adapter(Activity context, List<NoticeBean.DataBean.ChildrenoneBean> list) {
        this.layoutInflater=context.getLayoutInflater();
        this.list = list;
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
        NoticeBean.DataBean.ChildrenoneBean bean=list.get(position);
        if (convertView==null){
            holder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.notice_list_item,null);
            holder.noticeImg= (ImageView) convertView.findViewById(R.id.noticeImg);
            holder.noticeTitle= (TextView) convertView.findViewById(R.id.noticeTitle);
            holder.noticeContent= (TextView) convertView.findViewById(R.id.noticeContent);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.noticeTitle.setText(bean.getTitle());
        holder.noticeContent.setText(bean.getIntroduce());
        if (bean.getThumb()==null){
            holder.noticeImg.setImageResource(R.mipmap.notice_null);
        }
        ImageLoader.getInstance().displayImage(Pic+bean.getThumb(),holder.noticeImg);
        return convertView;
    }
    private class ViewHolder{
        private TextView noticeTitle,noticeContent;
        private ImageView noticeImg;
    }

}
