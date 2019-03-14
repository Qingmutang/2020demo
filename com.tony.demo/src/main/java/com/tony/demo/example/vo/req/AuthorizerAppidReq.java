package com.tony.demo.example.vo.req;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tony.demo.example.constant.ExampleRequestValidate;
import com.tony.demo.misc.base.PageInfo;

import lombok.Data;

@Data
public class AuthorizerAppidReq implements Serializable{

	private static final long serialVersionUID = -7896276990773968868L;

	private Long id;

    private String componentAppid;

    private String authorizerAppid;

    private String groupId;

    private String microName;

    private Byte status;
    
    private List<Byte> statusList;

    private String version;

    private Byte versionStatus;

    private Byte serviceType;

    private String userName;

    private Byte createdByWx;
    
    private PageInfo pageInfo;
    
    public ExampleRequestValidate validateReq(){
    	/**校验必填参数  校验参数是否满足*/
    	if(StringUtils.isBlank(groupId)) {
    		return ExampleRequestValidate.GROUPID_NULL;
    	}
    	
    	if(StringUtils.isBlank(authorizerAppid)) {
    		return ExampleRequestValidate.AUTHORIZATION_APPID_NULL;
    	}
    	
    	return ExampleRequestValidate.SUCCESS;
    	
    }
    
}
