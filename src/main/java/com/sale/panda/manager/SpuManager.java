/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.manager;

import com.sale.panda.dao.entity.Spu;
import com.sale.panda.dao.entity.SpuPageQuery;
import com.sale.panda.manager.entity.PageQueryResult;

import java.util.List;

/**
 *
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 17:37
 */
public interface SpuManager {

    Integer insert(Spu spu);

    Integer batchInsert(List<Spu> goods);

    Integer update(Spu spu);

    PageQueryResult<List<Spu>> pageQuery(SpuPageQuery pageQuery);
}
