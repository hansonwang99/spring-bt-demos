package cn.codesheep.springbt_alisms.model.result;

public class ResponseMessage {

    private boolean success = true;
    private String error = "";
    private final String version = "V1.0";
    private Object data;

    // 创建正确返回实例
    public ResponseMessage() {
        this.success = true;
        this.error = "";
    }

    // 创建带数据的正确返回实例
    public ResponseMessage(Object data ) {
        this.success = true;
        this.error = "";
        this.data = data;
    }

    // 创建错误返回实例
    public ResponseMessage(String errorString) {
        this.success = false;
        this.error = errorString;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getVersion() {
        return version;
    }
}