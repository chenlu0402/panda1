package com.sale.panda.controller;

import com.sale.panda.dao.entity.GoodsType;
import com.sale.panda.manager.GoodsTypeManager;
import com.sale.panda.manager.SpuManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: chenlu
 * @description:
 * @date:2019/10/15 19:02
 */
@Controller
public class PandaSaleController {

    @Resource
    private SpuManager spuManager;

    @Resource
    private GoodsTypeManager goodsTypeManager;

    @RequestMapping("/")
    public String index(){
        return "/test";
    }

    @RequestMapping("/index")
    public String hello(){
        return "/index";
    }

    @RequestMapping("/remain")
    public String remain(){
        return "/remain";
    }

    @RequestMapping("/goods_list_page")
    public String goods(ModelMap model){
        List<GoodsType> goodsTypeList = goodsTypeManager.listAllGoodsType();
        model.put("goodsTypeList",goodsTypeList);
        return "/goods_list";
    }

    @RequestMapping("/goods_import_detail_page")
    public String goodsDetail(ModelMap model){
        List<GoodsType> goodsTypeList = goodsTypeManager.listAllGoodsType();
        model.put("goodsTypeList",goodsTypeList);
        return "/goods_import_detail";
    }

    @RequestMapping("/sale_page")
    public String sale(){
        return "/sale";
    }

    @RequestMapping("/goods_add_page")
    public String addGoods(){
        return "/goods_add";
    }

}
