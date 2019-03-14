package com.tony.demo.example.service;

import com.tony.demo.example.vo.req.AuthorizerAppidReq;
import com.tony.demo.example.vo.resp.AuthorizerAppidListResp;
import com.tony.demo.example.vo.resp.AuthorizerAppidResp;
import com.tony.demo.misc.base.Response;

public interface IAuthorizerAppidService {
    /**查询单条信息*/
	public Response<AuthorizerAppidResp> getAuthorizerAppidInfo(AuthorizerAppidReq req);
	
	/**查询集合信息*/
	public Response<AuthorizerAppidListResp> queryAuthorizerAppidInfoByPageInfo(AuthorizerAppidReq req);
	
	/**添加和修改信息*/
	public Response<AuthorizerAppidResp> insertAuthorizerAppidInfo(AuthorizerAppidReq req);
}
