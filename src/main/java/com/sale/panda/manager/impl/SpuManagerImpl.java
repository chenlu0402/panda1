/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.manager.impl;

import com.sale.panda.dao.SpuMapper;
import com.sale.panda.dao.entity.Spu;
import com.sale.panda.manager.SpuManager;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 17:38
 */
@Component
public class SpuManagerImpl implements SpuManager {

    @Resource
    private SpuMapper spuMapper;

    @Override
    public Integer insert(Spu spu) {
        return spuMapper.insert(spu);
    }

    @Override
    public Integer batchInsert(List<Spu> goods) {
        return spuMapper.batchInsert(goods);
    }

    @Override
    public Integer update(Spu spu) {
        return spuMapper.update(spu);
    }
}
