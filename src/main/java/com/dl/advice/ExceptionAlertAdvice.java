package com.dl.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ExceptionAlertAdvice {

	@Pointcut("execution (* com.dl.*.*.*(..))")
	public void alertFor() {

	}

	@AfterThrowing(value = "alertFor()", throwing = "exception")
	public void captureErrorAndSetAlerts(JoinPoint joinPoint, Exception exception) {
		log.error("Exception occurs in {}", joinPoint.getSignature());
		log.error("Exception message  {}", exception.getMessage());
	}
}