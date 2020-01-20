package com.sale.panda.controller;

import com.sale.panda.manager.SpuManager;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: chenlu
 * @description:
 * @date:2019/10/15 19:02
 */
@Controller
public class PandaSaleController {

    @Resource
    private SpuManager spuManager;

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

    @RequestMapping("/goods")
    public String goods(){
        return "/goods";
    }

    @RequestMapping("/goods_detail")
    public String goodsDetail(ModelMap model,@RequestParam Integer spuId){
        model.put("spuId",spuId);
        return "/goods_detail";
    }

    @RequestMapping("/testHome")
    public String testHome(){
        return "/test_home";
    }

}
