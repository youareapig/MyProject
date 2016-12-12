package adpter;

import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myproject.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import bean.GoodsDetailsBean;
import bean.OrderDataBean;
import bean.OrderMakeSureBean;
import bean.orderBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import utils.Global;

/**
 * Created by Administrator on 2016/12/8.
 */
public class IndentActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<OrderMakeSureBean> orderBeen = new ArrayList<>();
    public void addData(List<OrderMakeSureBean> lists){
        orderBeen=lists;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_indent_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        if (orderBeen!=null||orderBeen.isEmpty()){
            OrderMakeSureBean orderBean = orderBeen.get(position);
            ImageLoader.getInstance().displayImage(new Global().getUrl()+orderBean.getImage(),viewHolder.indentGoodsImg);
            viewHolder.indentName.setText(orderBean.getCommodityname());
            viewHolder.indentGoodsNum.setText(orderBean.getCommoditynumber());
            viewHolder.indentGoodsPrice.setText(orderBean.getCommdityprice());
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (orderBeen!=null||orderBeen.isEmpty()){
            count=orderBeen.size();
        }
        return count;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.indent_goods_img)
        ImageView indentGoodsImg;
        @BindView(R.id.indent_name)
        TextView indentName;
        @BindView(R.id.indent_goods_num)
        TextView indentGoodsNum;
        @BindView(R.id.indent_good_type)
        TextView indentGoodType;
        @BindView(R.id.indent_goods_price)
        TextView indentGoodsPrice;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
