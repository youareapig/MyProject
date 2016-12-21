package adpter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myproject.GoodsDetailsActivity;
import com.myproject.IndentActivity;
import com.myproject.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import bean.OrderMakeSureBean;
import bean.ShopCarBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import utils.Global;
import utils.ToastUtil;

/**
 * Created by Administrator on 2016/12/6.
 */
public class ShopCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ShopCarBean> mShopCarBeans = new ArrayList<>();
    private int mCommodityNumber;
    private RecyclerView mRecyclerView;
    private static final String LOG_TAG="ShopCarAdapter";
    private Callback mCallback;
    private Context mContext;
    private double mPrice=0;
    private List<ShopCarBean> list;
    private StringBuilder mGoodsID = new StringBuilder();
    public ShopCarAdapter(RecyclerView mRecyclerView,Callback mCallback,Context mContext) {
        this.mRecyclerView = mRecyclerView;
        this.mCallback=mCallback;
        this.mContext=mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.shopcar_item, parent, false);
        return new CommodityViewHolder(view);
    }
    //添加数据
    public void addData(List<ShopCarBean> mShopCarBeans){
        this.mShopCarBeans=mShopCarBeans;
        notifyDataSetChanged();
    }
    //移除数据
    public void removeData(){
        if (mShopCarBeans!=null){
            mShopCarBeans.clear();
        }
        notifyDataSetChanged();
    }
    //选中所用商品
    public void allChecked(){
        if (mShopCarBeans!=null){
            for (ShopCarBean shopCarBean:mShopCarBeans){
                shopCarBean.setIscheck(true);
            }
            notifyDataSetChanged();
        }
    }
    //取消选择所用商品
    public void allcancel(){
        if (mShopCarBeans!=null){
            for (ShopCarBean shopCarBean:mShopCarBeans){
                shopCarBean.setIscheck(false);
            }
            notifyDataSetChanged();
        }
    }
    //计算选择商品的总价格
    public void calculatePrice(){
        mPrice=0;
        boolean isCheck=true;
        boolean isCallBackCheck=true;
        if (mShopCarBeans!=null){
            for (ShopCarBean shopCarBean:mShopCarBeans){
                if (shopCarBean.ischeck()){
                    mPrice+=(Double.parseDouble(shopCarBean.getGoods_price())*shopCarBean.getNumber());
                }
                if (!shopCarBean.ischeck()){
                    isCallBackCheck=false;
                    isCheck=false;
                }
                mCallback.callBackPrice(mPrice);
            }
            if (isCheck){
                mCallback.callBackisCheckAll(isCallBackCheck);
            }else {
                mCallback.callBackisCheckAll(isCallBackCheck);
            }
        }
    }
    //结算选中的商品
    public void clearingCommodity(){
        List<OrderMakeSureBean>  list = new ArrayList<>();
        if (mShopCarBeans!=null){
            for (ShopCarBean shopCarBean:mShopCarBeans){
                if (shopCarBean.ischeck()){
                    OrderMakeSureBean orderMakeSureBean = new OrderMakeSureBean();
                    orderMakeSureBean.setCommoditynumber(shopCarBean.getNumber()+"");
                    orderMakeSureBean.setImage(shopCarBean.getImage());
                    orderMakeSureBean.setCommodityname(shopCarBean.getName());
                    orderMakeSureBean.setCommdityprice(shopCarBean.getGoods_price());
                    orderMakeSureBean.setCommdityId(shopCarBean.getGoods_id());
                    list.add(orderMakeSureBean);
                }
            }
            if (list!=null&&list.size()>=1){
                EventBus.getDefault().postSticky(list);
                Intent intent = new Intent(mContext, IndentActivity.class);
                mContext.startActivity(intent);
            }else {
                ToastUtil.showToast(mContext,"你还未选择任何商品");
            }
        }
    }
    //删除选中商品
    public void deleteCommodity(){
        if (mShopCarBeans!=null){
            mGoodsID.delete(0,mGoodsID.length());
            list = new ArrayList<>();
            for (ShopCarBean shopCarBean:mShopCarBeans){
                if (shopCarBean.ischeck()){
                    list.add(shopCarBean);
                    mGoodsID.append(",");
                    mGoodsID.append(shopCarBean.getGoods_id());
                }
            }
            if(list!=null&&list.size()>=1){
                mCallback.callBackGoodsID(mGoodsID);
            }else {
                Toast.makeText(mContext,"你还未选择任何商品",Toast.LENGTH_LONG).show();
            }
        }
    }
    public void makeSureDelete(){
        for (int i=0;i<list.size();i++){
            mShopCarBeans.remove(list.get(i));
            if (mShopCarBeans.size()==0){
                mCallback.callBackVisibility();
            }
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final CommodityViewHolder viewHolder = (CommodityViewHolder) holder;
        Log.v(LOG_TAG,"----------->"+mShopCarBeans.size());
        Log.v(LOG_TAG,"--------------->"+position);
        if (mShopCarBeans==null||mShopCarBeans.isEmpty())return;
            final ShopCarBean listBean = mShopCarBeans.get(position);
            mCommodityNumber = mShopCarBeans.get(position).getNumber();
            viewHolder.mShopcarChoose.setOnCheckedChangeListener(null);
            viewHolder.mShopcarNumber.setText(listBean.getNumber()+"");
            ImageLoader.getInstance().displayImage(new Global().getUrl()+listBean.getImage(),viewHolder.mShopcarItemImg);
            viewHolder.mShopcarItemName.setText(listBean.getName());
            viewHolder.mShopcarItemSale.setText(listBean.getGoods_price());
            viewHolder.mShopcarChoose.setChecked(listBean.ischeck());
            //页面跳转到详细商品界面
            viewHolder.mShopcarItemName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), GoodsDetailsActivity.class);
                    intent.putExtra("goodsID",listBean.getGoods_id());
                    view.getContext().startActivity(intent);
                }
            });

            viewHolder.mShopcarChoose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    /*item在列表滑动或则渲染layout的时候不能调用notifyItemChanged更新试图，因此在此利用Handler队列进行
                    * 更新*/
                    viewHolder.mShopcarChoose.post(new Runnable() {
                        @Override
                        public void run() {
                            calculatePrice();
                            notifyItemChanged(position);
                        }
                    });
                    Log.v(LOG_TAG,"-------------->"+b);
                    mShopCarBeans.get(position).setIscheck(b);
                }
            });
            viewHolder.mShopcarAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count = mShopCarBeans.get(position).getNumber()+1;
                    mShopCarBeans.get(position).setNumber(count);
                    viewHolder.mShopcarNumber.post(new Runnable() {
                        @Override
                        public void run() {
                            calculatePrice();
                            notifyItemChanged(position);
                        }
                    });
                    requestNumber(Global.SHOPCARADDNUMBER,mShopCarBeans.get(position).getUserid(),mShopCarBeans.get(position).getGoods_id());
                }
            });
            viewHolder.mShopcarSubtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int num = mShopCarBeans.get(position).getNumber();
                    if (num>1){
                        int count = num-1;
                        mShopCarBeans.get(position).setNumber(count);

                    }
                    viewHolder.mShopcarNumber.post(new Runnable() {
                        @Override
                        public void run() {
                            calculatePrice();
                            notifyItemChanged(position);
                        }
                    });
                    requestNumber(Global.SHOPCARREDUCENUMBER,mShopCarBeans.get(position).getUserid(),mShopCarBeans.get(position).getGoods_id());
                }
            });
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mShopCarBeans!=null){
            count=mShopCarBeans.size();
        }
        return count;
    }
    public void requestNumber(String url,String userid,String goods_id){
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("userid",userid);
        params.addBodyParameter("goods_id",goods_id);
        x.http().post(params, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.v(LOG_TAG,"------------->"+result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class CommodityViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.shopcar_choose)
        CheckBox mShopcarChoose;
        @BindView(R.id.shopcar_item_img)
        ImageView mShopcarItemImg;
        @BindView(R.id.shopcar_item_name)
        TextView mShopcarItemName;
        @BindView(R.id.shopcar_item_sale)
        TextView mShopcarItemSale;
        @BindView(R.id.shopcar_add)
        TextView mShopcarAdd;
        @BindView(R.id.shopcar_number)
        TextView mShopcarNumber;
        @BindView(R.id.shopcar_subtract)
        TextView mShopcarSubtract;

        public CommodityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface Callback{
        void callBackPrice(double price);
        void callBackGoodsID(StringBuilder goodsID);
        void callBackisCheckAll(boolean b);
        void callBackVisibility();
    }
}
