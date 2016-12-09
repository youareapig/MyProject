package utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import bean.OrderDataBean;

/**
 * Created by Administrator on 2016/12/9.
 */
public class RequestUtil {
    public static void reQuestSureOrder(final Context mContext, String userid, String goods_id, String goodsNumber){
        RequestParams params = new RequestParams(Global.SUREODER);
        params.addBodyParameter("userid",userid);
        params.addBodyParameter("goods_id",goods_id);
        params.addBodyParameter("number",goodsNumber);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(mContext, "下单成功", Toast.LENGTH_SHORT).show();
                Log.v("goooooooooooooo","------------>"+result);
                Gson gson = new Gson();
                OrderDataBean orderDataBean= gson.fromJson(result,OrderDataBean.class);
                EventBus.getDefault().postSticky(orderDataBean);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(mContext, "下单失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}
