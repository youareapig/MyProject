package indexfragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.myproject.R;
import com.myproject.SearchActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import adpter.ClassfiyHalf_Adapter;
import adpter.Classfiy_Adapter;
import bean.ClassifyBean;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class Classify extends Fragment implements View.OnClickListener {
    private static final String URL = "http://192.168.0.105/api.php/Goods/category";
    private RelativeLayout classfiy_search;
    private ListView left_view, main_view;
    private Classfiy_Adapter classfiyAdapter;
    private ProgressDialog progressDialog = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classify, container, false);
        inivt();
        left_view = (ListView) view.findViewById(R.id.left_listview);
        main_view = (ListView) view.findViewById(R.id.main_listview);
        classfiy_search = (RelativeLayout) view.findViewById(R.id.classfiy_search);
        classfiy_search.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.classfiy_search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }

    public void inivt() {
        progressDialog = ProgressDialog.show(getActivity(), "请稍后", "玩命加载中....", true);
        RequestParams params = new RequestParams(URL);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                cache(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "服务器故障,请稍后重试", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                progressDialog.cancel();
            }

            @Override
            public boolean onCache(String result) {
                cache(result);
                return true;
            }
        });

    }

    public void cache(String result) {
        Gson gson = new Gson();
        ClassifyBean classityBean = gson.fromJson(result, ClassifyBean.class);
        //TODO 左边一级分类
        classfiyAdapter = new Classfiy_Adapter(getContext(), classityBean.getData());
        left_view.setAdapter(classfiyAdapter);
        //TODO 默认展示第一项数据
        ClassfiyHalf_Adapter adapter = new ClassfiyHalf_Adapter(getContext(), classityBean.getData().get(0).getChildren());
        main_view.setAdapter(adapter);
        left_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO 设置选中状态
                classfiyAdapter.setSelectItem(position);
                classfiyAdapter.notifyDataSetInvalidated();
                ClassifyBean.DataBean dataBean = (ClassifyBean.DataBean) parent.getItemAtPosition(position);
                Log.d("img", "图片" + dataBean.getLink_url());
                ClassfiyHalf_Adapter adapter = new ClassfiyHalf_Adapter(getContext(), dataBean.getChildren());
                main_view.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });


    }


}
