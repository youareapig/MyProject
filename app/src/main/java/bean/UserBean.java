package bean;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class UserBean {


    /**
     * code : -3003
     * message : 该用户已经被注册
     * data :
     */

    private int code;
    private String message;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
