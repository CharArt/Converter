package com.charartpav.converte.aspect;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/* @author Artem Charykov*/

@Component
@Aspect
public class LogAspect {

	@Around("execution(* com.charartpav.converte.service.UserListService.*(..))")
	public Object aroundServiceMethodExecution (ProceedingJoinPoint pjp) {
		Long start = System.currentTimeMillis();
		Object  res = null;
		try {
			res = pjp.proceed();
		}
		catch (Throwable ex) {
			Logger.getLogger(LogAspect.class.getName()).log(Level.SEVERE, null, ex);
		}
		Long end = System.currentTimeMillis();
		System.out.println("Execution of method" + pjp.getSignature() + " took " + (end - start) + " msec.");
		return res;
	}
}