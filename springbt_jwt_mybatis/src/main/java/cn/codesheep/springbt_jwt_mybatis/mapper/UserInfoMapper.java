package cn.codesheep.springbt_jwt_mybatis.mapper;

import cn.codesheep.springbt_jwt_mybatis.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper {
	
	int addUser(UserInfo userInfo) ;

	UserInfo getUserByName(String userName);

	UserInfo getUserById(Long userId);
	
	UserInfo getUser(UserInfo userInfo);
	
	int deleteUser(Long userId) ;
	
	int deleteUser(UserInfo userInfo) ;
	
	List<UserInfo> getAllUser() ;

	UserInfo getUserByMobile(String mobile);

	List<UserInfo> searchUser(@Param("keyword") String keyword);

	int updateUserInfo(UserInfo userInfo);
}
