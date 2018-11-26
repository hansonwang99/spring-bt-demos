package cn.codesheep.springbt_jwt_mybatis.entity;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable{

	private static final long serialVersionUID = -9096279489213347051L;
	
	private Long roleId  ;
	private String roleCode ;
	private String roleName ;
	private String roleDes ;
	private List<Authority> auths ;
	
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDes() {
		return roleDes;
	}
	public void setRoleDes(String roleDes) {
		this.roleDes = roleDes;
	}
	public List<Authority> getAuths() {
		return auths;
	}
	public void setAuths(List<Authority> auths) {
		this.auths = auths;
	}

	

}
