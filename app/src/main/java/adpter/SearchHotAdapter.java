package adpter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myproject.GoodsListActivity;
import com.myproject.R;

import java.util.List;

import bean.HotWordsBean;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public class SearchHotAdapter extends BaseAdapter {

    private List<HotWordsBean.DataBean> list;
    private LayoutInflater layoutInflater;

    public SearchHotAdapter(Context context, List<HotWordsBean.DataBean> list) {
        this.list = list;
        this.layoutInflater = ((Activity) context).getLayoutInflater();
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
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
        final ViewHolder holder ;
        HotWordsBean.DataBean bean=list.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.hotserach_item, null);
            holder.textView = (TextView) convertView.findViewById(R.id.hotsearchText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(bean.getKe_name());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GoodsListActivity.class);
                intent.putExtra("seach", holder.textView.getText().toString());
                v.getContext().startActivity(intent);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView textView;
    }
}
