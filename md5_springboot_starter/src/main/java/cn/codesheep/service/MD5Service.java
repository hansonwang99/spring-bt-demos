package cn.codesheep.service;

import cn.codesheep.util.MD5Util;

public class MD5Service {

    public String getMD5( String input ) {
        return MD5Util.getMD5( input.getBytes() );
    }
}
