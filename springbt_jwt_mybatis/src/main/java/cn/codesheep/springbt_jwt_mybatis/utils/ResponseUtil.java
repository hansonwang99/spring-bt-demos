package cn.codesheep.springbt_jwt_mybatis.utils;

public class ResponseUtil {
	
	public static final Integer STATUS_SUCCESS = 0;
	public static final Integer STATUS_FAIL = 1;
	public static final String STATUS_SUCCESS_MSG = "操作成功";
	public static final String STATUS_FIAL_MSG = "操作失败";
	

	public static ResponseData getFailed(String msg) {
	    return new ResponseData(STATUS_FAIL, msg, null);
	}

	public static ResponseData getFailed() {
	    return new ResponseData(STATUS_FAIL, STATUS_FIAL_MSG, null);
	}


	public static ResponseData getSucceed() {
	    return new ResponseData(STATUS_SUCCESS, STATUS_SUCCESS_MSG, null);
	}

	public static ResponseData getSucceed(Object data) {
	    return new ResponseData(STATUS_SUCCESS, STATUS_SUCCESS_MSG, data);
	}

	public static ResponseData getSucceed(String msg, Object data) {
	    return new ResponseData(STATUS_SUCCESS, msg, data);
	}

}
