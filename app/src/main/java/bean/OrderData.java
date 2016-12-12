package bean;

/**
 * Created by Administrator on 2016/12/12.
 */
public class OrderData {
    private String goods_id;
    private String addr;
    private String goods_number;
    private String send_price;
    private String total_price;
    private String message;
    private String goods_total;
    private String goods_image;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(String goods_number) {
        this.goods_number = goods_number;
    }

    public String getSend_price() {
        return send_price;
    }

    public void setSend_price(String send_price) {
        this.send_price = send_price;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGoods_total() {
        return goods_total;
    }

    public void setGoods_total(String goods_total) {
        this.goods_total = goods_total;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }
}
