package com.tony.demo.misc.base;

import java.io.Serializable;

import javax.swing.text.Utilities;

import com.alibaba.fastjson.JSONObject;

public class ServiceRequest implements Serializable{

	private static final long serialVersionUID = 4423576079327417300L;

	private String appCode;
	private Long currentUser;
	private String methodName;
	private String body;
	
	private JSONObject json;
	
	private boolean appCodeChecked;
	
	private Object contract;
	private Object model;
	
	private String clientId;
	private String requestId;
	
	@SuppressWarnings("unchecked")
	public <T> T getModel(){
		return (T)model;
	}
	
	public void setModel(Object model){
		this.model = model;
	}	
	
	@SuppressWarnings("unchecked")
	public <T> T getContract(){
		return (T)contract;
	}
	
	public void setContract(Object contract){
		this.contract = contract;
	}
	
	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
		
		if(json != null)
			body = json.toJSONString();
		else
			body = null;
	}


	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getBody()
	{
		return body;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public boolean isAppCodeChecked() {
		return appCodeChecked;
	}

	public void setAppCodeChecked(boolean appCodeChecked) {
		this.appCodeChecked = appCodeChecked;
	}

	public Long getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Long currentUser) {
		this.currentUser = currentUser;
	}
	
	public String getClientId()
	{
		return clientId;
	}

	public String getRequestId()
	{
		return requestId;
	}


}
