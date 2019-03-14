package com.tony.demo.example.vo.resp;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class AuthorizerAppidResp implements Serializable{
	
	private static final long serialVersionUID = 7969017162653577489L;

	@JSONField(name ="componentAppid")
	@JsonProperty("componentAppid")
	private String componentAppid;

    private String authorizerAppid;

    private String groupId;

    private String microName;

    private List<Byte> status;

    private String version;

    private Byte versionStatus;

    private Byte serviceType;

    private String userName;

    private Byte createdByWx;
}
