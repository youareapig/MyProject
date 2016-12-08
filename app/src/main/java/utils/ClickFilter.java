package utils;

import com.nostra13.universalimageloader.utils.L;

/**
 * Created by Administrator on 2016/12/6.
 */
public class ClickFilter {
    public static final long INTERVAL = 500;
    public static  long LASTTIME=0;
    public static boolean fliter(){
        long time = System.currentTimeMillis();
        LASTTIME=time;
        if (time- LASTTIME>INTERVAL){
            return false;
        }else {
            return true;
        }
    }
}
