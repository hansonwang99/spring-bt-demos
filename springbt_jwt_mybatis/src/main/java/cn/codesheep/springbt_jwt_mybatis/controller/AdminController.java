package cn.codesheep.springbt_jwt_mybatis.controller;

import cn.codesheep.springbt_jwt_mybatis.entity.UserInfo;
import cn.codesheep.springbt_jwt_mybatis.entity.UserRole;
import cn.codesheep.springbt_jwt_mybatis.service.IRoleService;
import cn.codesheep.springbt_jwt_mybatis.service.IUserInfoService;
import cn.codesheep.springbt_jwt_mybatis.service.IUserRoleService;
import cn.codesheep.springbt_jwt_mybatis.utils.ResponseData;
import cn.codesheep.springbt_jwt_mybatis.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserRoleService userRoleService;

    @GetMapping("/addUser")
    public ResponseData addUser(@RequestParam(name ="userName",required = true ) String userName ,
                                @RequestParam(name ="password",required = true ) String password ) {

        BCryptPasswordEncoder code = new BCryptPasswordEncoder() ;
        String pwd = code.encode(password) ;

        UserInfo userInfo = new UserInfo( );
        userInfo.setUserName(userName);
        userInfo.setPassword(pwd);
        int i = userInfoService.addUser(userInfo) ;

        if(i>0) {
            return ResponseUtil.getSucceed( userInfo ) ;
        }

        return ResponseUtil.getFailed("添加用户失败") ;
    }

    @GetMapping("/addRole")
    public ResponseData addRole( @RequestParam(name ="userName",required = true ) String userName ,
                                 @RequestParam(name ="roleName",required = true ) String roleName ) {

        UserRole userRole = new UserRole();
        userRole.setUserId( userInfoService.getUserByName(userName).getUserId() );
        userRole.setRoleId( roleService.getRoleByRoleCode( roleName ).getRoleId() );
        return ( userRoleService.addRole( userRole ) > 0 ) ? ResponseUtil.getSucceed() : ResponseUtil.getFailed("添加用户角色失败");
    }

    @GetMapping("/removeRole")
    public ResponseData removeRole( @RequestParam(name ="userName",required = true ) String userName ,
                                    @RequestParam(name ="roleName",required = true ) String roleName ) {

        UserRole userRole = new UserRole();
        userRole.setUserId( userInfoService.getUserByName(userName).getUserId() );
        userRole.setRoleId( roleService.getRoleByRoleCode( roleName ).getRoleId() );
        return ( userRoleService.deleteRole( userRole ) > 0 ) ? ResponseUtil.getSucceed() : ResponseUtil.getFailed("删除用户角色失败");
    }

}
