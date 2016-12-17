package bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/17 0017.
 */
public class SalesBean {


    private int code;
    private String message;

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * active_name : 元旦活动
         * active_logo : Uploads/Active/2016-12-17//5854d6f056541.png
         */

        private TitleBean title;
        /**
         * goods_id : 1
         * active_gnumbers : 40
         * active_price : 38.25
         * shop_price : 50.00
         * goods_name : 酷斯特/KUST 雪佛兰科鲁兹 刹车灯 奥烁40灯高亮LED刹车灯灯泡 09-14款老科
         * thumb : Uploads/Goodspic/2016-11-21//5832c3e0113e7.png
         */

        private List<InfoBean> Info;

        public TitleBean getTitle() {
            return title;
        }

        public void setTitle(TitleBean title) {
            this.title = title;
        }

        public List<InfoBean> getInfo() {
            return Info;
        }

        public void setInfo(List<InfoBean> Info) {
            this.Info = Info;
        }

        public static class TitleBean {
            private String active_name;
            private String active_logo;

            public String getActive_name() {
                return active_name;
            }

            public void setActive_name(String active_name) {
                this.active_name = active_name;
            }

            public String getActive_logo() {
                return active_logo;
            }

            public void setActive_logo(String active_logo) {
                this.active_logo = active_logo;
            }
        }

        public static class InfoBean {
            private String goods_id;
            private String active_gnumbers;
            private String active_price;
            private String shop_price;
            private String goods_name;
            private String thumb;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getActive_gnumbers() {
                return active_gnumbers;
            }

            public void setActive_gnumbers(String active_gnumbers) {
                this.active_gnumbers = active_gnumbers;
            }

            public String getActive_price() {
                return active_price;
            }

            public void setActive_price(String active_price) {
                this.active_price = active_price;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }
    }
}
