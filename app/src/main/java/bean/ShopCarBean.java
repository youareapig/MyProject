package bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/10/27 0027.
 */
public class ShopCarBean implements Parcelable{
    private String userid;
    private String goods_id;
    private  String goods_price;
    private  String name;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean ischeck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private String image;
    private String username;
    private boolean ischeck=false;
    private int number=1;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
