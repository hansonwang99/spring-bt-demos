package cn.codesheep.springbt_jwt_mybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

// UserInfo 用于和 MySQL数据表 cs_user实体关联
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -2969413835212226593L;

	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long userId ;
	
	private String userName ;
	
	private String password ;

	private String createdTime;


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

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

}
