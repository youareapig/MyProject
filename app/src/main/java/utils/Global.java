package utils;

/**
 * Created by Administrator on 2016/11/23 0023.接口全局变量
 */
public class Global {
    private String url="http://192.168.0.126/";
    public static final String SHOPCAR="http://192.168.0.130/";
    public static final String SHOPCARDATA="http://192.168.0.125/ailunphp/trunk/api.php/Cart/carDisplay";
    public static final String SHOPCARDELETEDATA="http://192.168.0.125/ailunphp/trunk/api.php/Cart/carDel";
    public static final String SUREODER="http://192.168.0.125/ailunphp/trunk/api.php/Cart/App_Order";
    public String getUrl() {
        return url;
    }
    public static final String SHOPCARADDDATA="http://192.168.0.125/ailunphp/trunk/api.php/Cart/carAdd";
    public void setUrl(String url) {
        this.url = url;
    }
}