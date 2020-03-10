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
 * 订单记录
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 09:53
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderModel {
    String orderId;
    Integer totalCount;
    BigDecimal smallChanges;
    BigDecimal totalAmount;
    Integer RefundCount;
    BigDecimal refundAmount;
    String remark;
    String creator;
    String createdTime;
}
