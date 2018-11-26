package cn.codesheep.springbt_jwt_mybatis.controller;

import cn.codesheep.springbt_jwt_mybatis.utils.ResponseData;
import cn.codesheep.springbt_jwt_mybatis.utils.ResponseUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/testNoAuth")
    public ResponseData noAuth( ) {
        return ResponseUtil.getSucceed("No auth !",null);
    }

    @GetMapping("/testRead")
    @PreAuthorize("hasAuthority('ROLE_AUTH_READ')")  // 等同于 @PreAuthorize("hasRole('AUTH_READ')")
    public ResponseData testRead() {
        return ResponseUtil.getSucceed("READ auth success ！",null);
    }

    @GetMapping("/testDownload")
    @PreAuthorize("hasRole('AUTH_DOWNLOAD')")
    public ResponseData testDownload() {
        return ResponseUtil.getSucceed("DOWNLOAD auth success ！",null);
    }

    @GetMapping("/testUpload")
    @PreAuthorize("hasRole('AUTH_UPLOAD')")
    public ResponseData testUpload() {
        return ResponseUtil.getSucceed("UPLOAD auth success !",null);
    }

    @GetMapping("/testAdmin")
    @PreAuthorize("hasRole('AUTH_ADMIN')")
    public ResponseData testAdmin( ) {
        return ResponseUtil.getSucceed("ADMIN auth success ！",null);
    }



}
