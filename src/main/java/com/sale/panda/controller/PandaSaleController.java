package com.sale.panda.controller;

import com.sale.panda.controller.model.BaseResult;
import com.sale.panda.controller.model.SpuPageQueryModel;
import com.sale.panda.dao.entity.Spu;
import com.sale.panda.dao.entity.SpuPageQuery;
import com.sale.panda.manager.SpuManager;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @PostMapping("/pageQuerySpu1")
    @ResponseBody
    public BaseResult<List<Spu>> pageQuerySpu(@RequestBody SpuPageQueryModel spuModel){
        SpuPageQuery pageQuery = new SpuPageQuery();
        BeanUtils.copyProperties(spuModel,pageQuery);
        return BaseResult.buildSuccess(spuManager.pageQuery(pageQuery));
    }
}
