package cn.codesheep.springbt_security_jwt.controller;

import cn.codesheep.springbt_security_jwt.model.entity.User;
import cn.codesheep.springbt_security_jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.AuthenticationException;

@RestController
public class JwtAuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/authentication/login", method = RequestMethod.POST)
    public ResponseEntity<?> createToken( String username,String password ) throws AuthenticationException {

        final String token = authService.login( username, password );

        // Return the token
        return ResponseEntity.ok( token );
    }

    @RequestMapping(value = "/authentication/register", method = RequestMethod.POST)
    public User register( @RequestBody User addedUser ) throws AuthenticationException {
        return authService.register(addedUser);
    }

}
