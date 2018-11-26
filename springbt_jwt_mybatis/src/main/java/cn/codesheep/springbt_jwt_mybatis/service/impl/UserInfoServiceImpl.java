package cn.codesheep.springbt_jwt_mybatis.service.impl;

import cn.codesheep.springbt_jwt_mybatis.entity.UserInfo;
import cn.codesheep.springbt_jwt_mybatis.mapper.UserInfoMapper;
import cn.codesheep.springbt_jwt_mybatis.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@Service
public class UserInfoServiceImpl implements IUserInfoService {

	@Autowired	
	private UserInfoMapper userInfoMapper ;

	@Override
	public int addUser( UserInfo userInfo ) {
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		userInfo.setCreatedTime( dateForm.format(new Date()) );
		return this.userInfoMapper.addUser(userInfo);
	}

	@Override
	public UserInfo getUserByName(String userName) {
		return this.userInfoMapper.getUserByName( userName );
	}

	@Override
	public UserInfo getUserById(Long userId) {
		return this.userInfoMapper.getUserById(userId);
	}

	@Override
	public UserInfo getUser(UserInfo userInfo) {
		return this.userInfoMapper.getUser(userInfo);
	}

	@Override
	public List<UserInfo> getAllUser() {
		return this.userInfoMapper.getAllUser();
	}

}
