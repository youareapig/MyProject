package fragment;

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

import com.myproject.GoodsDetailsActivity;
import com.myproject.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adpter.Index_GridView_Adpter;
import adpter.Index_ListView_Adpter;
import adpter.Index_ViewPager_Adapter;
import myview.Index_GrideView;
import myview.Index_ListView;
import myview.LooperTextView;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class Index extends Fragment implements ViewPager.OnPageChangeListener {
    private Index_GrideView index_grid, index_activity;
    private Index_ListView index_list;
    private List<HashMap<String, Object>> gList, a_list, l_list;
    private HashMap<String, Object> gHashMap_by, gHashMap_mr, gHashMap_pj, gHashMap_lt, aHashMap1, aHashMap2, aHashMap3, aHashMap4, lHashMap1, lHashMap2, lHashMap3, lHashMap4;
    private ViewPager index_viewpager;
    private int[] icon = {R.mipmap.paly, R.mipmap.paly, R.mipmap.paly, R.mipmap.paly};
    private ImageView[] viewpagerImage;
    private Handler handler;
    private RadioGroup index_rgp;
    private LooperTextView notice;
    private List<String> notice_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index, container, false);
        /**加载布局*/
        index_grid = (Index_GrideView) view.findViewById(R.id.index_gridview);
        index_activity = (Index_GrideView) view.findViewById(R.id.grid_activity);
        index_viewpager = (ViewPager) view.findViewById(R.id.index_viewpager);
        index_rgp = (RadioGroup) view.findViewById(R.id.indexi_viewpage_tag);
        index_list = (Index_ListView) view.findViewById(R.id.index_listview);
        notice= (LooperTextView) view.findViewById(R.id.index_notice);
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
        /**新品推荐*/
        l_list = new ArrayList<>();
        lHashMap1 = new HashMap<>();
        lHashMap1.put("img", R.mipmap.listview1);
        lHashMap1.put("details", "加分了开发商开发就爱死了开房间爱离开就发啦就是的法律");
        lHashMap1.put("sale", "¥9.8");
        lHashMap2 = new HashMap<>();
        lHashMap2.put("img", R.mipmap.listview1);
        lHashMap2.put("details", "加分了开发商开发就爱死了开房间爱离开就发啦就是的法律");
        lHashMap2.put("sale", "¥9.8");
        lHashMap3 = new HashMap<>();
        lHashMap3.put("img", R.mipmap.listview1);
        lHashMap3.put("details", "加分了开发商开发就爱死了开房间爱离开就发啦就是的法律");
        lHashMap3.put("sale", "¥9.8");
        lHashMap4 = new HashMap<>();
        lHashMap4.put("img", R.mipmap.listview1);
        lHashMap4.put("details", "加分了开发商开发就爱死了开房间爱离开就发啦就是的法律");
        lHashMap4.put("sale", "¥9.8");
        l_list.add(lHashMap1);
        l_list.add(lHashMap2);
        l_list.add(lHashMap3);
        l_list.add(lHashMap4);
        /**活动促销*/
        a_list = new ArrayList<>();
        aHashMap1 = new HashMap<>();
        aHashMap1.put("img", R.mipmap.a2);
        aHashMap2 = new HashMap<>();
        aHashMap2.put("img", R.mipmap.a2);
        aHashMap3 = new HashMap<>();
        aHashMap3.put("img", R.mipmap.a2);
        aHashMap4 = new HashMap<>();
        aHashMap4.put("img", R.mipmap.a2);
        a_list.add(aHashMap1);
        a_list.add(aHashMap2);
        a_list.add(aHashMap3);
        a_list.add(aHashMap4);

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
        //index_activity.setAdapter(new Index_GridViewActivity_Adpter(getActivity(), a_list));
        index_list.setAdapter(new Index_ListView_Adpter(getActivity(), l_list));
        index_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), GoodsDetailsActivity.class);
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
