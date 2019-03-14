package com.tony.demo.misc.constant;

public class RequestValidate {
	
	//请求参数类型
	public static RequestValidate SUCCESS = new RequestValidate(200,"ok");
	public static RequestValidate TOKEN_NULL = new RequestValidate(501,"token is null");
	public static RequestValidate ORDERID_NULL = new RequestValidate(501,"orderId is null");
	
	private Integer code ; 
	private String message;

	RequestValidate(Integer code, String message) {
		this.code = code;
		this.message = message;
	}


	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
