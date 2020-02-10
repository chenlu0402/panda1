/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.dao;

import com.sale.panda.dao.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品sku
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 10:00
 */
@Mapper
public interface GoodsImportDetailMapper {

    Integer insert(Goods good);

    Integer batchInsert(List<Goods> goods);

    Integer update(Goods goods);

    List<Goods> pageQuery(GoodsPageQuery pageQuery);

    Integer count(GoodsPageQuery pageQuery);

}
