/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.manager.impl;

import com.sale.panda.controller.model.RefundModel;
import com.sale.panda.dao.OrderDetailMapper;
import com.sale.panda.dao.OrderMapper;
import com.sale.panda.dao.RefundMapper;
import com.sale.panda.dao.SkuMapper;
import com.sale.panda.dao.entity.*;
import com.sale.panda.manager.OrderManager;
import com.sale.panda.manager.entity.PageQueryResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单相关
 *
 * @author chenlu
 * @version V1.0
 * @since 2020-01-05 10:34
 */
@Component
public class OrderManagerImpl implements OrderManager {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderDetailMapper detailMapper;

    @Resource
    private RefundMapper refundMapper;

    @Resource
    private SkuMapper skuMapper;

    @Override
    public Integer insert(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public Integer insert(List<OrderDetail> details) {
        return detailMapper.batchInsert(details);
    }

    @Override
    public List<OrderDetail> listDetailByOrderId(String orderId) {
        OrderDetailQuery query = new OrderDetailQuery();
        query.setOrderId(orderId);
        return detailMapper.listDetailByParam(query);
    }

    @Override
    public PageQueryResult<List<Order>> pageQueryOrder(OrderPageQuery query) {
        Integer count = orderMapper.count(query);
        List<Order> list = orderMapper.listOrderByParam(query);
        return new PageQueryResult<>(count, list);
    }

    @Override
    public void handleRefund(RefundModel refundModel) {
        List<RefundInfo> refunds = new ArrayList<>();
        List<Sku> skus = new ArrayList<>();
        if (refundModel.getSkuId() == null) {
            OrderDetailQuery query = new OrderDetailQuery();
            query.setOrderId(refundModel.getOrderId());
            List<OrderDetail> details = detailMapper.listDetailByParam(query);
            details.stream().forEach(detail -> {
                refunds.add(getRefundInfo(refundModel.getOrderId(), detail.getSkuId(), detail.getCount(), refundModel.getRemark()));
                skus.add(getSku(detail.getSkuId(), detail.getCount()));
            });
        } else {
            refunds.add(getRefundInfo(refundModel.getOrderId(), refundModel.getSkuId(), refundModel.getCount(), refundModel.getRemark()));
            skus.add(getSku(refundModel.getSkuId(), refundModel.getCount()));
        }
        refundMapper.insert(refunds);
        skuMapper.batchUpdate(skus);
    }

    private RefundInfo getRefundInfo(String orderId, Integer skuId, Integer count, String remark) {
        RefundInfo refundInfo = new RefundInfo();
        refundInfo.setOrderId(orderId);
        refundInfo.setSkuId(skuId);
        refundInfo.setCount(count);
        refundInfo.setRemark(remark);
        return refundInfo;
    }

    private Sku getSku(Integer skuId, Integer count) {
        Sku sku = new Sku();
        sku.setSkuId(skuId);
        sku.setCount(count);
        return sku;
    }
}
