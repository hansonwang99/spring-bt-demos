package cn.codesheep.springbt_alisms.comm;

import org.springframework.stereotype.Component;

@Component
public class Const {

    // 以下为 阿里云短信服务相关参数
    // 产品名称:云通信短信API产品,开发者无需替换
    public static final String PRODUCT = "Dysmsapi";
    // 产品域名,开发者无需替换
    public static final String DOMAIN = "dysmsapi.aliyuncs.com";
    // 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    public static final String ACCESS_KEY_ID = "2zhnLdxKasxZbekw";
    public static final String ACCESS_KEY_SECRET = "Mh8aH90PdvhRb1BImWjbyuy0hmiElH";
}
