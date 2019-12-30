package com.sale.panda.controller;

import com.sale.panda.dao.entity.LoginUser;
import com.sale.panda.manager.UserManager;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
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
    private UserManager userManager;

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

    @RequestMapping("/user")
    @ResponseBody
    public List<LoginUser> user(){
        return userManager.getUserList();
    }

    @RequestMapping("/update")
    @ResponseBody
    public void update(){
        userManager.update(new Date());
    }
}
