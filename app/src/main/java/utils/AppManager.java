package utils;

import android.app.Activity;

import java.util.Collection;
import java.util.Collections;
import java.util.IllegalFormatCodePointException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */
/*
* 创建activity管理类*/
public class AppManager {
    private static List<Activity> mActivityLists = Collections.synchronizedList(new LinkedList<Activity>());
    private static volatile AppManager mAppManager;
    private AppManager() {
    }
//    单例模式采用双加锁保证线程安全
    public static  AppManager getSingleInstance(){
        if (mAppManager==null){
            synchronized (AppManager.class){
                if (mAppManager==null){
                    mAppManager = new AppManager();
                }
            }
        }
        return mAppManager;
    }
    /*添加一个activity到堆栈*/
    public void addActivity(Activity activity){
        mActivityLists.add(activity);
    }
    /*删除一个activity*/
    public void removeActivity(Activity activity){
        mActivityLists.remove(activity);
    }
    /*获取当前activity*/
    public Activity currentActivity(){
        if (mActivityLists==null||mActivityLists.isEmpty()){
            return null;
        }else {
            return mActivityLists.get(mActivityLists.size()-1);
        }
    }
    /*结束当前activity*/
    public  void finishCurrentActivity(){
        if (mActivityLists==null||mActivityLists.isEmpty()){
            return;
        }else {
            Activity activity = mActivityLists.get(mActivityLists.size()-1);
           finishActivity(activity);
        }
    }
    /*结束指定的activity*/
    public  void finishActivity(Activity activity){
        if (activity!=null){
            mActivityLists.remove(activity);
            activity.finish();
            activity=null;
        }
    }
    /*结束指定类名的activity*/
    public void finishActivity(Class<?> cls){
        for (Activity activity:mActivityLists){
            if (activity.getClass().equals(cls)){
                finishActivity(activity);
            }
        }
    }
    /*结束所有activity*/
    public void finishAll(){
        if (mActivityLists==null||mActivityLists.isEmpty()){
            return;
        }else {
            for (int i=0;i<mActivityLists.size();i++){
                if (mActivityLists.get(i)!=null){
                    mActivityLists.get(i).finish();
                }
            }
        }
        mActivityLists.clear();
    }
}
