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
 * TODO
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 18:22
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpuPageQuery {

    String spuName;
}
