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

import adpter.NoticeHalfList_Adapter;
import bean.NoticeHalfBean;
import utils.Global;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public class Knowledge extends Fragment {
    private Global global;
    private String url;
    private ListView listView;
    private List<NoticeHalfBean.DataBean> knowledgeList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notice_knowledge, container, false);
        global = new Global();
        url = global.getUrl() + "api.php/News/newstypeLst";
        listView = (ListView) view.findViewById(R.id.knowledge_listview);
        knowledgeUrl();
        return view;

    }

    private void knowledgeUrl() {
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("cat_id", "4");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("tag", "知识成功" + result);
                Gson gson = new Gson();
                NoticeHalfBean noticeHalfBean = gson.fromJson(result, NoticeHalfBean.class);
                knowledgeList = noticeHalfBean.getData();
                listView.setAdapter(new NoticeHalfList_Adapter(getActivity(), knowledgeList));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        NoticeHalfBean.DataBean bean = (NoticeHalfBean.DataBean) parent.getItemAtPosition(position);
                        Intent intent = new Intent(getActivity(), NoticeHtmlActivity.class);
                        intent.putExtra("web", bean.getLinkurl());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("tag", "知识失败");
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
