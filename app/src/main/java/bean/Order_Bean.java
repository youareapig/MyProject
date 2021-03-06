package bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/10 0010.
 */
public class Order_Bean {

    /**
     * code : 5000
     * message : 数据查询成功
     * data : [{"is_pay":"0","addtime":"1431378612","orderlist_id":"12","order_goods_id":"123456789","goods_name":"酷斯特/KUST 雪佛兰科鲁兹 刹车灯 奥烁40灯高亮LED刹车灯灯泡 09-14款老科","goods_count":"50","goods_price":"40.00","server_price":"0.00","goods_total":"2000.00","state":"0","goods_image":"Uploads/Goodspic/2016-11-21//5832c3e0113e7.png"}]
     */

    private int code;
    private String message;
    /**
     * is_pay : 0
     * addtime : 1431378612
     * orderlist_id : 12
     * order_goods_id : 123456789
     * goods_name : 酷斯特/KUST 雪佛兰科鲁兹 刹车灯 奥烁40灯高亮LED刹车灯灯泡 09-14款老科
     * goods_count : 50
     * goods_price : 40.00
     * server_price : 0.00
     * goods_total : 2000.00
     * state : 0
     * goods_image : Uploads/Goodspic/2016-11-21//5832c3e0113e7.png
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String is_pay;
        private String addtime;
        private String orderlist_id;
        private String order_goods_id;
        private String goods_name;
        private String goods_count;
        private String goods_price;
        private String server_price;
        private String goods_total;
        private String state;
        private String goods_image;

        public String getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(String is_pay) {
            this.is_pay = is_pay;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getOrderlist_id() {
            return orderlist_id;
        }

        public void setOrderlist_id(String orderlist_id) {
            this.orderlist_id = orderlist_id;
        }

        public String getOrder_goods_id() {
            return order_goods_id;
        }

        public void setOrder_goods_id(String order_goods_id) {
            this.order_goods_id = order_goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_count() {
            return goods_count;
        }

        public void setGoods_count(String goods_count) {
            this.goods_count = goods_count;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getServer_price() {
            return server_price;
        }

        public void setServer_price(String server_price) {
            this.server_price = server_price;
        }

        public String getGoods_total() {
            return goods_total;
        }

        public void setGoods_total(String goods_total) {
            this.goods_total = goods_total;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }
    }
}
