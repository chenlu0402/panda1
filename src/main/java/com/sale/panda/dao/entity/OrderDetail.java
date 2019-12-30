/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.dao.entity;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * 订单详细
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 12:02
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetail {

    String orderId;
    String skuId;
    Integer count;
    BigDecimal amount;
    /**
     * 0-会员折扣 1-立减折扣
     */
    Integer discountType;
    /**
     * 折扣金额
     */
    BigDecimal discountAmount;
}
