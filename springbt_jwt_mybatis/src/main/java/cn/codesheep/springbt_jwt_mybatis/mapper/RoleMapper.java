package cn.codesheep.springbt_jwt_mybatis.mapper;

import cn.codesheep.springbt_jwt_mybatis.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {

    Role getRoleByRoleCode(String roleCode );
}
