package com.tony.demo.misc.base;

import java.io.Serializable;

import com.tony.demo.misc.exception.ServiceException;

import lombok.Data;

@Data
public class Response<T> implements Serializable{
	
	private static final long serialVersionUID = -6772539589950910360L;
	public static final String MSG_OK		= "ok";	
	public static final String API_OK       = "200";
	public static final Integer RT_LOGIN	= Integer.valueOf(101);
	public static final Integer RT_LOGOUT	= Integer.valueOf(102);

	/**业务处理代码*/
	private String resultCode="101";
	
	/**状态码*/
	private String statusCode = API_OK;
	
	/**状态描述*/
	private String msg = MSG_OK;
	
	/**业务模型結果*/
	private T result;
	
	public Response(){

	}

	public Response(T result){
		this.result = result;
	}

	public Response(String msg, String statusCode){
		this.msg = msg;
		this.statusCode = statusCode;
	}
	
	public Response(String msg, String statusCode, String resultCode){
		this.msg = msg;
		this.statusCode = statusCode;
		this.resultCode = resultCode;
	}
	
	public Response(ServiceException e)
	{
		this(e.getMessage(), e.getStatusCode(), e.getResultCode());
	}


}
