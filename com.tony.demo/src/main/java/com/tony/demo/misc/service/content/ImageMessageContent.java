package com.tony.demo.misc.service.content;

import org.springframework.stereotype.Component;

import com.tony.demo.misc.vo.resp.BaseWechatContent;

import lombok.extern.slf4j.Slf4j;

@Component("xmlmessage0")
@Slf4j
public class ImageMessageContent extends AbstractMessageXmlContent {

	@Override
	public String getXmlContent(BaseWechatContent content, String toUserName, String fromUserName) {
		String replyContent = null;

		try {
			
		} catch (Exception e) {
			log.error("关注事件消息回复解析失败", e);
		}
		return replyContent;
	}

}
