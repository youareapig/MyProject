package bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31 0031.
 */
public class GoodsList_Bean {

    private int code;
    private String message;

    private List<DataBean> data=null;
    /**
     * brand_name : 巾帼宝丽曼
     * brand_id : 1
     */

    private List<BrandBean> brand;

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

    public List<BrandBean> getBrand() {
        return brand;
    }

    public void setBrand(List<BrandBean> brand) {
        this.brand = brand;
    }

    public static class DataBean {
        private String goods_id;
        private String goods_name;
        private String cat_id;
        private String type_id;
        private String shop_price;
        private String sales;
        private String thumb;
        private String sm_thumb;
        private String hints;
        private String amount;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getSm_thumb() {
            return sm_thumb;
        }

        public void setSm_thumb(String sm_thumb) {
            this.sm_thumb = sm_thumb;
        }

        public String getHints() {
            return hints;
        }

        public void setHints(String hints) {
            this.hints = hints;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public static class BrandBean {
        private String brand_name;
        private String brand_id;

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }
    }
}
