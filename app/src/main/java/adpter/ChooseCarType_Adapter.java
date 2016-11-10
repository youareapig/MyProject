package adpter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myproject.R;

import java.util.List;

import bean.CarBean;

/**
 * Created by Administrator on 2016/11/10 0010.
 */
public class ChooseCarType_Adapter extends BaseAdapter{

    private List<CarBean> carList;
    private LayoutInflater layoutInflater;

    public ChooseCarType_Adapter(Context context, List<CarBean> carList) {
        this.layoutInflater=((Activity) context).getLayoutInflater();
        this.carList = carList;
    }

    @Override
    public int getCount() {
        return carList.size();
    }

    @Override
    public Object getItem(int position) {
        return carList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        CarBean carBean=carList.get(position);
        String currentLetter = carBean.getPinyin().charAt(0) + "";
        String string=null;
        if (position == 0) {
            string = currentLetter;
        } else {
            //上一个人的拼音的首字母
            String preLetter = carList.get(position - 1).getPinyin().charAt(0) + "";
            if (!TextUtils.equals(preLetter, currentLetter)) {
                string = currentLetter;
            }
        }
        if (convertView==null){
            holder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.choosecar_item,null);
            holder.carIndex= (TextView) convertView.findViewById(R.id.choosecar_item_index);
            holder.carName= (TextView) convertView.findViewById(R.id.choosecar_item_name);
            holder.carImage= (ImageView) convertView.findViewById(R.id.choosecar_item_icon);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.carIndex.setVisibility(string==null?View.GONE:View.VISIBLE);
        holder.carIndex.setText(currentLetter);
        holder.carName.setText(carBean.getCarName());
        holder.carImage.setImageResource(carBean.getCarIcon());
        return convertView;
    }
    class ViewHolder{
        TextView carName,carIndex;
        ImageView carImage;
    }
}
