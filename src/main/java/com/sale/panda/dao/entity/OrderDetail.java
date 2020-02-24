/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.dao.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

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
    Integer skuId;
    Integer count;
    /**
     * 0-无优惠 1-折扣 2-立减
     */
    Integer discountType;
    BigDecimal originAmount;
    BigDecimal amount;
    /**
     * 折扣数值或者立减金额
     */
    BigDecimal discountAmount;
    Date createdTime;
    Date updated_time;
}
