package bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class IndexBean {

    /**
     * code : 1000
     * message : 获取信息成功
     * data : {"Ad":[{"image_src":"Uploads/Ad/2016-12-13/584fa7b64edb8.png"},{"image_src":"Uploads/Ad/2016-12-13/584fa7e404a38.png"},{"image_src":"Uploads/Ad/2016-12-13/584fa812c8320.png"},{"image_src":"Uploads/Ad/2016-12-13/584fa83a87fc8.png"}],"newsInfo":[{"title":"好的啦"},{"title":"武松打虎"},{"title":"武大郎"}],"activeInfo":[{"active_id":"1","active_logo":"Uploads/Active/2016-12-16//585385de0ebf0.jpg"},{"active_id":"2","active_logo":"Uploads/Active/2016-12-16//5853860e0ccb0.jpg"}]}
     */

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
         * image_src : Uploads/Ad/2016-12-13/584fa7b64edb8.png
         */

        private List<AdBean> Ad;
        /**
         * title : 好的啦
         */

        private List<NewsInfoBean> newsInfo;
        /**
         * active_id : 1
         * active_logo : Uploads/Active/2016-12-16//585385de0ebf0.jpg
         */

        private List<ActiveInfoBean> activeInfo;

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
    }
}
