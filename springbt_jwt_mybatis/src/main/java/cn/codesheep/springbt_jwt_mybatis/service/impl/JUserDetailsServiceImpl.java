package cn.codesheep.springbt_jwt_mybatis.service.impl;

import cn.codesheep.springbt_jwt_mybatis.entity.JUserFactory;
import cn.codesheep.springbt_jwt_mybatis.entity.User;
import cn.codesheep.springbt_jwt_mybatis.mapper.AuthMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JUserDetailsServiceImpl implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(JUserDetailsServiceImpl.class);

    @Autowired
    private AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = authMapper.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }

        return JUserFactory.create(user);
    }
}
 
