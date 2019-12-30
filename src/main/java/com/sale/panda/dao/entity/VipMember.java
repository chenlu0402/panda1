/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * 会员实体类
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 09:34
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VipMember {

    String mobile;

    String customerName;

    /**
     * 消费的金额
     */
    BigDecimal monetary;

    /**
     * 享受的折扣
     */
    Integer discount;

    /**
     * 注册时间
     */
    Date registTime;

    /**
     * 备注
     */
    String remark;
}
