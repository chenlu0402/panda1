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

/**
 * 货物
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 09:51
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Sku {
    Integer skuId;
    String skuName;
    Integer spuId;
    String size;
    String color;
    String feature1;
    String feature2;
    String feature3;
    Integer count;
}
