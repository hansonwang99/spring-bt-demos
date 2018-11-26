package cn.codesheep.springbt_jwt_mybatis.entity;

import java.io.Serializable;

public class Authority implements Serializable{

	private static final long serialVersionUID = 8805168618208509679L;
	
	private Long authId  ;
	private String authCode ;
	private String authName ;
	private String authDes ;
	public Long getAuthId() {
		return authId;
	}
	public void setAuthId(Long authId) {
		this.authId = authId;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public String getAuthDes() {
		return authDes;
	}
	public void setAuthDes(String authDes) {
		this.authDes = authDes;
	}
	
	
	

}
