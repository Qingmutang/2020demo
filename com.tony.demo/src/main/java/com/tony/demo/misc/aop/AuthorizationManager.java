package com.tony.demo.misc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.tony.demo.misc.base.BaseRequest;
import com.tony.demo.misc.base.Response;
import com.tony.demo.misc.exception.ServiceException;

@Aspect
//@Order(0)
@Component
public class AuthorizationManager {
	
	private static Logger log = LoggerFactory.getLogger(AuthorizationManager.class);

	@Pointcut("@annotation(com.tony.demo.misc.aop.Authorization)")
	//@Pointcut("execution(public * com.tony.demo.misc.controller.*.*(..))")
	public void authPointcut() {
		
	}
	
	@Around("authPointcut()")
	public Object requireAuthorization(ProceedingJoinPoint joinPoint) throws Throwable{
	
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Object[] args = joinPoint.getArgs();

		if (args != null && args.length > 0&&args[0] instanceof BaseRequest) {
			BaseRequest req = (BaseRequest) args[0];
			String token = req.getToken();
			if (StringUtils.isEmpty(token)) {

				return new Response(ServiceException.TOKEN_NULL_EXCEPTION);
			} else {
				return checkToken(joinPoint, token);
			}
		}
		return getNormalResponse("用户没有权限");

	}
	
	private Object checkToken(ProceedingJoinPoint joinPoint, String token) {
		try {
			return joinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Response getNormalResponse(String msg) {
		Response resp = new Response();
		resp.setMsg(msg);
		resp.setResultCode("999");
		return resp;
	}
	
}
