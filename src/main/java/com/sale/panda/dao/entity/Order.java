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
 * 订单记录（支持单件折扣和总价折扣）
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 09:53
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    String orderId;
    /**
     * 会员编号  会员表里的主键id
     */
    Integer vipCode;
    /**
     *  0-会员折扣 1-立减折扣
     */
    Integer orderDiscountType;
    /**
     * 整个订单的折扣
     */
    BigDecimal orderDiscount;
    /**
     * 订单金额
     */
    BigDecimal amount;
    String remark;
    String creator;

}
