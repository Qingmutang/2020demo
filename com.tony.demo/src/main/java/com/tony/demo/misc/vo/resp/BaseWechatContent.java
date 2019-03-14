package com.tony.demo.misc.vo.resp;

import java.io.Serializable;

import lombok.Data;

@Data
public class BaseWechatContent implements Serializable{
	/**
	 * 店鋪id wechat_menu.group_id
	 *
	 * @mbggenerated
	 */
	private String groupId;

	/**
	 * 第三方平台appid wechat_menu.component_appid
	 *
	 * @mbggenerated
	 */
	private String componentAppid;

	/**
	 * 授权方appid wechat_menu.authorizer_appid
	 *
	 * @mbggenerated
	 */
	private String authorizerAppid;
	
	/**
	 * 内容类型：0. 图片（image）、1 .视频（video）、2.语音 （voice）、3.图文（news)、4.文本（text）
	 * wechat_menu.content_type
	 *
	 * @mbggenerated
	 */
	private Byte contentType;

	/**
	 * 点击事件发送的内容 wechat_menu.content
	 *
	 * @mbggenerated
	 */
	private String content;
}
