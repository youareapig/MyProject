package bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class IndexBean {



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
        private int dif_time;
        /**
         * image_src : Uploads/Ad/2016-12-13/584fa7b64edb8.png
         */

        private List<AdBean> Ad;
        /**
         * title : 好的啦
         */

        private List<NewsInfoBean> newsInfo;


        private List<ActiveInfoBean> activeInfo;

        private List<SecondGoodsBean> second_goods;

        public int getDif_time() {
            return dif_time;
        }

        public void setDif_time(int dif_time) {
            this.dif_time = dif_time;
        }

        public List<AdBean> getAd() {
            return Ad;
        }

        public void setAd(List<AdBean> Ad) {
            this.Ad = Ad;
        }

        public List<NewsInfoBean> getNewsInfo() {
            return newsInfo;
        }

        public void setNewsInfo(List<NewsInfoBean> newsInfo) {
            this.newsInfo = newsInfo;
        }

        public List<ActiveInfoBean> getActiveInfo() {
            return activeInfo;
        }

        public void setActiveInfo(List<ActiveInfoBean> activeInfo) {
            this.activeInfo = activeInfo;
        }

        public List<SecondGoodsBean> getSecond_goods() {
            return second_goods;
        }

        public void setSecond_goods(List<SecondGoodsBean> second_goods) {
            this.second_goods = second_goods;
        }

        public static class AdBean {
            private String image_src;

            public String getImage_src() {
                return image_src;
            }

            public void setImage_src(String image_src) {
                this.image_src = image_src;
            }
        }

        public static class NewsInfoBean {
            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class ActiveInfoBean {
            private String active_id;
            private String active_logo;

            public String getActive_id() {
                return active_id;
            }

            public void setActive_id(String active_id) {
                this.active_id = active_id;
            }

            public String getActive_logo() {
                return active_logo;
            }

            public void setActive_logo(String active_logo) {
                this.active_logo = active_logo;
            }
        }

        public static class SecondGoodsBean {
            private String goods_id;
            private String shop_price;
            private String active_price;
            private String thumb;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getActive_price() {
                return active_price;
            }

            public void setActive_price(String active_price) {
                this.active_price = active_price;
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
