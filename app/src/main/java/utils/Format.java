package utils;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/12/12.
 */
public class Format {
    public static String formatDecimal(double decimal){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(decimal);
    }
}
