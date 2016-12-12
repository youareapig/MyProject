package noticfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.myproject.NoticeHtmlActivity;
import com.myproject.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import adpter.NoticeList_Adapter;
import bean.NoticeBean;
import utils.Global;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public class Notice extends Fragment{
    private Global global;
    private String url;
    private List<NoticeBean.DataBean.ChildrenoneBean> noticeList;
    private ListView listview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.notice_notice,container,false);
        global=new Global();
        url=global.getUrl()+"api.php/News/newstype";
        listview= (ListView) view.findViewById(R.id.notice_listview);
        visit();

        return view;

    }
    private void visit(){
        RequestParams params=new RequestParams(url);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("tag","公告成功"+result);
                Gson gson=new Gson();
                NoticeBean noticeBean=gson.fromJson(result,NoticeBean.class);
                noticeList=noticeBean.getData().getChildrenone();
                //TODO 标题编号分别是1,2,3,4
                if (noticeBean.getCode()==2000){
                    Log.i("tag","获取成功");
                    listview.setAdapter(new NoticeList_Adapter(getActivity(),noticeList));
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            NoticeBean.DataBean.ChildrenoneBean bean= (NoticeBean.DataBean.ChildrenoneBean) parent.getItemAtPosition(position);
                            Intent intent=new Intent(getActivity(), NoticeHtmlActivity.class);
                            intent.putExtra("web",bean.getLinkurl());
                            startActivity(intent);
                            Log.i("tag","链接地址："+bean.getLinkurl());
                        }
                    });
                }else {
                    Log.i("tag","获取成功");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("tag","公告失败");
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
