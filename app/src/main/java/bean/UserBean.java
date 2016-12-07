package bean;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class UserBean {

    /**
     * code : 3000
     * message : 登陆成功
     * data : {"userid":"4","mobile":"18140463430","groupid":"3"}
     */

    private int code;
    private String message;
    /**
     * userid : 4
     * mobile : 18140463430
     * groupid : 3
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
        private String mobile;
        private String groupid;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getGroupid() {
            return groupid;
        }

        public void setGroupid(String groupid) {
            this.groupid = groupid;
        }
    }
}
