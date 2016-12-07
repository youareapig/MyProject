package bean;

/**
 * Created by Administrator on 2016/12/6 0006.
 */
public class UserInformationBean {

    /**
     * code : 3000
     * message : 获取信息成功
     * data : {"userid":"4","header":null,"username":null,"gender":"0","birthday":"2016-12-06"}
     */

    private int code;
    private String message;
    /**
     * userid : 4
     * header : null
     * username : null
     * gender : 0
     * birthday : 2016-12-06
     */

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
        private String userid;
        private Object header;
        private Object username;
        private String gender;
        private String birthday;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public Object getHeader() {
            return header;
        }

        public void setHeader(Object header) {
            this.header = header;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
            this.username = username;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }
    }
}
