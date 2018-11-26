package cn.codesheep.springbt_jwt_mybatis.service;

import cn.codesheep.springbt_jwt_mybatis.entity.Role;

public interface IRoleService {
    Role getRoleByRoleCode(String roleCode );
}
