package com.sale.panda.controller.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: chenlu
 * @description:
 * @date:2020/3/15 10:41
 */
@Slf4j
@WebFilter(urlPatterns={"/order/*","/goods/*"},filterName="controllerFilter")
public class ControllerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        String uri = req.getRequestURI();
        String param = JSON.toJSONString(req.getParameterMap());
        log.info("request uri={},request param={}",uri,param);
        filterChain.doFilter(request, response);
    }
}
