/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.manager.impl;

import com.sale.panda.dao.SkuMapper;
import com.sale.panda.dao.entity.Sku;
import com.sale.panda.manager.SkuManager;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 18:17
 */
@Component
public class SkuManagerImpl implements SkuManager{

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
    public List<Sku> listSkuBySpuId(Integer spuId) {
        return skuMapper.listSkuBySpuId(spuId);
    }
}
