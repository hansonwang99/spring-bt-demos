package cn.codesheep.springbt_jwt_mybatis.controller;

import cn.codesheep.springbt_jwt_mybatis.service.IAuthService;
import cn.codesheep.springbt_jwt_mybatis.service.IUserInfoService;
import cn.codesheep.springbt_jwt_mybatis.utils.ResponseData;
import cn.codesheep.springbt_jwt_mybatis.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader ;

    @Autowired
    private IAuthService authService;

    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping("/login")
    public ResponseData createAuthenticationToken(@RequestParam(name ="userName",required = true ) String userName ,
                                                  @RequestParam(name ="password",required = true ) String pass) throws AuthenticationException {
    	
    	String token = authService.login( userName, pass );

    	 if( "".equals(token) ) {
             return ResponseUtil.getFailed("获取TOKEN失败");
         }

         return ResponseUtil.getSucceed( token );
    }

    @GetMapping("/refresh")
    public ResponseData refreshAndGetAuthenticationToken( HttpServletRequest request ) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        
        if("".equals(refreshedToken)) {
            return ResponseUtil.getFailed("刷新TOKEN失败");
        } 
            
        return ResponseUtil.getSucceed(refreshedToken);
    }

    @GetMapping("/register")
    public ResponseData register( @RequestParam(name ="userName",required = true ) String userName ,
                                 @RequestParam(name ="password",required = true ) String password ) throws AuthenticationException {

        return ( authService.register(userName,password) > 0 ) ? ResponseUtil.getSucceed() : ResponseUtil.getFailed("用户注册失败") ;
    }

}
