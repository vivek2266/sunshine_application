package com.dl.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogExecutionTracker {

	@Around("@annotation(com.dl.annotation.TrackExecutionTime)")
	public Object logExecutionDuration(ProceedingJoinPoint pjp) throws Throwable {
		// before advice
		long startTime = System.currentTimeMillis();
		Object obj = pjp.proceed();
		// after advice
		long endTime = System.currentTimeMillis();
		log.info(" method {} execution takes {} ms times to complete ", pjp.getSignature(), (endTime - startTime));
		return obj;
	}
}
