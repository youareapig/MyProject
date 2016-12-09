package bean;

/**
 * Created by Administrator on 2016/12/8.
 */
public class OrderDataBean {
    private String code;
    private String message;
    private DatasBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DatasBean getData() {
        return data;
    }

    public void setData(DatasBean data) {
        this.data = data;
    }
}
