package cn.codesheep.springbt_brace.controller;

import cn.codesheep.springbt_brace.entity.User;
import cn.codesheep.springbt_brace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    CacheManager cacheManager;

    @RequestMapping( value = "/getusersbyname", method = RequestMethod.POST)
    public List<User> getUsersByName( @RequestBody User user ) {
        System.out.println( "-------------------------------------------" );
        System.out.println("call /getusersbyname");
        System.out.println(cacheManager.toString());
        List<User> users = userService.getUsersByName( user.getUserName() );
        return users;
    }

}
