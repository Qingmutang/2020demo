package com.tony.demo.misc.service;

import com.tony.demo.misc.base.Response;
import com.tony.demo.misc.vo.req.OrderRequest;

public interface IOrderDemoService {
	
	public Response<String> getOrderInfo(OrderRequest req);

}
