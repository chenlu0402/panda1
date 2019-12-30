/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.manager.impl;

import com.sale.panda.dao.UserMapper;
import com.sale.panda.dao.entity.LoginUser;
import com.sale.panda.manager.UserManager;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-10-27 09:51
 */
@Component
public class UserManagerImpl implements UserManager {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<LoginUser> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public Integer update(Date date) {
        return userMapper.update(date);
    }
}
