package cn.codesheep.springbt_alisms.model.dto;

public class VerificationCode {

    private String mobile;  // 接收或发送验证码的手机号手机号

    private String code;  // 验证码

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
