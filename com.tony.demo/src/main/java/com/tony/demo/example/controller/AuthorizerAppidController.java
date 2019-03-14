package com.tony.demo.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.tony.demo.example.service.IAuthorizerAppidService;
import com.tony.demo.example.vo.req.AuthorizerAppidReq;
import com.tony.demo.example.vo.resp.AuthorizerAppidListResp;
import com.tony.demo.example.vo.resp.AuthorizerAppidResp;
import com.tony.demo.misc.base.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthorizerAppidController {
	@Autowired
	IAuthorizerAppidService authorizerAppidService;
	
    @PostMapping("/getAuthorizerAppidInfo")
	public Response<AuthorizerAppidResp> getAuthorizerAppidInfo(@RequestBody AuthorizerAppidReq req) {
		log.info("getAuthorizerAppidInfo 获取信息 start-> req:{}",JSONObject.toJSON(req));
		Response<AuthorizerAppidResp> authorizerAppidInfo = new Response<AuthorizerAppidResp>();
		try {
			authorizerAppidInfo = authorizerAppidService.getAuthorizerAppidInfo(req);
		} catch (Exception e) {
			log.error("getAuthorizerAppidInfo 获取信息 error-> e:{}",JSONObject.toJSON(e));
		}
		log.info("getAuthorizerAppidInfo 获取信息 end-> resp:{}",JSONObject.toJSON(authorizerAppidInfo));
		return authorizerAppidInfo;
	}

    @PostMapping("/queryAuthorizerAppidInfoList")
	public Response<AuthorizerAppidListResp> queryAuthorizerAppidInfoByPageInfo(@RequestBody AuthorizerAppidReq req) {
		log.info("getAuthorizerAppidInfoList 获取信息 start-> req:{}",JSONObject.toJSON(req));
		Response<AuthorizerAppidListResp> resp = new Response<AuthorizerAppidListResp>();
		try {
			resp = authorizerAppidService.queryAuthorizerAppidInfoByPageInfo(req);
		} catch (Exception e) {
			log.error("getAuthorizerAppidInfoList 获取信息 error-> e:{}",JSONObject.toJSON(e));
		}
		log.info("getAuthorizerAppidInfoList 获取信息 end-> req:{}",JSONObject.toJSON(resp));
		
		return resp;
	}
    
    @PostMapping("/insertAuthorizerAppidInfo")
    public Response<AuthorizerAppidResp> insertAuthorizerAppidInfo(@RequestBody AuthorizerAppidReq req){
    	log.info("insertAuthorizerAppidInfo 获取信息 start-> req:{}",JSONObject.toJSON(req));
    	Response<AuthorizerAppidResp> resp = new Response<AuthorizerAppidResp>();
		try {
			resp = authorizerAppidService.insertAuthorizerAppidInfo(req);
		} catch (Exception e) {
			log.error("insertAuthorizerAppidInfo 获取信息 error-> e:{}",JSONObject.toJSON(e));
		}
		log.info("insertAuthorizerAppidInfo 获取信息 end-> req:{}",JSONObject.toJSON(resp));
		
		return resp;
    }

}
