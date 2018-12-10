package cn.codesheep.springbt_alioss.model.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AliyunOssPropertiesDTO {

    @Value("${aliyun.oss.project-name}")
    private String projectName;

    @Value("${aliyun.oss.access-id}")
    private String accessId;

    @Value("${aliyun.oss.access-key}")
    private String accessKey;

    @Value("${aliyun.oss.bucket}")
    private String bucket;

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;


    private Integer maxSize = Integer.valueOf(1);
    private Integer expire = Integer.valueOf(30);
    private boolean secure = false;
    private String roleSessionName;

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public String getRoleSessionName() {
        return roleSessionName;
    }

    public void setRoleSessionName(String roleSessionName) {
        this.roleSessionName = roleSessionName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}
