package bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class HotWordsBean {


    private int code;
    private String message;

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
        private String ke_id;
        private String ke_name;

        public String getKe_id() {
            return ke_id;
        }

        public void setKe_id(String ke_id) {
            this.ke_id = ke_id;
        }

        public String getKe_name() {
            return ke_name;
        }

        public void setKe_name(String ke_name) {
            this.ke_name = ke_name;
        }
    }
}
