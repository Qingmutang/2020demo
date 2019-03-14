package com.tony.demo.misc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tony.demo.misc.aop.Authorization;
import com.tony.demo.misc.base.Response;
import com.tony.demo.misc.vo.req.OrderRequest;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderDemoController {
	
	@PostMapping("/info")
	@Authorization
	public Response<String> getOrderInfo(@RequestBody OrderRequest order) {
		log.info("msg:order {}",order.toString() );  
		
		return new Response<String>("orderInfo:"+order);
	}
	
	@PostMapping("/infoNormal")
	public Response<String> getOrderInfoNormal(@RequestBody OrderRequest order) {
		log.info("msg:order {}",order.toString() );  
		
		return new Response<String>("orderInfo:"+order);
	}

}
