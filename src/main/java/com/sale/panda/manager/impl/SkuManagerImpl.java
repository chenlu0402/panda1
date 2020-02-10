/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.manager.impl;

import com.sale.panda.dao.SkuMapper;
import com.sale.panda.dao.entity.Goods;
import com.sale.panda.dao.entity.Sku;
import com.sale.panda.dao.entity.GoodsPageQuery;
import com.sale.panda.manager.SkuManager;
import com.sale.panda.manager.entity.PageQueryResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 18:17
 */
@Component
public class SkuManagerImpl implements SkuManager {

    @Resource
    private SkuMapper skuMapper;

    @Override
    public Integer insert(Sku sku) {
        return skuMapper.insert(sku);
    }

    @Override
    public Integer batchInsert(List<Sku> goods) {
        return skuMapper.batchInsert(goods);
    }

    @Override
    public Integer update(Sku sku) {
        return skuMapper.update(sku);
    }

    @Override
    public PageQueryResult<List<Goods>> pageQuery(GoodsPageQuery pageQuery) {
        return new PageQueryResult<List<Goods>>(skuMapper.count(pageQuery), skuMapper.pageQuery(pageQuery));
    }

    @Override
    public List<Goods> listGoodsBySpuId(Integer spuId) {
        return skuMapper.listGoodsBySpuId(spuId);
    }
}
