package bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/12/8.
 */
public class DataBean implements Parcelable {
    private String addr_id;
    private String shr_name;
    private String shr_province;
    private String shr_city;
    private String shr_area;
    private String shr_address;
    private String shr_phone;
    private String zip_code;
    private String userid;
    private String status;

    public String getAddr_id() {
        return addr_id;
    }

    public void setAddr_id(String addr_id) {
        this.addr_id = addr_id;
    }

    public String getShr_name() {
        return shr_name;
    }

    public void setShr_name(String shr_name) {
        this.shr_name = shr_name;
    }

    public String getShr_province() {
        return shr_province;
    }

    public void setShr_province(String shr_province) {
        this.shr_province = shr_province;
    }

    public String getShr_city() {
        return shr_city;
    }

    public void setShr_city(String shr_city) {
        this.shr_city = shr_city;
    }

    public String getShr_area() {
        return shr_area;
    }

    public void setShr_area(String shr_area) {
        this.shr_area = shr_area;
    }

    public String getShr_address() {
        return shr_address;
    }

    public void setShr_address(String shr_address) {
        this.shr_address = shr_address;
    }

    public String getShr_phone() {
        return shr_phone;
    }

    public void setShr_phone(String shr_phone) {
        this.shr_phone = shr_phone;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.addr_id);
        dest.writeString(this.shr_name);
        dest.writeString(this.shr_province);
        dest.writeString(this.shr_city);
        dest.writeString(this.shr_area);
        dest.writeString(this.shr_address);
        dest.writeString(this.shr_phone);
        dest.writeString(this.zip_code);
        dest.writeString(this.userid);
        dest.writeString(this.status);
    }

    public DataBean() {
    }

    protected DataBean(Parcel in) {
        this.addr_id = in.readString();
        this.shr_name = in.readString();
        this.shr_province = in.readString();
        this.shr_city = in.readString();
        this.shr_area = in.readString();
        this.shr_address = in.readString();
        this.shr_phone = in.readString();
        this.zip_code = in.readString();
        this.userid = in.readString();
        this.status = in.readString();
    }

    public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
        @Override
        public DataBean createFromParcel(Parcel source) {
            return new DataBean(source);
        }

        @Override
        public DataBean[] newArray(int size) {
            return new DataBean[size];
        }
    };
}
