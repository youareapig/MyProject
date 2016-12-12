package bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class IndexBean {

    /**
     * code : 4000
     * message : 获取信息成功
     * data : {"newsInfo":[{"title":"西门庆大官人"}]}
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
         * title : 西门庆大官人
         */

        private List<NewsInfoBean> newsInfo;

        public List<NewsInfoBean> getNewsInfo() {
            return newsInfo;
        }

        public void setNewsInfo(List<NewsInfoBean> newsInfo) {
            this.newsInfo = newsInfo;
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
    }
}
