package bean;

import android.os.Parcelable;

/**
 * Created by Administrator on 2016/12/10.
 */
public class OrderMakeSureBean {
    private String image;
    private String commodityname;
    private String commoditynumber;
    private String commdityprice;
    private String commdityId;

    public String getCommdityId() {
        return commdityId;
    }

    public void setCommdityId(String commdityId) {
        this.commdityId = commdityId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCommodityname() {
        return commodityname;
    }

    public void setCommodityname(String commodityname) {
        this.commodityname = commodityname;
    }

    public String getCommoditynumber() {
        return commoditynumber;
    }

    public void setCommoditynumber(String commoditynumber) {
        this.commoditynumber = commoditynumber;
    }

    public String getCommdityprice() {
        return commdityprice;
    }

    public void setCommdityprice(String commdityprice) {
        this.commdityprice = commdityprice;
    }
}
