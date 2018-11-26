package cn.codesheep.springbt_jwt_mybatis.utils;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseData implements Serializable {

	private static final long serialVersionUID = -3973546572325250341L;

	private Integer status = ResponseUtil.STATUS_SUCCESS;
	private String msg;
	private Object data;

	public ResponseData() {
	}

	public ResponseData(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	



}

