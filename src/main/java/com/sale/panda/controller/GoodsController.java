/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.controller;

import com.sale.panda.controller.model.BaseResult;
import com.sale.panda.controller.model.SkuModel;
import com.sale.panda.controller.model.SpuModel;
import com.sale.panda.controller.model.SpuPageQueryModel;
import com.sale.panda.dao.entity.Sku;
import com.sale.panda.dao.entity.Spu;
import com.sale.panda.dao.entity.SpuPageQuery;
import com.sale.panda.manager.SkuManager;
import com.sale.panda.manager.SpuManager;
import com.sale.panda.manager.entity.PageQueryResult;

import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 商品相关
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 17:31
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private SpuManager spuManager;

    @Resource
    private SkuManager skuManager;

    @GetMapping("/test")
    public String test(){
        return "Hi!";
    }

    @PostMapping("/addSpu")
    public BaseResult addSpu(@RequestBody SpuModel spuModel){
        Spu spu = new Spu();
        BeanUtils.copyProperties(spuModel,spu);
        spuManager.insert(spu);
        return BaseResult.buildSuccess();
    }

    @PostMapping("/updateSpu")
    public BaseResult updateSpu(@RequestBody SpuModel spuModel){
        Spu spu = new Spu();
        BeanUtils.copyProperties(spuModel,spu);
        spuManager.update(spu);
        return BaseResult.buildSuccess();
    }

    @PostMapping("/addSku")
    public BaseResult addSku(@RequestBody SkuModel skuModel){
        Sku sku = new Sku();
        BeanUtils.copyProperties(skuModel,sku);
        skuManager.insert(sku);
        return BaseResult.buildSuccess();
    }

    @PostMapping("/pageQuerySpu")
    public BaseResult<List<Spu>> pageQuerySpu(@RequestBody SpuPageQueryModel spuModel){
        SpuPageQuery pageQuery = new SpuPageQuery();
        BeanUtils.copyProperties(spuModel,pageQuery);
        PageQueryResult<List<Spu>> result = spuManager.pageQuery(pageQuery);
        return BaseResult.buildSuccess(result.getData(),result.getTotal());
    }

    @GetMapping("/listSkuBySpuId")
    public BaseResult<List<Sku>> listSkuBySpuId(@RequestParam(required = true) Integer spuId){
        return BaseResult.buildSuccess(skuManager.listSkuBySpuId(spuId));
    }

}
