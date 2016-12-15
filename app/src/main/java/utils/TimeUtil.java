package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/12/14.
 */
public class TimeUtil {
    public static String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(System.currentTimeMillis());
    }
}
