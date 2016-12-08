package adpter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.myproject.CompileAddressActivity;
import com.myproject.ModificationAddressActivity;
import com.myproject.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import bean.AddressBean;
import bean.UpdateAddressBean;
import utils.Global;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
public class ManageAddress_Adapter extends BaseAdapter {
    private List<AddressBean.DataBean> list;
    private LayoutInflater layoutInflater;
    private Global global;
    private String addr_id, userID;
    private SharedPreferences sharedPreferences;
    private Activity context;

    public ManageAddress_Adapter(Activity context, List<AddressBean.DataBean> list) {
        this.context = context;
        this.layoutInflater = context.getLayoutInflater();
        this.list = list;
        sharedPreferences = context.getSharedPreferences("userLogin", context.MODE_PRIVATE);
        userID = sharedPreferences.getString("userID", null);
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
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final AddressBean.DataBean dataBean = list.get(position);


        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.manageaddress_item, null);
            holder.shrName = (TextView) convertView.findViewById(R.id.shrname);
            holder.shrTel = (TextView) convertView.findViewById(R.id.shrtel);
            holder.shrAddress = (TextView) convertView.findViewById(R.id.shraddress);
            holder.order_item_pinglun = (TextView) convertView.findViewById(R.id.order_item_pinglun);
            holder.deleteaddress= (TextView) convertView.findViewById(R.id.deleteaddress);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.shrName.setText(dataBean.getShr_name());
        holder.shrTel.setText(dataBean.getShr_phone());
        holder.shrAddress.setText(dataBean.getShr_province() + dataBean.getShr_city() + dataBean.getShr_area() + dataBean.getShr_address());
        //TODO 删除地址
        holder.order_item_pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                global = new Global();
                addr_id = dataBean.getAddr_id();
                String delteUrl = global.getUrl() + "api.php/Member/memberDeladdress";
                deleteAddress(delteUrl, position);

            }
        });
        //TODO 修改地址
        holder.deleteaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                global = new Global();
                addr_id = dataBean.getAddr_id();
                String updateUrl = global.getUrl() + "api.php/Member/memberSaveaddress";
                updateAddress(updateUrl,position);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView shrName;
        private TextView shrTel;
        private TextView shrAddress;
        private TextView order_item_pinglun,deleteaddress;
    }

    //TODO 删除收货地址
    private void deleteAddress(String url, final int position) {
        Log.i("delete", "参数" + userID + "   " + addr_id);
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("userid", userID);
        params.addBodyParameter("addr_id", addr_id);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject mJson = new JSONObject(result);
                    String myCode = mJson.getString("code");
                    if (myCode.equals("3000")) {
                        //TODO 数据删除成功后移除该项，更新界面
                        list.remove(position);
                        notifyDataSetChanged();
                    } else if (myCode.equals("-3000")) {
                        Toast.makeText(context, "删除地址失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("delete", "请求成功" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("delete", "请求失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private void updateAddress(String url, final int position){
        RequestParams params=new RequestParams(url);
        params.addBodyParameter("userid",userID);
        params.addBodyParameter("addr_id",addr_id);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("delete", "请求成功" + result);
                Gson gson=new Gson();
                UpdateAddressBean bean=gson.fromJson(result,UpdateAddressBean.class);
                String name=bean.getData().getShr_name();
                String phone=bean.getData().getShr_phone();
                String province=bean.getData().getShr_province();
                String city=bean.getData().getShr_city();
                String county=bean.getData().getShr_area();
                String addr=bean.getData().getShr_address();
                String postcode=bean.getData().getZip_code();
                String addressID=bean.getData().getAddr_id();
                if (bean.getCode()==3000){
                    Intent intent=new Intent(context,ModificationAddressActivity.class);
                    intent.putExtra("updatename",name);
                    intent.putExtra("updatephone",phone);
                    intent.putExtra("updateprovince",province);
                    intent.putExtra("updatecity",city);
                    intent.putExtra("updatecounty",county);
                    intent.putExtra("updateaddr",addr);
                    intent.putExtra("updatepostcode",postcode);
                    intent.putExtra("updateaddressID",addressID);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("delete", "请求失败" );
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
}
