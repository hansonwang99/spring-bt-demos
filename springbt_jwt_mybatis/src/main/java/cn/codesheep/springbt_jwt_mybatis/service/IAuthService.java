package cn.codesheep.springbt_jwt_mybatis.service;

import cn.codesheep.springbt_jwt_mybatis.entity.User;

public interface IAuthService {
	
	User findByUsername(String name) ;
	
	int register(String username, String password);
    String login(String username, String password);
    String refresh(String oldToken);
}
