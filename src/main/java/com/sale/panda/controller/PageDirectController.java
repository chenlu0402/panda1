package com.sale.panda.controller;

import com.sale.panda.dao.entity.GoodsType;
import com.sale.panda.manager.GoodsTypeManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: chenlu
 * @description:
 * @date:2019/10/15 19:02
 */
@Controller
public class PageDirectController {

    @Resource
    private GoodsTypeManager goodsTypeManager;

    @RequestMapping("/loginPage")
    public String tologin(){
        return "/login";
    }

    @RequestMapping("/homePage")
    public String home(){
        return "/home";
    }

    @RequestMapping("/logout")
    public String logout(){
        //Subject subject = SecurityUtils.getSubject();
        //subject.logout(); // shiro底层删除session的会话信息
        return "/login";
    }

    @RequestMapping("/user/login")
    @ResponseBody
    public String handleLogin(String username,String password){
        return "/home";
    }


    @RequestMapping("/goods_list_page")
    public String goods(ModelMap model){
        List<GoodsType> goodsTypeList = goodsTypeManager.listAllGoodsType();
        model.put("goodsTypeList",goodsTypeList);
        return "/goods_list";
    }

    @RequestMapping("/order_list_page")
    public String order(){
        return "/order_list";
    }

    @RequestMapping("/order_detail_page")
    public String detail(String orderId,ModelMap modelMap){
        modelMap.put("orderId",orderId);
        return "/order_detail";
    }

    @RequestMapping("/goods_add_log_page")
    public String goodsDetail(ModelMap model){
        List<GoodsType> goodsTypeList = goodsTypeManager.listAllGoodsType();
        model.put("goodsTypeList",goodsTypeList);
        return "goods_add_log";
    }

    @RequestMapping("/sale_page")
    public String sale(){
        return "/sale";
    }

    @RequestMapping("/desc")
    public String desc(){
        return "/desc";
    }

    @RequestMapping("/goods_add_page")
    public String addGoods(){
        return "/goods_add";
    }

    @RequestMapping("/refund_list_page")
    public String refundList(){
        return "/refund_list";
    }

    @RequestMapping("/sku_change_page")
    public String changeList(){
        return "/sku_change_list";
    }

}
