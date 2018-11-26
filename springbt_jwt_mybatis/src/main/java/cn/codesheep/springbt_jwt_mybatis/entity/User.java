package cn.codesheep.springbt_jwt_mybatis.entity;

import java.io.Serializable;
import java.util.List;

// User用于和角色、权限等结合使用
public class User implements Serializable{

	private static final long serialVersionUID = -6522815687143910770L;

	private Long userId ;
	
	private String userName ;
	
	private String password ;

	private String createdTime;
	
	private List<Role> roles;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
}
