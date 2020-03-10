package com.sale.panda.controller;

import com.alibaba.fastjson.JSON;
import com.sale.panda.controller.model.*;
import com.sale.panda.dao.entity.*;
import com.sale.panda.manager.OrderManager;
import com.sale.panda.manager.entity.PageQueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: chenlu
 * @description:
 * @date:2020/2/23 10:55
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderManager orderManager;

    /**
     * 订单结算
     *
     * @param order
     * @return
     */
    @PostMapping("/settleOrder")
    @Transactional(rollbackFor = Exception.class)
    public BaseResult sale(@RequestBody SaleOrderModel order) {
        List<SaleGoodsModel> goodsDetails = JSON.parseArray(order.getSaleGoodsList(), SaleGoodsModel.class);
        String orderId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        Order saleOrder = new Order();
        BeanUtils.copyProperties(order, saleOrder);
        saleOrder.setOrderId(orderId);
        saleOrder.setTotalCount(0);
        saleOrder.setSmallChanges(order.getOrderAmount().subtract(order.getTotalAmount()));
        List<OrderDetail> details = goodsDetails.stream().map(detail -> {
            saleOrder.setTotalCount(saleOrder.getTotalCount() + detail.getCount());
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(detail, orderDetail);
            orderDetail.setOrderId(orderId);
            orderDetail.setOriginAmount(detail.getSalePrice());
            return orderDetail;
        }).collect(Collectors.toList());
        orderManager.insert(saleOrder);
        orderManager.insert(details);
        return BaseResult.buildSuccess();
    }

    @GetMapping("/listDetail")
    BaseResult<List<OrderDetailModel>> listDetailByOrderId(@RequestParam(required = true) String orderId){
        return BaseResult.buildSuccess(orderManager.listDetailByOrderId(orderId));
    }

    @PostMapping("/listOrder")
    BaseResult<PageQueryResult<List<OrderModel>>> listOrderByParam(@RequestBody OrderPageQuery query){
        if(StringUtils.isNotBlank(query.getDatetimeRange())){
            String[] datetimeRange = query.getDatetimeRange().split("~");
            query.setCreatedStartTime(datetimeRange[0]);
            query.setCreatedEndTime(datetimeRange[1]);
        }
        PageQueryResult<List<OrderModel>> queryResult = orderManager.pageQueryOrder(query);
        return BaseResult.buildSuccess(queryResult.getData(),queryResult.getTotal());
    }

    @PostMapping("/refund")
    BaseResult refund(@RequestBody RefundModel refundModel){
        orderManager.handleRefund(refundModel);
        return BaseResult.buildSuccess();
    }

    @PostMapping("listRefundInfo")
    BaseResult<List<RefundGoods>> listRefundInfo(RefundPageQueryModel pageQueryModel){
        RefundPageQuery query = new RefundPageQuery();
        BeanUtils.copyProperties(pageQueryModel,query);
        PageQueryResult<List<RefundGoods>> result = orderManager.pageQueryRefundInfo(query);
        return BaseResult.buildSuccess(result.getData(),result.getTotal());
    }
}
