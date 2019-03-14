package com.tony.demo.misc.aop;


import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tony.demo.misc.base.NormalReturn;
import com.tony.demo.misc.base.ServiceRequest;
import com.tony.demo.misc.exception.ServiceException;
import com.tony.demo.misc.utils.DtoUtil;
import com.tony.demo.misc.utils.ValidationUtil;

@Aspect
@Order(1)
@Component
public class ContractManager {
	
	private static final Logger logger = LoggerFactory.getLogger(ContractManager.class);
	
	@Pointcut("@annotation (com.tony.demo.misc.aop.Contract)")
	private void contract(){};

	@Pointcut("@annotation (com.tony.demo.misc.aop.Model)")
	private void model(){};
	
	@Around("contract()")
	public Object contract(ProceedingJoinPoint joinPoint) throws Throwable{
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Object[] args = joinPoint.getArgs();
		
		if(args != null && args.length > 0 && args[0] instanceof ServiceRequest
				&& signature.getReturnType().getName().equals("com.tony.demo.misc.base.NormalReturn")){
			ServiceRequest req = (ServiceRequest)args[0];
			Method method = DtoUtil.getMethod(joinPoint);
			Contract annotation = method.getAnnotation(Contract.class);
			if(annotation != null){
				Class<?> clazz = annotation.value();
				if(clazz != null){
					Object o = null;
					try {
						o = JSONObject.toJavaObject(req.getJson(), clazz);
					} catch (Exception e) {
						logger.warn("contract process fail.", e);
						return new NormalReturn(ServiceException.PARAMS_EXCEPTION);
					}
					
					NormalReturn nr = ValidationUtil.validate(o);
					if(nr != null){
						return nr;
					}
					req.setContract(o);
				}
			}
		}
		return joinPoint.proceed();
	}
	
	@Around("model()")
	public Object model(ProceedingJoinPoint joinPoint) throws Throwable{
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Object[] args = joinPoint.getArgs();
		
		if(args != null && args.length > 0 && args[0] instanceof ServiceRequest
				&& signature.getReturnType().getName().equals("com.tony.demo.misc.base.NormalReturn")){
			ServiceRequest req = (ServiceRequest)args[0];
			Method method = DtoUtil.getMethod(joinPoint);
			Model annotation = method.getAnnotation(Model.class);
			if(annotation != null){
				Class<?> clazzContract = annotation.contract();
				Class<?> clazzModel = annotation.model();
				if(clazzContract != null && clazzModel != null){
					Object o = JSONObject.toJavaObject(req.getJson(), clazzContract);
					NormalReturn nr = ValidationUtil.validate(o);
					if(nr != null){
						return nr;
					}
					req.setContract(o);
					req.setModel(DtoUtil.contract2Model(o, clazzModel));
				}
			}
		}
		return joinPoint.proceed();
	}
}
