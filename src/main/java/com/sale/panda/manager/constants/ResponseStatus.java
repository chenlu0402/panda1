/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.manager.constants;

/**
 * 状态
 *
 * @author chenlu
 * @version V1.0
 * @since 2020-01-05 15:40
 */
public enum ResponseStatus {

    SUCCESS(0,"成功！"),
    SERVER_ERROR(500,"服务器内部错误！"),
    FILE_TYPE_NOT_SUPPORT(501,"上传文件格式不正确,仅支持excel文件！"),
    EMPTY_UPLOAD(502,"导入的数据为空！"),

    ;

    private final int code;

    private final String desc;

    ResponseStatus(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
