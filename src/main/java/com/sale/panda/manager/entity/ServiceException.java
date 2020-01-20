package com.sale.panda.manager.entity;

import com.sale.panda.manager.constants.ResponseStatus;

public class ServiceException extends RuntimeException {


    private Integer code;

    private static final long serialVersionUID = 2797101169777619442L;

    public ServiceException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public ServiceException(ResponseStatus responseStatus) {
        super(responseStatus.getDesc());
        this.code = responseStatus.getCode();
    }

    public ServiceException(Exception e) {
        super(e);
    }

    public Integer getCode() {
        return code;
    }
}
