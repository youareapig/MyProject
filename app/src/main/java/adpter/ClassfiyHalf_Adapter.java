package adpter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myproject.GoodsListActivity;
import com.myproject.R;

import java.util.List;

import bean.ClassifyBean;
import myview.Index_GrideView;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class ClassfiyHalf_Adapter extends BaseAdapter {

    private int defItem=0;
    private LayoutInflater layoutInflater;
    private List<ClassifyBean.DataBean.ChildrenBean> list ;


    public ClassfiyHalf_Adapter(Context context, List<ClassifyBean.DataBean.ChildrenBean> list) {
        this.list=list;
        this.layoutInflater=((Activity) context).getLayoutInflater();

    }
    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        if (list==null){
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        if (list==null){
            return null;
        }
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        ClassifyBean.DataBean.ChildrenBean childrenBean=list.get(position);
        if (convertView==null){
            holder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.classfiy_grid_item,null);
            holder.title= (TextView) convertView.findViewById(R.id.grid_title);
            holder.grideView= (Index_GrideView) convertView.findViewById(R.id.main_gridview);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.title.setText(childrenBean.getCat_name());


        holder.grideView.setAdapter(new ClassfiyGrid_Adapter(convertView.getContext(),childrenBean.getChildren1()));

        holder.grideView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO GridView每一项对应的商品ＩＤ
                ClassifyBean.DataBean.ChildrenBean.Children1Bean children1Bean = (ClassifyBean.DataBean.ChildrenBean.Children1Bean) parent.getItemAtPosition(position);
                Log.d("taglist","汽车id:"+children1Bean.getCat_id());
                Intent intent=new Intent(view.getContext(), GoodsListActivity.class);
                intent.putExtra("goodsID",children1Bean.getCat_id());
                view.getContext().startActivity(intent);

            }
        });
        return convertView;
    }
    class ViewHolder{
        TextView title;
        Index_GrideView grideView;
    }


}
