/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.controller;

import com.sale.panda.controller.model.BaseResult;
import com.sale.panda.controller.model.GoodsModel;
import com.sale.panda.controller.model.GoodsPageQueryModel;
import com.sale.panda.dao.entity.Goods;
import com.sale.panda.dao.entity.GoodsPageQuery;
import com.sale.panda.manager.GoodsImportDetailManager;
import com.sale.panda.manager.SkuManager;
import com.sale.panda.manager.SpuManager;
import com.sale.panda.manager.constants.ResponseStatus;
import com.sale.panda.manager.entity.PageQueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

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

    @Resource
    private GoodsImportDetailManager detailManager;

    @GetMapping("/test")
    public String test(){
        return "Hi!";
    }
    @PostMapping("/updateImportGoods")
    public BaseResult updateGoods(@RequestBody GoodsModel goodsModel){
        Goods good = new Goods();
        BeanUtils.copyProperties(goodsModel,good);
        if(StringUtils.isNotBlank(goodsModel.getInPrice())){
            good.setInPrice(new BigDecimal(goodsModel.getInPrice()));
        }
        if(StringUtils.isNoneBlank(goodsModel.getSalePrice())){
            good.setSalePrice(new BigDecimal(goodsModel.getSalePrice()));
        }
        detailManager.update(good);
        return BaseResult.buildSuccess();
    }

    @PostMapping("/batchAddImportGoods")
    public BaseResult batchAddGoods(@RequestBody List<Goods> allData){
        detailManager.batchInsert(allData);
        return BaseResult.buildSuccess();
    }

    @PostMapping("/pageQueryGoods")
    public BaseResult<List<Goods>> pageQueryGoods(@RequestBody GoodsPageQueryModel skuModel){
        GoodsPageQuery pageQuery = new GoodsPageQuery();
        BeanUtils.copyProperties(skuModel,pageQuery);
        PageQueryResult<List<Goods>> result = skuManager.pageQuery(pageQuery);
        return BaseResult.buildSuccess(result.getData(),result.getTotal());
    }

    @PostMapping("/pageQueryImportGoods")
    public BaseResult<List<Goods>> pageQueryImportGoods(@RequestBody GoodsPageQueryModel goodsModel){
        GoodsPageQuery pageQuery = new GoodsPageQuery();
        BeanUtils.copyProperties(goodsModel,pageQuery);
        if(StringUtils.isNotBlank(goodsModel.getDatetimeRange())){
            String[] datetimeRange = goodsModel.getDatetimeRange().split("~");
            pageQuery.setCreatedTimeStart(datetimeRange[0]);
            pageQuery.setCreatedTimeEnd(datetimeRange[1]);
        }
        PageQueryResult<List<Goods>> result = detailManager.pageQuery(pageQuery);
        return BaseResult.buildSuccess(result.getData(),result.getTotal());
    }

    @GetMapping("/listGoodsBySpuId")
    public BaseResult<List<Goods>> listGoodsBySpuId(Integer spuId){
        if(spuId == null){
            return BaseResult.buildSuccess();
        }
        return BaseResult.buildSuccess(skuManager.listGoodsBySpuId(spuId));
    }

    @PostMapping("/upload")
    public BaseResult upload(@RequestBody MultipartFile file){
        String fileName = file.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            BaseResult.buildFail(ResponseStatus.FILE_TYPE_NOT_SUPPORT);
        }
        spuManager.handleUpload(file);
       return BaseResult.buildSuccess();
    }

}
