package com.myproject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import adpter.Notice_Adapter;
import bean.NoticeBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import utils.Global;

public class NoticeActivity extends AppCompatActivity {
    @BindView(R.id.notice_title)
    TabLayout noticeTitle;
    @BindView(R.id.notice_viewpager)
    ViewPager noticeViewpager;
    private Unbinder unbinder;
    private FragmentManager manager;
    private Global global;
    private String url;
    private List<String> list = new ArrayList<>();
    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        unbinder = ButterKnife.bind(this);
        global = new Global();
        url = global.getUrl() + "api.php/News/newstype";
        visit();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    //TODO 4个获取标题
    private void visit() {
        progressDialog = ProgressDialog.show(this, "请稍后", "玩命加载中....", true);
        RequestParams params = new RequestParams(url);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                NoticeBean noticeBean = gson.fromJson(result, NoticeBean.class);

                //TODO 标题编号分别是1,2,3,4
                if (noticeBean.getCode() == 2000) {
                    for (int i = 0; i < noticeBean.getData().getInfo().size(); i++) {
                        String a = noticeBean.getData().getInfo().get(i).getCat_name();
                        list.add(a);
                    }
                    noticeTitle.setupWithViewPager(noticeViewpager);
                    manager = NoticeActivity.this.getSupportFragmentManager();
                    noticeViewpager.setAdapter(new Notice_Adapter(list, manager));

                } else {
                    Toast.makeText(NoticeActivity.this, "获取信息失败...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("taga", "公告访问错误");
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

                return false;
            }
        });
    }
}
