package com.tony.demo.misc.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.tony.demo.misc.exception.ServiceException;

public class NormalReturn implements Serializable {
	private static final long serialVersionUID = -5219724691798725987L;
	
	public static final String MSG_OK		= "ok";	
	public static final Integer RT_LOGIN	= Integer.valueOf(101);
	public static final Integer RT_LOGOUT	= Integer.valueOf(102);

	private String resultCode;
	private String statusCode	= ServiceException.API_OK;
	private String msg			= MSG_OK;
	
	@JSONField(name = "result")
	private Object result;
	@JSONField(serialize=false)
	private Integer returnType;
	
	private long costTime;
	
	@JSONField(name = "validationErrors")
	private Map<String, ArrayList<String>> validationErrors;
	
	public NormalReturn(){
		
	}
	
	public NormalReturn(Object result){
		this.result = result;
	}
	
	public NormalReturn(String msg, String statusCode){
		this.msg = msg;
		this.statusCode = statusCode;
	}
	
	public NormalReturn(ServiceException e){
		this(e.getMessage(), e.getStatusCode(), e.getResultCode());
	}
	
	public NormalReturn(String msg, String statusCode, String resultCode){
		this.msg = msg;
		this.statusCode = statusCode;
		if (StringUtils.isNotBlank(resultCode)) {
			this.resultCode = resultCode;
		}		
	}
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	public Object getResult() {
		return result;
	}
	
	public void setResult(Object result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getCostTime() {
		return costTime;
	}
	
	public void setCostTime(long costTime) {
		this.costTime = costTime;
	}

	public Integer getReturnType() {
		return returnType;
	}

	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}

	public Map<String, ArrayList<String>> getValidationErrors() {
		return validationErrors;
	}
	
	public void setValidationErrors(Map<String, ArrayList<String>> validationErrors) {
		this.validationErrors = validationErrors;
	}

	public static NormalReturn check(JSONObject jso, String... params){
		for(String param: params){
			if(!jso.containsKey(param)){
				NormalReturn nr = new NormalReturn();
				nr.setStatusCode("400");
				nr.setMsg(param+" is null");
				return nr;
			}else if(jso.getString(param).isEmpty()){
				NormalReturn nr = new NormalReturn();
				nr.setStatusCode("400");
				nr.setMsg(param+" is empty");				
				return nr;
			}
		}
		return null;
	}
	
}