package utils;

/**
 * Created by Administrator on 2016/11/23 0023.接口全局变量
 */
public class Global {
    public  String url="https://www.ailunwang.cn/";
    public static final String URL="https://www.ailunwang.cn/";
    public static final String SHOPURL="http://192.168.0.125/ailunphp/trunk/";
    public static final String SHOPCARDATA=URL+"/api.php/Cart/carDisplay";
    public static final String SHOPCARDELETEDATA=URL+"/api.php/Cart/carDel";
    public static final String SUREODER=URL+"/api.php/Cart/App_Order";
    public static final String SHOPCARADDNUMBER=URL+"/api.php/Cart/goods_number_plus";
    public static final String SHOPCARREDUCENUMBER=URL+"/api.php/Cart/goods_number_reduce";
    public static final String SHOPCARORDER=SHOPURL+"/api.php/Order/order";
    public String getUrl() {
        return url;
    }
    public static final String SHOPCARADDDATA=URL+"/api.php/Cart/carAdd";
    public static final String APP_ID = "wx426b3015555a46be";
    public static final String ZFBAPP_ID = "2016121604333909";
    public void setUrl(String url) {
        this.url = url;
    }
}