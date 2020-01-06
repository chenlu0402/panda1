/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.dao;

import com.sale.panda.dao.entity.Spu;
import com.sale.panda.dao.entity.SpuPageQuery;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 商品spu
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 10:00
 */
@Mapper
public interface SpuMapper {

    Integer insert(Spu spu);

    Integer batchInsert(List<Spu> goods);

    Integer update(Spu spu);

    Integer batchUpdateByIds(Spu spu);

    List<Spu> pageQuery(SpuPageQuery pageQuery);


}
