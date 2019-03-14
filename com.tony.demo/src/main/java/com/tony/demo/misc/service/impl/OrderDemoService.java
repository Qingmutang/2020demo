package com.tony.demo.misc.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tony.demo.misc.base.Response;
import com.tony.demo.misc.constant.RequestValidate;
import com.tony.demo.misc.service.IOrderDemoService;
import com.tony.demo.misc.service.content.IMessageXmlContent;
import com.tony.demo.misc.vo.req.OrderRequest;
import com.tony.demo.misc.vo.resp.BaseWechatContent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderDemoService implements IOrderDemoService {
	
    @Resource
    private Map<String, IMessageXmlContent> messageXmlContent;

	@Override
	public Response<String> getOrderInfo(OrderRequest req) {
		log.info("获取订单信息 start-> req:{}",JSONObject.toJSON(req));
		Response<String> resp = new Response<String>();
		String result = null;
        try {
            RequestValidate requestValidate = req.validateRequest();
            if(!requestValidate.equals(RequestValidate.SUCCESS)) {
            	resp.setResultCode(requestValidate.getCode()+"");
            	resp.setMsg(requestValidate.getMessage());
            	return resp;
            }
            
        	
           result = "order info by orderService";
           resp.setResult(result);
           
           log.info("获取订单信息 stop-> resp:{}",JSONObject.toJSON(resp));

        } catch (Exception e) {
            log.info("自定义菜单创建接口  error {}", e);
        }
      
       return resp;
	}
	/**
	 * 这里调用策略模式，使用map直接自动装载，更具类型 get不同的实现
	 */
	public void testUserModel() {
		
		  Byte contentType = 0;
          String messageXmlContentKey = "xmlmessage"+contentType;
          IMessageXmlContent xmlContent = messageXmlContent.get(messageXmlContentKey);
          BaseWechatContent content = new BaseWechatContent();
          
          String content2 = xmlContent.xmlContent(content, "toUserName", "fromUserName");
           
	}

}
