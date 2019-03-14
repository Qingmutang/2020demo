package com.tony.demo.misc.service.content;

import com.tony.demo.misc.vo.resp.BaseWechatContent;
/**
 * 实现策略模式，根据content类型调用不同的模板
 * @author zhaoliang
 *
 */
public interface IMessageXmlContent {
	/**
	 * 转换成消息xml格式
	 * @param content
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	String xmlContent(BaseWechatContent content,String toUserName,String fromUserName);

}
