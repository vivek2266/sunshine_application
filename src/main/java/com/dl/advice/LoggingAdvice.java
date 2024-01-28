package com.dl.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAdvice {

	@Pointcut("execution(* com.dl.*.*.*(..))")
	private void logPointCut() {

	}

	//@Before("logPointCut()")
	public void logRequest(JoinPoint joinPoint) throws JsonProcessingException {
		log.info("class name {} ,method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
		log.info("Request Body {} ", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
	}
	//@AfterReturning(value = "execution (* com.dl.controller.*.*(..))",returning = "object")
    public void logResponse(JoinPoint joinPoint,Object object) throws JsonProcessingException {
        log.info("LoggingAdvice::logResponse class name {} ,method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
        log.info("LoggingAdvice::logResponse Response Body {} ", new ObjectMapper().writeValueAsString(object));
    }

    //@After(value = "execution (* com.dl.controller.*.*(..))")
    public void logResponse(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("LoggingAdvice::logResponse class name {} ,method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
        log.info("LoggingAdvice::logResponse Response Body {} ", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }
}
