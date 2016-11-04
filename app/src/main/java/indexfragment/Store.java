package indexfragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.myproject.R;
import com.myproject.SearchActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adpter.Index_GridView_Adpter;
import adpter.Index_ViewPager_Adapter;
import myview.Index_GrideView;
import myview.LooperTextView;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class Store extends Fragment implements ViewPager.OnPageChangeListener {
    private Index_GrideView index_grid;
    private List<HashMap<String, Object>> gList;
    private HashMap<String, Object> gHashMap_by, gHashMap_mr, gHashMap_pj, gHashMap_lt;
    private ViewPager index_viewpager;
    private int[] icon = {R.mipmap.paly, R.mipmap.paly, R.mipmap.paly, R.mipmap.paly};
    private ImageView[] viewpagerImage;
    private Handler handler;
    private RadioGroup index_rgp;
    private LooperTextView notice;
    private List<String> notice_list;
    private ImageView store_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index, container, false);
        /**加载布局*/
        index_grid = (Index_GrideView) view.findViewById(R.id.index_gridview);
        index_viewpager = (ViewPager) view.findViewById(R.id.index_viewpager);
        index_rgp = (RadioGroup) view.findViewById(R.id.indexi_viewpage_tag);
        notice= (LooperTextView) view.findViewById(R.id.index_notice);
        store_search= (ImageView) view.findViewById(R.id.store_search);
        /**实现跑马灯效果*/
        notice_list=new ArrayList<>();
        notice_list.add("拒绝囧途，新车必备");
        notice_list.add("新车必备,拒绝囧途!");
        notice.setTipList(notice_list);
        /**首页轮播*/
        viewpagerImage = new ImageView[icon.length];
        for (int i = 0; i < icon.length; i++) {
            ImageView iv = new ImageView(getActivity());
            iv.setImageResource(icon[i]);
            viewpagerImage[i] = iv;
        }
        index_viewpager.setAdapter(new Index_ViewPager_Adapter(viewpagerImage));
        index_viewpager.addOnPageChangeListener(this);
        /**无限循环*/
        handler = new Handler() {
            int position = 0;

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (index_viewpager.getCurrentItem() == icon.length - 1) {
                    position = 0;
                } else {
                    position = index_viewpager.getCurrentItem() + 1;
                }
                index_viewpager.setCurrentItem(position, true);
            }
        };

        /**开启线程，实现3秒自动播放*/
        new Auto().start();

        /**分类*/
        gList = new ArrayList<>();
        gHashMap_by = new HashMap();
        gHashMap_by.put("img", R.mipmap.hairdressing);
        gHashMap_by.put("id", "美容");

        gHashMap_mr = new HashMap<>();
        gHashMap_mr.put("img", R.mipmap.penqi);
        gHashMap_mr.put("id", "维修保养");

        gHashMap_pj = new HashMap();
        gHashMap_pj.put("img", R.mipmap.spraylacquer);
        gHashMap_pj.put("id", "喷漆");

        gHashMap_lt = new HashMap();
        gHashMap_lt.put("img", R.mipmap.sweep);
        gHashMap_lt.put("id", "扫一扫");

        gList.add(gHashMap_by);
        gList.add(gHashMap_mr);
        gList.add(gHashMap_pj);
        gList.add(gHashMap_lt);
        /**添加适配器*/
        index_grid.setAdapter(new Index_GridView_Adpter(getActivity(), gList));
        /**搜索*/
        store_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton dot = (RadioButton) index_rgp.getChildAt(position);
        dot.setChecked(true);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class Auto extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
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
