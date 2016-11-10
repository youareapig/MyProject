package bean;

/**
 * Created by Administrator on 2016/11/10 0010.
 */
public class Order_Bean {
    private String date;
    private String state;
    private int img;
    private String name;
    private double goodsprice;
    private int number;
    private double goodstotalsale;
    private double serviceprice;
    private double totalprice;

    public Order_Bean(String date, double totalprice, double serviceprice, String state, int img, String name, double goodsprice, int number, double goodstotalsale) {
        this.date = date;
        this.totalprice = totalprice;
        this.serviceprice = serviceprice;
        this.state = state;
        this.img = img;
        this.name = name;
        this.goodsprice = goodsprice;
        this.number = number;
        this.goodstotalsale = goodstotalsale;
    }




    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(double goodsprice) {
        this.goodsprice = goodsprice;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getGoodstotalsale() {
        return goodstotalsale;
    }

    public void setGoodstotalsale(double goodstotalsale) {
        this.goodstotalsale = goodstotalsale;
    }

    public double getServiceprice() {
        return serviceprice;
    }

    public void setServiceprice(double serviceprice) {
        this.serviceprice = serviceprice;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }
}
