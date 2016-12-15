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

        private List<NewsInfoBean> newsInfo;


        private List<AdBean> Ad;

        public List<NewsInfoBean> getNewsInfo() {
            return newsInfo;
        }

        public void setNewsInfo(List<NewsInfoBean> newsInfo) {
            this.newsInfo = newsInfo;
        }

        public List<AdBean> getAd() {
            return Ad;
        }

        public void setAd(List<AdBean> Ad) {
            this.Ad = Ad;
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

        public static class AdBean {
            private String image_src;

            public String getImage_src() {
                return image_src;
            }

            public void setImage_src(String image_src) {
                this.image_src = image_src;
            }
        }
    }
}
