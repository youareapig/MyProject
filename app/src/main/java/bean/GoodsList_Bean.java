package bean;

/**
 * Created by Administrator on 2016/10/31 0031.
 */
public class GoodsList_Bean {
    private int goodslist_img;
    private String goodslist_img_introduce;
    private double goodslist_sale;
    private int goodslist_person;
    public GoodsList_Bean(int goodslist_img,String goodslist_img_introduce,double goodslist_sale,int goodslist_person){
        this.goodslist_img=goodslist_img;
        this.goodslist_img_introduce=goodslist_img_introduce;
        this.goodslist_sale=goodslist_sale;
        this.goodslist_person=goodslist_person;

    }
    public int getGoodslist_img() {
        return goodslist_img;
    }

    public void setGoodslist_img(int goodslist_img) {
        this.goodslist_img = goodslist_img;
    }

    public String getGoodslist_img_introduce() {
        return goodslist_img_introduce;
    }

    public void setGoodslist_img_introduce(String goodslist_img_introduce) {
        this.goodslist_img_introduce = goodslist_img_introduce;
    }

    public double getGoodslist_sale() {
        return goodslist_sale;
    }

    public void setGoodslist_sale(double goodslist_sale) {
        this.goodslist_sale = goodslist_sale;
    }

    public int getGoodslist_person() {
        return goodslist_person;
    }

    public void setGoodslist_person(int goodslist_person) {
        this.goodslist_person = goodslist_person;
    }
}
