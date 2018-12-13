package cn.codesheep.springbt_mybatis_generator.service;

import cn.codesheep.springbt_mybatis_generator.entity.UserInfo;
import cn.codesheep.springbt_mybatis_generator.entity.UserInfoExample;
import cn.codesheep.springbt_mybatis_generator.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    // 单条件模糊搜索
    public List<UserInfo> searchUserByUserName( String userName ) {

        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserNameLike( '%'+ userName +'%' ); // 设置模糊搜索的条件

        String orderByClause = "user_name DESC";
        userInfoExample.setOrderByClause( orderByClause );  // 设置通过某个字段排序的条件

        return userInfoMapper.selectByExample( userInfoExample );
    }

    // 单条件模糊搜索（带分页）
    public List<UserInfo> searchUserByUserName( String userName, Integer pageNum, Integer pageSize ) {

        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserNameLike( '%'+ userName +'%' ); // 设置模糊搜索的条件

        userInfoExample.setStartOffset( (pageNum-1)*pageSize );  // 设置分页
        userInfoExample.setPageSize(pageSize);

        String orderByClause = "user_name DESC";
        userInfoExample.setOrderByClause( orderByClause );  // 设置通过某个字段排序的条件

        return userInfoMapper.selectByExample( userInfoExample );
    }

    // 多条件精确搜索
    public List<UserInfo> multiConditionsSearch( UserInfo userInfo ) {

        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();

        if( !"".equals(userInfo.getPhone()) )
            criteria.andPhoneEqualTo( userInfo.getPhone() );
        if( !"".equals(userInfo.getUserName()) )
            criteria.andUserNameEqualTo( userInfo.getUserName() );

        return userInfoMapper.selectByExample( userInfoExample );
    }






}
