package adpter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.myproject.R;

import java.util.List;

import bean.ShopCar_ListBean;
import indexfragment.ShopCar;

/**
 * Created by Administrator on 2016/10/27 0027.
 */
public class ShopCar_ListView_Adapter extends BaseAdapter {
    private List<ShopCar_ListBean> shopcar_list;
    private LayoutInflater layoutInflater;
    private OnItemCheckedChangeListener onItemCheckedChangeListener;
    private OnCountChangeListener onCountChangeListener;

    public ShopCar_ListView_Adapter(Context context, ShopCar shopCar, List<ShopCar_ListBean> shopcar_list) {
        this.layoutInflater = ((Activity) context).getLayoutInflater();
        this.onItemCheckedChangeListener = shopCar;
        this.onCountChangeListener = shopCar;
        this.shopcar_list = shopcar_list;

    }

    public void setlistGoodsBean(List<ShopCar_ListBean> shopcar_list) {
        this.shopcar_list = shopcar_list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (shopcar_list != null) {
            return shopcar_list.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (shopcar_list!=null){
            return shopcar_list.get(position);
        }else {
            return null;
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.shopcar_item, parent, false);
            holder.img = (ImageView) convertView.findViewById(R.id.shopcar_item_img);
            holder.name = (TextView) convertView.findViewById(R.id.shopcar_item_name);
            holder.sale = (TextView) convertView.findViewById(R.id.shopcar_item_sale);
            holder.choose = (CheckBox) convertView.findViewById(R.id.shopcar_choose);
            holder.add = (TextView) convertView.findViewById(R.id.shopcar_subtract);
            holder.subtract = (TextView) convertView.findViewById(R.id.shopcar_add);
            holder.number = (TextView) convertView.findViewById(R.id.shopcar_number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        setShopCarView(holder, position);
        return convertView;
    }

    public void initListener(final ViewHolder holder, final int position) {
        /**增加数量*/
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(holder.number.getText().toString());
                onCountChangeListener.onCountChangeListener(++count, position);
            }
        });
        /**减少数量*/
        holder.subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(holder.number.getText().toString());
                int i = unAddNum(count);
                onCountChangeListener.onCountChangeListener(i, position);
            }
        });
        /**勾选功能*/
        holder.choose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.choose.isChecked()) {
                    onItemCheckedChangeListener.onItemCheckedChangeListener(true, position);
                    Log.d("taga", "已经执行选中" + position);

                } else {
                    onItemCheckedChangeListener.onItemCheckedChangeListener(false, position);
                    Log.d("taga", "已经执行未选中" + position);
                }


            }
        });

    }

    public void setShopCarView(ViewHolder holder, int position) {
        initListener(holder, position);
        ShopCar_ListBean shopCar_listBean = shopcar_list.get(position);
        int count = shopCar_listBean.getEt_count();
        boolean isCheck = shopCar_listBean.getIsCheck();
        /**设置价格*/
        holder.sale.setText(shopCar_listBean.getPrize());
        /**设置商品个数*/
        holder.number.setText(count + "");
        holder.choose.setChecked(isCheck);
        holder.img.setImageResource(shopCar_listBean.getShopcar_img());
        holder.name.setText(shopCar_listBean.getShopcar_name());
        holder.sale.setText(shopCar_listBean.getPrize());

    }

    class ViewHolder {
        ImageView img;
        TextView name, sale, add, subtract, number;
        CheckBox choose;
    }

    public interface OnItemCheckedChangeListener {
        void onItemCheckedChangeListener(boolean isCheck, int position);
    }

    public interface OnCountChangeListener {
        void onCountChangeListener(int count, int position);
    }

    public int unAddNum(int buy_count) {
        if (buy_count <= 1) {
            buy_count = 1;
        } else {
            buy_count--;
        }
        return buy_count;
    }
}
