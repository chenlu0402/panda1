/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.dao;

import com.sale.panda.dao.entity.Goods;
import com.sale.panda.dao.entity.GoodsPageQuery;
import com.sale.panda.dao.entity.Sku;
import com.sale.panda.dao.entity.SubCountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品sku
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 10:00
 */
@Mapper
public interface SkuMapper {

    Integer insert(Sku sku);

    Integer batchInsert(List<Sku> goods);

    Integer update(Sku sku);

    Integer batchUpdate(List<Sku> skus);

    Sku getSku(@Param("skuId") Integer skuId);

    List<Goods> pageQuery(GoodsPageQuery pageQuery);

    List<Goods> listGoodsBySpuId(@Param("spuId") Integer spuId);

    Integer count(GoodsPageQuery pageQuery);

    /**
     * 跟据订单明细减去卖出的库存
     * @param list
     * @return
     */
    Integer subCount(List<SubCountInfo> list);

}
