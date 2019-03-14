package com.tony.demo.misc.vo.req;

import org.springframework.util.StringUtils;

import com.tony.demo.misc.base.BaseRequest;
import com.tony.demo.misc.constant.RequestValidate;

import lombok.Data;

@Data
public class OrderRequest extends BaseRequest{
	private static final long serialVersionUID = -4483260041255776202L;
	private String orderId;

	@Override
	public String toString() {
		return "OrderRequest [orderId=" + orderId + ", getToken()=" + getToken() + "]";
	}
	
	public RequestValidate validateRequest() {
		
		if(StringUtils.isEmpty(orderId)) {
			return RequestValidate.ORDERID_NULL;
		}
		
		if(StringUtils.isEmpty(getToken())) {
			return RequestValidate.TOKEN_NULL;
		}
		
		return RequestValidate.SUCCESS;
	}
}
