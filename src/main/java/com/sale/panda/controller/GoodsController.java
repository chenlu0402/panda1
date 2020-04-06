/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.controller;

import com.sale.panda.controller.model.BaseResult;
import com.sale.panda.controller.model.DeletedRequestModel;
import com.sale.panda.controller.model.GoodsPageQueryModel;
import com.sale.panda.dao.entity.*;
import com.sale.panda.manager.*;
import com.sale.panda.manager.constants.ResponseStatus;
import com.sale.panda.manager.entity.PageQueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private GoodsTypeManager goodsTypeManager;

    @Resource
    private SkuChangeLogManager changeLogManager;

    @GetMapping("/listAllType")
    public BaseResult<List<GoodsType>> listAllType(){
        return BaseResult.buildSuccess(goodsTypeManager.listAllGoodsType());
    }

    @PostMapping("/deletedImportedGoods")
    @Transactional(rollbackFor = Exception.class)
    public BaseResult deletedImportedGoods(@RequestBody DeletedRequestModel requestModel) {
        Goods good = new Goods();
        good.setSkuId(requestModel.getSkuId());
        good.setCreatedTime(requestModel.getCreatedTime());
        good.setCount(requestModel.getCount());
        detailManager.delete(good);

        List<SubCountInfo> list = new ArrayList<>();
        SubCountInfo subCountInfo = new SubCountInfo();
        subCountInfo.setSkuId(requestModel.getSkuId());
        subCountInfo.setCount(requestModel.getCount());
        list.add(subCountInfo);
        skuManager.subCount(list);
        return BaseResult.buildSuccess();
    }

    @PostMapping("/batchAddImportGoods")
    public BaseResult batchAddGoods(@RequestBody List<Goods> allData) {
        detailManager.batchInsert(allData);
        return BaseResult.buildSuccess();
    }

    @PostMapping("/pageQueryGoods")
    public BaseResult<List<Goods>> pageQueryGoods(@RequestBody GoodsPageQueryModel skuModel) {
        GoodsPageQuery pageQuery = new GoodsPageQuery();
        BeanUtils.copyProperties(skuModel, pageQuery);
        PageQueryResult<List<Goods>> result = skuManager.pageQuery(pageQuery);
        return BaseResult.buildSuccess(result.getData(), result.getTotal());
    }

    @PostMapping("/pageQueryImportGoods")
    public BaseResult<List<Goods>> pageQueryImportGoods(@RequestBody GoodsPageQueryModel goodsModel) {
        GoodsPageQuery pageQuery = new GoodsPageQuery();
        BeanUtils.copyProperties(goodsModel, pageQuery);
        if (StringUtils.isNotBlank(goodsModel.getDatetimeRange())) {
            String[] datetimeRange = goodsModel.getDatetimeRange().split("~");
            pageQuery.setCreatedTimeStart(datetimeRange[0]);
            pageQuery.setCreatedTimeEnd(datetimeRange[1]);
        }
        PageQueryResult<List<Goods>> result = detailManager.pageQuery(pageQuery);
        return BaseResult.buildSuccess(result.getData(), result.getTotal());
    }

    @GetMapping("/listGoodsBySpuId")
    public BaseResult<List<Goods>> listGoodsBySpuId(@RequestParam(required = true) Integer spuId) {
        return BaseResult.buildSuccess(skuManager.listGoodsBySpuId(spuId));
    }

    @GetMapping("/listSkuForSale")
    public BaseResult<List<Goods>> listSkuForSale(@RequestParam(required = true) Integer spuId) {
        return BaseResult.buildSuccess(skuManager.listSkuForSale(spuId));
    }

    @PostMapping("/upload")
    public BaseResult upload(@RequestBody MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            BaseResult.buildFail(ResponseStatus.FILE_TYPE_NOT_SUPPORT);
        }
        spuManager.handleUpload(file);
        return BaseResult.buildSuccess();
    }

    @GetMapping("/getSkuById")
    public BaseResult<Sku> getSkuById(@RequestParam Integer skuId){
        Sku sku = skuManager.getSku(skuId);
        return BaseResult.buildSuccess(sku);
    }

    @PostMapping("/pageQueryChangeDetail")
    public BaseResult<List<SkuChangeDetail>> pageQueryChangeDetail(@RequestBody GoodsPageQueryModel goodsModel){
        GoodsPageQuery pageQuery = new GoodsPageQuery();
        pageQuery.setSpuName(goodsModel.getSpuName());
        if (StringUtils.isNotBlank(goodsModel.getDatetimeRange())) {
            String[] datetimeRange = goodsModel.getDatetimeRange().split("~");
            pageQuery.setCreatedTimeStart(datetimeRange[0]);
            pageQuery.setCreatedTimeEnd(datetimeRange[1]);
        }
        PageQueryResult<List<SkuChangeDetail>> result = changeLogManager.pageQueryChangeDetail(pageQuery);
        return BaseResult.buildSuccess(result.getData(), result.getTotal());
    }

}
