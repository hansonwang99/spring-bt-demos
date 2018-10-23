package cn.codesheep.springbt_security_jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    @RequestMapping( value="/normal/test", method = RequestMethod.GET )
    public String test1() {
        return "ROLE_NORMAL";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping( value = "/admin/test", method = RequestMethod.GET )
    public String test2() {
        return "ROLE_ADMIN";
    }
}
