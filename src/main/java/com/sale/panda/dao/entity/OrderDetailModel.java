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

/**
 * 订单详细
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 12:02
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailModel {
    String orderId;
    String spuName;
    String size;
    String color;
    Integer skuId;
    Integer count;
    /**
     * 0-无优惠 1-折扣 2-立减
     */
    Integer discountType;
    String discountTypeDesc;
    BigDecimal originAmount;
    BigDecimal amount;
    /**
     * 折扣数值或者立减金额
     */
    BigDecimal discountAmount;
    /**
     * 退货数量
     */
    Integer refundCount;
    /**
     * 退款金额
     */
    BigDecimal refundAmount;
}
