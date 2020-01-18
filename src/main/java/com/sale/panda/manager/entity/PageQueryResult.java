/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.manager.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * TODO
 *
 * @author chenlu
 * @version V1.0
 * @since 2020-01-18 15:28
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageQueryResult<T> {

    Integer total;

    T data;

    public PageQueryResult(Integer total,T data){
        this.total = total;
        this.data = data;
    }

}
