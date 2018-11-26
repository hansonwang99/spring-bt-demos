package cn.codesheep.springbt_jwt_mybatis.mapper;

import cn.codesheep.springbt_jwt_mybatis.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMapper {

    int addRole( UserRole userRole );
    int deleteRole( UserRole userRole );
    UserRole findUserRole( UserRole userRole );
}
