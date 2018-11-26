package cn.codesheep.springbt_jwt_mybatis.mapper;

import cn.codesheep.springbt_jwt_mybatis.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthMapper {
	
	User findByUsername(String name) ;
}
