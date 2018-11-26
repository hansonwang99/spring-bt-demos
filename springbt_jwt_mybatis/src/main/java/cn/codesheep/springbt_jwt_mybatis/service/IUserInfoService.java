package cn.codesheep.springbt_jwt_mybatis.service;

import cn.codesheep.springbt_jwt_mybatis.entity.UserInfo;

import java.util.List;

public interface IUserInfoService {

	public int addUser(UserInfo userInfo);

	public UserInfo getUserByName( String userName );

	public UserInfo getUserById(Long userId) ;
	
	public UserInfo getUser(UserInfo userInfo) ;
	
	public List<UserInfo> getAllUser() ;
}
