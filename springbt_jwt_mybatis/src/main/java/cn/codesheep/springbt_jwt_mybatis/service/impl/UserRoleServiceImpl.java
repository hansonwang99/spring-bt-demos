package cn.codesheep.springbt_jwt_mybatis.service.impl;

import cn.codesheep.springbt_jwt_mybatis.entity.UserRole;
import cn.codesheep.springbt_jwt_mybatis.mapper.UserRoleMapper;
import cn.codesheep.springbt_jwt_mybatis.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserRoleServiceImpl implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public int addRole(UserRole userRole) {
        SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        userRole.setCreatedTime( dateForm.format(new Date()) );
        return userRoleMapper.addRole( userRole );
    }

    @Override
    public int deleteRole(UserRole userRole) {
        return userRoleMapper.deleteRole( userRole );
    }

    @Override
    public UserRole findUserRole( Long userId, Long roleId ) {
        UserRole userRole = new UserRole();
        userRole.setUserId( userId );
        userRole.setRoleId( roleId );
        return userRoleMapper.findUserRole( userRole );
    }
}
