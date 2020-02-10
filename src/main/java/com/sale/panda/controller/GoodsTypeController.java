package com.sale.panda.controller;

import com.sale.panda.controller.model.BaseResult;
import com.sale.panda.dao.entity.GoodsType;
import com.sale.panda.manager.GoodsTypeManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: chenlu
 * @description:
 * @date:2020/2/3 21:20
 */
@RestController
public class GoodsTypeController {

    @Resource
    private GoodsTypeManager goodsTypeManager;

    @GetMapping("/listAllType")
    public BaseResult<List<GoodsType>> listAllType(){
        return BaseResult.buildSuccess(goodsTypeManager.listAllGoodsType());
    }
}
