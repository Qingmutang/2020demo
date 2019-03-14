package com.tony.demo.misc.service.content;

import org.apache.commons.lang3.StringUtils;

import com.tony.demo.misc.vo.resp.BaseWechatContent;

public abstract class AbstractMessageXmlContent implements IMessageXmlContent {

	@Override
	public String xmlContent(BaseWechatContent content, String toUserName, String fromUserName) {
		if(content==null||StringUtils.isBlank(content.getContent())||StringUtils.isBlank(toUserName)||StringUtils.isBlank(fromUserName)) {
			return "";
		}
		
		return this.getXmlContent(content, toUserName, fromUserName); 
	}

	public abstract String getXmlContent(BaseWechatContent content, String toUserName, String fromUserName);
}
