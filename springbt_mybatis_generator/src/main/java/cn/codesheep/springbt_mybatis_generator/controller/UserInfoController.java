package cn.codesheep.springbt_mybatis_generator.controller;

import cn.codesheep.springbt_mybatis_generator.entity.UserInfo;
import cn.codesheep.springbt_mybatis_generator.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/searchUser")
    public List<UserInfo> search( @RequestParam String userName ) {
        return userInfoService.searchUserByUserName( userName );
    }

    @PostMapping("/multiConditionsSearch")
    public List<UserInfo> multiConditionsSearch( @RequestBody UserInfo userInfo ) {
        return userInfoService.multiConditionsSearch( userInfo );
    }

    @GetMapping("/searchUserWithPagination")
    public List<UserInfo> searchUserWithPagination( @RequestParam String userName, @RequestParam String pageNum, @RequestParam String pageSize  ) {
        return userInfoService.searchUserByUserName( userName, Integer.valueOf(pageNum), Integer.valueOf(pageSize) );
    }


}
