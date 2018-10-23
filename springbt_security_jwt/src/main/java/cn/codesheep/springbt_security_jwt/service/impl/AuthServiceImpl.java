package cn.codesheep.springbt_security_jwt.service.impl;

import cn.codesheep.springbt_security_jwt.comm.Const;
import cn.codesheep.springbt_security_jwt.model.entity.User;
import cn.codesheep.springbt_security_jwt.repository.UserRepository;
import cn.codesheep.springbt_security_jwt.service.AuthService;
import cn.codesheep.springbt_security_jwt.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register( User userToAdd ) {

        final String username = userToAdd.getUsername();
        if( userRepository.findByUsername(username)!=null ) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword( encoder.encode(rawPassword) );
        return userRepository.save(userToAdd);
    }

    @Override
    public String login( String username, String password ) {

        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken( username, password );

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername( username );
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public String refresh(String oldToken) {

        final String token = oldToken.substring( Const.TOKEN_PREFIX.length() );
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = (User) userDetailsService.loadUserByUsername(username);
        if ( jwtTokenUtil.canTokenBeRefreshed( token ) ) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
