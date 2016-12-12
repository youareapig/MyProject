package bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public class NoticeBean {

    /**
     * code : 2000
     * message : 获取信息成功
     * data : {"info":[{"cat_id":"1","cat_name":"大公告"},{"cat_id":"2","cat_name":"福利"},{"cat_id":"3","cat_name":"咨询"},{"cat_id":"4","cat_name":"知识"}],"childrenone":[{"thumb":"","introduce":"","title":"154256125412541","linkurl":"Static/news_9.html"}]}
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
         * cat_id : 1
         * cat_name : 大公告
         */

        private List<InfoBean> info;
        /**
         * thumb :
         * introduce :
         * title : 154256125412541
         * linkurl : Static/news_9.html
         */

        private List<ChildrenoneBean> childrenone;

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public List<ChildrenoneBean> getChildrenone() {
            return childrenone;
        }

        public void setChildrenone(List<ChildrenoneBean> childrenone) {
            this.childrenone = childrenone;
        }

        public static class InfoBean {
            private String cat_id;
            private String cat_name;

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getCat_name() {
                return cat_name;
            }

            public void setCat_name(String cat_name) {
                this.cat_name = cat_name;
            }
        }

        public static class ChildrenoneBean {
            private String thumb;
            private String introduce;
            private String title;
            private String linkurl;

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
}
