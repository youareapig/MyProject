package bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public class NoticeHalfBean {


    /**
     * code : 2000
     * message : 获取信息成功
     * data : [{"news_id":"3","thumb":"Uploads/News/2016-12-09/584a7dcd06fb8.png","introduce":"","title":"好的福利11111","linkurl":""},{"news_id":"6","thumb":"Uploads/News/2016-12-09/584aa0d3af320.jpg","introduce":"256125211","title":"411852151","linkurl":"Static/news_6.html"},{"news_id":"8","thumb":"Uploads/News/2016-12-09/584a7fbb1cb60.jpg","introduce":"266362","title":"66666667","linkurl":"Static/news_8.html"}]
     */

    private int code;
    private String message;
    /**
     * news_id : 3
     * thumb : Uploads/News/2016-12-09/584a7dcd06fb8.png
     * introduce :
     * title : 好的福利11111
     * linkurl :
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
        private String news_id;
        private String thumb;
        private String introduce;
        private String title;
        private String linkurl;

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLinkurl() {
            return linkurl;
        }

        public void setLinkurl(String linkurl) {
            this.linkurl = linkurl;
        }
    }
}
