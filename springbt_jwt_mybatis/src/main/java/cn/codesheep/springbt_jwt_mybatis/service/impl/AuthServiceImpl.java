package cn.codesheep.springbt_jwt_mybatis.service.impl;

import cn.codesheep.springbt_jwt_mybatis.entity.JUser;
import cn.codesheep.springbt_jwt_mybatis.entity.User;
import cn.codesheep.springbt_jwt_mybatis.entity.UserInfo;
import cn.codesheep.springbt_jwt_mybatis.mapper.AuthMapper;
import cn.codesheep.springbt_jwt_mybatis.mapper.RoleMapper;
import cn.codesheep.springbt_jwt_mybatis.mapper.UserRoleMapper;
import cn.codesheep.springbt_jwt_mybatis.service.IAuthService;
import cn.codesheep.springbt_jwt_mybatis.service.IUserInfoService;
import cn.codesheep.springbt_jwt_mybatis.utils.JTokenUtil;
import cn.codesheep.springbt_jwt_mybatis.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements IAuthService {

	@Autowired	
	private AuthMapper authMapper ;
	
	@Autowired
	AuthenticationManager authenticationManager ;
	
	@Autowired
	JTokenUtil jwtTokenUtil ;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private IUserInfoService userInfoService;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;
	 
	@Value("${jwt.tokenHead}")
	private String tokenHead;
	 
	@Override
	public User findByUsername(String name) {
		return authMapper.findByUsername(name);
	}

   	@Override
	public String login(String username, String password) {

	   UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
	   final Authentication authentication = authenticationManager.authenticate(upToken);
	   SecurityContextHolder.getContext().setAuthentication(authentication);

	   final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	   final String token = jwtTokenUtil.generateToken(userDetails);
	   return token;
	}

	@Override
	public String refresh(String oldToken) {
		final String token = oldToken.substring(tokenHead.length());
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JUser user = (JUser) userDetailsService.loadUserByUsername(username);
		if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
			return jwtTokenUtil.refreshToken(token);
		}
		return null;
	}


	@Override
	public int register( String userName, String password ) {

		BCryptPasswordEncoder code = new BCryptPasswordEncoder() ;
		String pwd = code.encode( password ) ;
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userName);
		userInfo.setPassword(pwd);
		return userInfoService.addUser(userInfo);
	}

}
