package indexfragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.myproject.GoodsListActivity;
import com.myproject.NoticeActivity;
import com.myproject.R;
import com.myproject.SearchActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adpter.GoodsDetails_Viewpage_Adapter;
import adpter.IndexBannerAdapter;
import adpter.Index_GridView_Adpter;
import bean.GoodsList_Bean;
import bean.IndexBean;
import myview.Index_GrideView;
import myview.LooperTextView;
import myview.ObservableScrollView;
import utils.Global;
import utils.MainDownTimerView;
import utils.OnCountDownTimerListener;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class Store extends Fragment implements ObservableScrollView.ScrollViewListener, View.OnClickListener, ViewPager.OnPageChangeListener {
    private LooperTextView notice;
    private List<String> notice_list;
    private List<IndexBean.DataBean.AdBean> indexBannerList;
    private MainDownTimerView maindown;
    private RelativeLayout indextitle, morenotice;
    private ObservableScrollView scrollView;
    private ImageView index_search;
    private int height;
    private Index_GrideView index_grideView;
    private List<HashMap<String, Object>> list;
    private HashMap<String, Object> hashMap1, hashMap2, hashMap3, hashMap4, hashMap5, hashMap6, hashMap7, hashMap8;
    private TextView index_searchtext;
    private Global global;
    private String indexNoticeUrl;
    private ViewPager index_viewpager;
    private ViewGroup indexviewGroup;
    private ImageView[] indexTips, indexBannerImage;
    private Handler handler;
    private ProgressDialog progressDialog=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index, container, false);
        global = new Global();
        indexNoticeUrl = global.getUrl() + "api.php/Index/index";
        notice = (LooperTextView) view.findViewById(R.id.index_notice);
        maindown = (MainDownTimerView) view.findViewById(R.id.maindown);
        scrollView = (ObservableScrollView) view.findViewById(R.id.scrollview);
        indextitle = (RelativeLayout) view.findViewById(R.id.indextitle);
        index_grideView = (Index_GrideView) view.findViewById(R.id.index_gridview);
        index_searchtext = (TextView) view.findViewById(R.id.index_searchtext);
        index_search = (ImageView) view.findViewById(R.id.index_search);
        morenotice = (RelativeLayout) view.findViewById(R.id.morenotice);
        index_viewpager = (ViewPager) view.findViewById(R.id.index_viewpager);
        indexviewGroup = (ViewGroup) view.findViewById(R.id.indexviewGroup);
        morenotice.setOnClickListener(this);
        index_search.setOnClickListener(this);
        index_searchtext.setOnClickListener(this);


        list = new ArrayList<>();
        hashMap1 = new HashMap<>();
        hashMap1.put("id", "机油");
        hashMap1.put("img", R.mipmap.a1);
        hashMap2 = new HashMap<>();
        hashMap2.put("id", "轮胎");
        hashMap2.put("img", R.mipmap.a3);
        hashMap3 = new HashMap<>();
        hashMap3.put("id", "电瓶");
        hashMap3.put("img", R.mipmap.a2);
        hashMap4 = new HashMap<>();
        hashMap4.put("id", "全部商品");
        hashMap4.put("img", R.mipmap.a5);
        hashMap5 = new HashMap<>();
        hashMap5.put("id", "洗车");
        hashMap5.put("img", R.mipmap.a7);
        hashMap6 = new HashMap<>();
        hashMap6.put("id", "贴膜");
        hashMap6.put("img", R.mipmap.a6);
        hashMap7 = new HashMap<>();
        hashMap7.put("id", "喷漆");
        hashMap7.put("img", R.mipmap.a4);
        hashMap8 = new HashMap<>();
        hashMap8.put("id", "全部服务");
        hashMap8.put("img", R.mipmap.a5);
        list.add(hashMap1);
        list.add(hashMap2);
        list.add(hashMap3);
        list.add(hashMap4);
        list.add(hashMap5);
        list.add(hashMap6);
        list.add(hashMap7);
        list.add(hashMap8);
        index_grideView.setAdapter(new Index_GridView_Adpter(getActivity(), list));
        index_grideView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent =new Intent(getActivity(),GoodsListActivity.class);
                        intent.putExtra("seach", "机油");
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 =new Intent(getActivity(),GoodsListActivity.class);
                        intent1.putExtra("seach", "轮胎");
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 =new Intent(getActivity(),GoodsListActivity.class);
                        intent2.putExtra("seach", "电瓶");
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 =new Intent(getActivity(),GoodsListActivity.class);
                        intent3.putExtra("seach", "1");
                        startActivity(intent3);
                        break;
                }
            }
        });
        //TODO ScrollView拖动变色
        indextitle.setBackgroundColor(Color.argb(0, 0xfd, 0x91, 0x5b));
        ViewTreeObserver observer = index_viewpager.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                index_viewpager.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = index_viewpager.getHeight();
                index_viewpager.getWidth();
                scrollView.setScrollViewListener(Store.this);
            }
        });

        //TODO 请求网络接口
        indexNotice();


        //TODO 倒计时抢购
        maindown.setDownTimerListener(new OnCountDownTimerListener() {
            @Override
            public void onFinish() {
                //结束时回调函数
            }
        });
        //TODO 开始计时
        //maindown.startDownTimer();
        return view;
    }


    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= height) {
            float scale = (float) y / height;
            float alpha = (255 * scale);

            indextitle.setBackgroundColor(Color.argb((int) alpha, 69, 156, 249));
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.index_searchtext:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.morenotice:
                Intent intent1 = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent1);
                break;
        }
    }

    private void indexNotice() {
        progressDialog = ProgressDialog.show(getActivity(), "请稍后", "玩命加载中...", true);
        RequestParams params = new RequestParams(indexNoticeUrl);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("tag", result);
                Gson gson = new Gson();
                IndexBean indexBean = gson.fromJson(result, IndexBean.class);
                indexBannerList = indexBean.getData().getAd();
                //TODO 首页轮播
                indexTips = new ImageView[indexBannerList.size()];
                //TODO 圆点导航数组
                for (int i = 0; i < indexTips.length; i++) {
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(15, 15));
                    indexTips[i] = imageView;
                    if (i == 0) {
                        indexTips[i].setBackgroundResource(R.drawable.viewpage_check);
                    } else {
                        indexTips[i].setBackgroundResource(R.drawable.viewpage_goods);

                    }
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewPager.LayoutParams.WRAP_CONTENT,
                            ViewPager.LayoutParams.WRAP_CONTENT));
                    layoutParams.leftMargin = 10;
                    layoutParams.rightMargin = 10;
                    indexviewGroup.addView(imageView);
                }
                //TODO 图片数组
                indexBannerImage = new ImageView[indexBannerList.size()];
                for (int i = 0; i < indexBannerImage.length; i++) {
                    ImageView imageView = new ImageView(getActivity());
                    indexBannerImage[i] = imageView;
                    ImageLoader.getInstance().displayImage(global.getUrl() + indexBannerList.get(i).getImage_src(), imageView);

                }
                index_viewpager.setOnPageChangeListener(Store.this);
                //index_viewpager.setCurrentItem((indexBannerImage.length) * 100);
                index_viewpager.setAdapter(new IndexBannerAdapter(indexBannerImage));
                handler=new Handler(){
                    int bannerNo=0;
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if (index_viewpager.getCurrentItem()==indexBannerImage.length-1){
                            bannerNo=0;
                        }else {
                            bannerNo=index_viewpager.getCurrentItem()+1;
                        }
                        index_viewpager.setCurrentItem(bannerNo,true);
                    }
                };
                new MyThread().start();
                /**实现公告翻滚效果*/
                notice_list = new ArrayList<>();
                if (indexBean.getCode() == 1000) {
                    for (int i = 0; i < indexBean.getData().getNewsInfo().size(); i++) {
                        String b = indexBean.getData().getNewsInfo().get(i).getTitle();
                        notice_list.add(b);
                    }
                    notice.setTipList(notice_list);
                    maindown.setDownTime(10000);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setImageBackground(position % indexBannerImage.length);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setImageBackground(int selectItems) {
        for (int i = 0; i < indexTips.length; i++) {
            if (i == selectItems) {
                indexTips[i].setBackgroundResource(R.drawable.viewpage_check);
            } else {
                indexTips[i].setBackgroundResource(R.drawable.viewpage_goods);
            }
        }
    }
    private class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true){
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }
    }
}
