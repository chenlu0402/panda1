/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.manager;

import com.sale.panda.dao.entity.Sku;

import java.util.List;

/**
 * TODO
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 18:16
 */
public interface SkuManager {

    Integer insert(Sku sku);

    Integer batchInsert(List<Sku> goods);

    Integer update(Sku sku);
}
