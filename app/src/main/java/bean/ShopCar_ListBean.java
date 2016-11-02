package bean;

/**
 * Created by Administrator on 2016/10/27 0027.
 */
public class ShopCar_ListBean {
    private int shopcar_img;
    private String shopcar_name;
    private  String shopcar_sale;
    private int et_count = 1;
    private boolean isCheck = false;
    private String prize;

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean check) {
        isCheck = check;
    }

    public int getEt_count() {
        return et_count;
    }

    public void setEt_count(int et_count) {
        this.et_count = et_count;
    }

    public ShopCar_ListBean(String shopcar_name, String prize, int shopcar_img) {
        this.shopcar_name = shopcar_name;
        this.prize = prize;
        this.shopcar_img = shopcar_img;
    }

    public int getShopcar_img() {
        return shopcar_img;
    }

    public void setShopcar_img(int shopcar_img) {
        this.shopcar_img = shopcar_img;
    }

    public String getShopcar_name() {
        return shopcar_name;
    }

    public void setShopcar_name(String shopcar_name) {
        this.shopcar_name = shopcar_name;
    }

}
