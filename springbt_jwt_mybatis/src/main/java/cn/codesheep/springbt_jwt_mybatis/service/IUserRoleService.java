package cn.codesheep.springbt_jwt_mybatis.service;

import cn.codesheep.springbt_jwt_mybatis.entity.UserRole;

public interface IUserRoleService {
    int addRole( UserRole userRole );
    int deleteRole( UserRole userRole );
    UserRole findUserRole( Long userId, Long roleId );
}
