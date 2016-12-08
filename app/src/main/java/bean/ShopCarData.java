package bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
public class ShopCarData  {
    private int  code;
    private String message;

    public List<ShopCarBean> getData() {
        return data;
    }

    public void setData(List<ShopCarBean> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private List<ShopCarBean> data;
}
