package bean;

/**
 * Created by Administrator on 2016/12/5 0005.
 */
public class UpLoadingPhoto {

    /**
     * code : 3005
     * message : 图片上传成功
     * data : ./Uploads/App/Balance(magazine)-01-2.3.001-bigpicture_01_6.jpg
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
