/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.controller;

import com.sale.panda.controller.model.SpuModel;
import com.sale.panda.dao.entity.Spu;
import com.sale.panda.manager.SpuManager;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品相关
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 17:31
 */
@RestController("/goods")
public class GoodsController {

    @Resource
    private SpuManager spuManager;

    @PostMapping("/addSpu")
    public void addSpu(@RequestBody SpuModel spuModel){
        Spu spu = new Spu();
        BeanUtils.copyProperties(spuModel,spu);
        spuManager.insert(spu);
    }


}
