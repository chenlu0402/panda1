/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.manager.impl;

import com.sale.panda.controller.model.RefundModel;
import com.sale.panda.dao.*;
import com.sale.panda.dao.entity.*;
import com.sale.panda.manager.OrderManager;
import com.sale.panda.manager.constants.DiscountTypeEnum;
import com.sale.panda.manager.constants.SkuChangeActionEnum;
import com.sale.panda.manager.entity.PageQueryResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private SkuChangeLogMapper changeLogMapper;

    @Override
    public Integer insert(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public Integer insert(List<OrderDetail> details) {
        List<SubCountInfo> subCountInfos = new ArrayList<>();
        List<SkuChangeLog> logs = new ArrayList<>();
        //减去销售出去的库存
        details.stream().forEach(detail ->{
            SubCountInfo subCountInfo = new SubCountInfo();
            BeanUtils.copyProperties(detail,subCountInfo);
            subCountInfos.add(subCountInfo);

            SkuChangeLog log = new SkuChangeLog();
            log.setAction(SkuChangeActionEnum.SALE.getType());
            log.setSkuId(detail.getSkuId());
            log.setCount(0 - detail.getCount());
            logs.add(log);
        });
        skuMapper.subCount(subCountInfos);
        changeLogMapper.batchInsert(logs);
        return detailMapper.batchInsert(details);
    }

    @Override
    public List<OrderDetailModel> listDetailByOrderId(String orderId) {
        OrderDetailQuery query = new OrderDetailQuery();
        query.setOrderId(orderId);
        List<OrderDetailModel> list = detailMapper.listDetailByParam(query);
        list.stream().forEach(detail ->{
            detail.setDiscountTypeDesc(DiscountTypeEnum.getDesc(detail.getDiscountType()));
        });
        return list;
    }

    @Override
    public PageQueryResult<List<OrderModel>> pageQueryOrder(OrderPageQuery query) {
        Integer count = orderMapper.count(query);
        List<OrderModel> list = orderMapper.listOrderByParam(query);
        return new PageQueryResult<>(count, list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleRefund(RefundModel refundModel) {
        List<RefundInfo> refunds = new ArrayList<>();
        List<Sku> skus = new ArrayList<>();
        if (refundModel.getSkuId() == null) {
            OrderDetailQuery query = new OrderDetailQuery();
            query.setOrderId(refundModel.getOrderId());
            List<OrderDetailModel> details = detailMapper.listDetailByParam(query);
            details.stream().forEach(detail -> {
                refunds.add(getRefundInfo(refundModel.getOrderId(), detail.getSkuId(), detail.getCount(), detail.getAmount(),refundModel.getRemark()));
                skus.add(getSku(detail.getSkuId(), detail.getCount()));
            });
        } else {
            refunds.add(getRefundInfo(refundModel.getOrderId(), refundModel.getSkuId(), refundModel.getCount(), refundModel.getAmount(), refundModel.getRemark()));
            skus.add(getSku(refundModel.getSkuId(), refundModel.getCount()));
        }
        refundMapper.insert(refunds);
        skuMapper.batchUpdate(skus);

        List<SkuChangeLog> logs = skus.stream().map(sku->{
            SkuChangeLog log = new SkuChangeLog();
            log.setAction(SkuChangeActionEnum.REFUND.getType());
            log.setCount(sku.getCount());
            log.setSkuId(sku.getSkuId());
            return log;
        }).collect(Collectors.toList());
        changeLogMapper.batchInsert(logs);
    }

    @Override
    public PageQueryResult<List<RefundGoods>> pageQueryRefundInfo(RefundPageQuery pageQuery) {
        return new PageQueryResult<>(refundMapper.count(pageQuery),refundMapper.listRefundByParam(pageQuery));
    }

    private RefundInfo getRefundInfo(String orderId, Integer skuId, Integer count, BigDecimal amount, String remark) {
        RefundInfo refundInfo = new RefundInfo();
        refundInfo.setOrderId(orderId);
        refundInfo.setSkuId(skuId);
        refundInfo.setCount(count);
        refundInfo.setAmount(amount);
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
