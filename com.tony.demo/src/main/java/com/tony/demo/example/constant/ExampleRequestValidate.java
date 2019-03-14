package com.tony.demo.example.constant;

public class ExampleRequestValidate {
	//请求参数类型
		public static ExampleRequestValidate SUCCESS = new ExampleRequestValidate(200,"ok");
		public static ExampleRequestValidate TOKEN_NULL = new ExampleRequestValidate(501,"token is null");
		public static ExampleRequestValidate ORDERID_NULL = new ExampleRequestValidate(501,"orderId is null");
		public static ExampleRequestValidate GROUPID_NULL = new ExampleRequestValidate(501,"groupId is null");
		public static ExampleRequestValidate AUTHORIZATION_APPID_NULL = new ExampleRequestValidate(501,"authorizerAppid is null");
		
		private Integer code ; 
		private String message;

		ExampleRequestValidate(Integer code, String message) {
			this.code = code;
			this.message = message;
		}


		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
}
