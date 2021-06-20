package com.example.SpringExample.aop;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAspectJAutoProxy
public class AroundLoggingAspect {
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Around("within(com.springboot.assignment..*)")
	public Object aroundLogin(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		String method = theProceedingJoinPoint.getSignature().toShortString();
		myLogger.info("=============================== Executing @Around (finally) on method: "+method);
		long begin = System.currentTimeMillis();
		Object result = null;
		try {
				result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			myLogger.warning(e.getMessage());
			result="Exception handled in @Around";
		}
		long end = System.currentTimeMillis();
		myLogger.info("Duration: "+(end-begin)/1000+" seconds");
		return result;
	}
	
}
