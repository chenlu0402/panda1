package com.sale.panda.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: chenlu
 * @description:
 * @date:2019/8/12 13:38
 */
@Aspect
@Component
@Slf4j
public class UrlAspect {

    @Resource
    private HttpServletRequest request;

    @Pointcut(value = "execution(* com.sale.panda.controller..*.*(..)) ")
    public void executeService() {
    }

    @Before("executeService()")
    public void doBefore(JoinPoint joinPoint) {
        String url = request.getRequestURI();
        Object[] params = joinPoint.getArgs();
        log.info("request uri={},request param={}",url,params);
    }
}
