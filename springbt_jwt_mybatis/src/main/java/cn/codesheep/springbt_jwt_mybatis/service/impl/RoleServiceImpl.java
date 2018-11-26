package cn.codesheep.springbt_jwt_mybatis.service.impl;

import cn.codesheep.springbt_jwt_mybatis.entity.Role;
import cn.codesheep.springbt_jwt_mybatis.mapper.RoleMapper;
import cn.codesheep.springbt_jwt_mybatis.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role getRoleByRoleCode(String roleCode) {
        return roleMapper.getRoleByRoleCode( roleCode );
    }
}
