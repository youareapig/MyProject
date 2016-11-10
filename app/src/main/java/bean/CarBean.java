package bean;

import utils.PinyinUtils;

/**
 * Created by Administrator on 2016/11/10 0010.
 */
public class CarBean implements Comparable<CarBean>{
    private String carName;
    private int carIcon;
    private String pinyin;

    public CarBean(String carName, int carIcon) {
        this.carName = carName;
        this.carIcon = carIcon;
        this.pinyin= PinyinUtils.getPinyin(carName);
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCarIcon() {
        return carIcon;
    }

    public void setCarIcon(int carIcon) {
        this.carIcon = carIcon;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public int compareTo(CarBean carBean) {
        return this.pinyin.compareTo(carBean.getPinyin());
    }
}
