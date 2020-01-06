/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.controller.model;

import com.sale.panda.manager.utils.ResponseStatus;

/**
 * 结果返回类
 *
 * @author chenlu
 * @version V1.0
 * @since 2020-01-05 15:26
 */
public class BaseResult<T> {

    private int code = 200;

    private String msg = "SUCCESS";

    private T data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    BaseResult(){

    }
    BaseResult(int code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static BaseResult buildSuccess(){
        return new BaseResult();
    }

    public static BaseResult buildSuccess(Object data){
        BaseResult result = new BaseResult();
        result.setData(data);
        return result;
    }

    public static BaseResult buildFail(ResponseStatus status){
        return new BaseResult(status.getCode(),status.getDesc(),null);
    }
}
