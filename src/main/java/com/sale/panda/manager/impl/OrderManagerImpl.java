/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.manager.impl;

import com.sale.panda.dao.OrderDetailMapper;
import com.sale.panda.dao.OrderMapper;
import com.sale.panda.dao.entity.Order;
import com.sale.panda.dao.entity.OrderDetail;
import com.sale.panda.manager.OrderManager;

import java.util.List;

import javax.annotation.Resource;

/**
 * 订单相关
 *
 * @author chenlu
 * @version V1.0
 * @since 2020-01-05 10:34
 */
public class OrderManagerImpl implements OrderManager {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderDetailMapper detailMapper;

    @Override
    public Integer insert(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public Integer insert(List<OrderDetail> detail) {
        return detailMapper.insert(detail);
    }
}
