package utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/19.
 */
public class ToastUtil {
    private static Toast sToast=null;
    private static String sOldMsg;
    private static long sOneTime=0;
    private static long sTwoTime=0;
    public static void showToast(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
//        if (sToast==null){
//            sToast = Toast.makeText(context,str,Toast.LENGTH_SHORT);
//            sToast.show();
//            sOneTime = System.currentTimeMillis();
//        }else {
//            sTwoTime = System.currentTimeMillis();
//            if (sOldMsg.equals(str)){
//                if (sTwoTime-sOneTime>500){
//                    sToast.show();
//                }
//            }else {
//                sOldMsg = str;
//                sToast.setText(str);
//                sToast.show();
//            }
//        }
//        sOneTime = sTwoTime;
    }
}
