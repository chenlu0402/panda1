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
import com.sale.panda.dao.entity.GoodsPageQuery;
import com.sale.panda.dao.entity.Sku;
import com.sale.panda.dao.entity.SubCountInfo;
import com.sale.panda.manager.SkuManager;
import com.sale.panda.manager.entity.PageQueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public Sku getSku(Integer skuId) {
        return skuMapper.getSku(skuId);
    }

    @Override
    public PageQueryResult<List<Goods>> pageQuery(GoodsPageQuery pageQuery) {
        return new PageQueryResult<List<Goods>>(skuMapper.count(pageQuery), skuMapper.pageQuery(pageQuery));
    }

    @Override
    public List<Goods> listGoodsBySpuId(Integer spuId) {
        List<Goods> result = skuMapper.listGoodsBySpuId(spuId);
        if(!CollectionUtils.isEmpty(result)){
            result.stream().forEach(good->{
                good.setCount(1);
            });
        }
        return result;
    }

    @Override
    public List<Goods> listSkuForSale(Integer spuId) {
        List<Goods> result = new ArrayList<>();
        List<Goods> goodsList = skuMapper.listGoodsBySpuId(spuId);
        if(!CollectionUtils.isEmpty(goodsList)){
            goodsList.stream().forEach(good -> {
                if(good.getCount() == 0){
                    return;
                }
                String color = StringUtils.isBlank(good.getColor()) ? "" : good.getColor();
                String feature1 = StringUtils.isBlank(good.getFeature1()) ? "" : good.getFeature1();
                String feature2 = StringUtils.isBlank(good.getFeature2()) ? "" : good.getFeature2();
                String feature3 = StringUtils.isBlank(good.getFeature3()) ? "" : good.getFeature3();
                good.setSkuName(good.getTypeName() + good.getSize() + color + feature1 + feature2 + feature3);
                result.add(good);
            });
        }
        return result;
    }

    @Override
    public Integer subCount(List<SubCountInfo> list) {
        return skuMapper.subCount(list);
    }
}
