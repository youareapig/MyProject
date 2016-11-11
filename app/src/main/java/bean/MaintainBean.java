package bean;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
public class MaintainBean {
    private int maintainIcon;
    private String storeName;
    private String goodsName;
    private String maintainTime;
    private double maintainMoney;

    public int getMaintainIcon() {
        return maintainIcon;
    }

    public void setMaintainIcon(int maintainIcon) {
        this.maintainIcon = maintainIcon;
    }

    public MaintainBean(String storeName, String goodsName, String maintainTime, double maintainMoney, int maintainIcon) {
        this.storeName = storeName;
        this.goodsName = goodsName;
        this.maintainTime = maintainTime;
        this.maintainMoney = maintainMoney;
        this.maintainIcon=maintainIcon;

    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getMaintainTime() {
        return maintainTime;
    }

    public void setMaintainTime(String maintainTime) {
        this.maintainTime = maintainTime;
    }

    public double getMaintainMoney() {
        return maintainMoney;
    }

    public void setMaintainMoney(double maintainMoney) {
        this.maintainMoney = maintainMoney;
    }
}
