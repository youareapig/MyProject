package indexfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.myproject.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adpter.Index_GridView_Adpter;
import myview.Index_GrideView;
import myview.LooperTextView;
import myview.ObservableScrollView;
import utils.MainDownTimerView;
import utils.OnCountDownTimerListener;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class Store extends Fragment implements ObservableScrollView.ScrollViewListener,View.OnClickListener{
    private LooperTextView notice;
    private List<String> notice_list;
    private MainDownTimerView maindown;
    private RelativeLayout indextitle;
    private ObservableScrollView scrollView;
    private ImageView index_icon;
    private int height;
    private Index_GrideView index_grideView;
    private List<HashMap<String,Object>> list;
    private HashMap<String,Object> hashMap1,hashMap2,hashMap3,hashMap4,hashMap5,hashMap6,hashMap7,hashMap8;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index, container, false);
        notice = (LooperTextView) view.findViewById(R.id.index_notice);
        maindown= (MainDownTimerView) view.findViewById(R.id.maindown);
        scrollView= (ObservableScrollView) view.findViewById(R.id.scrollview);
        indextitle= (RelativeLayout) view.findViewById(R.id.indextitle);
        index_icon= (ImageView) view.findViewById(R.id.index_icon);
        index_grideView= (Index_GrideView) view.findViewById(R.id.index_gridview);
        list=new ArrayList<>();
        hashMap1=new HashMap<>();
        hashMap1.put("id","机油");
        hashMap1.put("img",R.mipmap .a1);
        hashMap2=new HashMap<>();
        hashMap2.put("id","轮胎");
        hashMap2.put("img",R.mipmap.a3);
        hashMap3=new HashMap<>();
        hashMap3.put("id","电瓶");
        hashMap3.put("img",R.mipmap.a2);
        hashMap4=new HashMap<>();
        hashMap4.put("id","全部商品");
        hashMap4.put("img",R.mipmap.a5);
        hashMap5=new HashMap<>();
        hashMap5.put("id","洗车");
        hashMap5.put("img",R.mipmap.a7);
        hashMap6=new HashMap<>();
        hashMap6.put("id","贴膜");
        hashMap6.put("img",R.mipmap.a6);
        hashMap7=new HashMap<>();
        hashMap7.put("id","喷漆");
        hashMap7.put("img",R.mipmap.a4);
        hashMap8=new HashMap<>();
        hashMap8.put("id","全部服务");
        hashMap8.put("img",R.mipmap.a5);
        list.add(hashMap1);
        list.add(hashMap2);
        list.add(hashMap3);
        list.add(hashMap4);
        list.add(hashMap5);
        list.add(hashMap6);
        list.add(hashMap7);
        list.add(hashMap8);
        index_grideView.setAdapter(new Index_GridView_Adpter(getActivity(),list));

        //TODO ScrollView拖动变色
        indextitle.setBackgroundColor(Color.argb(0, 0xfd, 0x91, 0x5b));
        ViewTreeObserver observer=index_icon.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                index_icon.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height =   index_icon.getHeight();
                index_icon.getWidth();
                scrollView.setScrollViewListener(Store.this);
            }
        });
        /**实现公告翻滚效果*/
        notice_list = new ArrayList<>();
        notice_list.add("拒绝囧途，新车必备");
        notice_list.add("新车必备,拒绝囧途!");
        notice.setTipList(notice_list);
        maindown.setDownTime(10000);
        //TODO 倒计时抢购
        maindown.setDownTimerListener(new OnCountDownTimerListener() {
            @Override
            public void onFinish() {
                Toast.makeText(getActivity(), "倒计时结束", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        //TODO 开始计时
        maindown.startDownTimer();
        return view;
    }


    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if(y<=height){
            float scale =(float) y /height;
            float alpha =  (255 * scale);

            indextitle.setBackgroundColor(Color.argb((int) alpha, 69, 156, 249));
        }


    }

    @Override
    public void onClick(View v) {

    }
}
