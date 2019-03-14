package com.tony.demo.misc.exception;

public class ServiceException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6147957033883568347L;
	/** 成功 */
	public static final String API_OK					= "200";
	/** 部分成功 */
	public static final String API_PARTIAL_OK			= "206";
	/** 格式错误 */
	public static final String BAD_REQUEST				= "400";
	/** 对象不存在 */
	public static final String NOT_EXIST				= "404";
	/** 服务器内部错误 */
	public static final String GENERAL_ERROR			= "500";
	/** 参数验证错误 */
	public static final String VALIDATE_ERROR			= "501";
	/** 接口已关闭 */
	public static final String NOT_IMPLEMENTED			= "502";
	/** 频次超限 */
	public static final String FREQUENCY_LIMIT_ERROR	= "503";
	/** 访问被禁 */
	public static final String FORBID_ERROR 			= "504";
	/** 请求参数非法 */
	public static final String PARAMS_ERROR				= "505";
	/** 重复请求 */
	public static final String REPEATED_REQ_ERROR		= "506";
	/** 访问被限流 */
	public static final String TRAFFIC_LIMIT_ERROR		= "507";
	/** APP CODE 不存在 */
	public static final String APPCODE_ERROR			= "600";
	/** 用户验证错误 */
	public static final String AUTHEN_ERROR				= "701";
	/** 用户授权错误 */
	public static final String AUTHOR_ERROR				= "702";
	/** 网络错误 */
	public static final String NETWORK_ERROR			= "801";
	/** 外部API调用失败 */
	public static final String OUTER_API_INVOKE_FAIL	= "802";
	/** 内部API调用失败 */
	public static final String INNER_API_INVOKE_FAIL	= "803";
	/** 签名验证失败 */
	public static final String SIGN_VERIFY_ERROR		= "901";
	/** 登录已失效 */
	public static final String LOGIN_INVALID			= "904";
	/** 未登录 */
	public static final String NOT_LOGGED_IN			= "907";
	
	public static final String TOKEN_NULL_ERROR         = "999";
	
	public static final ServiceException BAD_REQUEST_EXCEPTION		= new ServiceException("格式错误", BAD_REQUEST);
	public static final ServiceException NOT_EXIST_EXCEPTION		= new ServiceException("对象不存在", NOT_EXIST);
	public static final ServiceException GENERAL_EXCEPTION			= new ServiceException("服务器内部错误", GENERAL_ERROR);
	public static final ServiceException VALIDATE_EXCEPTION			= new ServiceException("参数验证错误", VALIDATE_ERROR);
	public static final ServiceException NOT_IMPLEMENTED_EXCEPTION	= new ServiceException("接口未实现", NOT_IMPLEMENTED);
	public static final ServiceException APPCODE_EXCEPTION			= new ServiceException("应用程序编号已存在", APPCODE_ERROR);
	public static final ServiceException AUTHEN_EXCEPTION			= new ServiceException("用户认证失败", AUTHEN_ERROR);
	public static final ServiceException AUTHOR_EXCEPTION			= new ServiceException("授权验证失败", AUTHOR_ERROR);
	public static final ServiceException NETWORK_EXCEPTION			= new ServiceException("网络错误", NETWORK_ERROR);
	public static final ServiceException SIGN_VERIFY_EXCEPTION		= new ServiceException("签名验证失败", SIGN_VERIFY_ERROR);
	public static final ServiceException PARAMS_EXCEPTION			= new ServiceException("请求参数非法", PARAMS_ERROR);
	public static final ServiceException REPEATED_REQ_EXCEPTION		= new ServiceException("请勿重复请求", REPEATED_REQ_ERROR);

	public static final ServiceException TOKEN_NULL_EXCEPTION		= new ServiceException("Token不存在", TOKEN_NULL_ERROR);
	
	private String statusCode;
	private String resultCode;
	
	public ServiceException() {
		
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(String message,String statusCode) {
		super(message);
		setStatusCode(statusCode);
	}
	
	public ServiceException(String message,String statusCode,String resultCode) {
		super(message);
		setStatusCode(statusCode);
		setResultCode(resultCode); 
	}
	
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		if (cause instanceof ServiceException) {
			setStatusCode(((ServiceException) cause).getStatusCode());
			setResultCode(((ServiceException) cause).getResultCode());
		}
	}

	public ServiceException(String message, String statusCode, Throwable cause) {
		super(message, cause);
		setStatusCode(statusCode);
	}
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	
}
