package com.dl.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class CustomLoggingFramework {

	@Around("execution (* com.dl.*.*.*(..))")
	public Object captureRequestAndResponse(ProceedingJoinPoint pjp) throws Throwable {
		// execute your before logic
		System.out.println("====before start====");
		log.info("execution started {} ", pjp.getSignature());
		log.info("Request body {}", new ObjectMapper().writeValueAsString(pjp.getArgs()));
		System.out.println("====before end====");

		Object obj = pjp.proceed();
		// after logic
		System.out.println("====after start====");

		log.info("execution ended {} ", pjp.getSignature());
		log.info("Response  body {}", new ObjectMapper().writeValueAsString(obj));
		System.out.println("====after end====");

		return obj;
	}
}
