package com.sale.panda.controller;

import com.sale.panda.controller.model.BaseResult;
import com.sale.panda.manager.constants.ResponseStatus;
import com.sale.panda.manager.entity.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: chenlu
 * @description:所有需要抛给前端的异常都throw上去，统一在controller处理
 * @date:2019/8/12 15:48
 */
@RestControllerAdvice
@Slf4j
public class TotalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        if (e instanceof ServiceException) {
            ServiceException se = (ServiceException)e;
            return BaseResult.buildFail(se.getCode(), se.getMessage());
        }
        return BaseResult.buildFail(ResponseStatus.SERVER_ERROR);
    }
}
