package com.sale.panda.controller;

import com.alibaba.fastjson.JSON;
import com.sale.panda.controller.model.BaseResult;
import com.sale.panda.controller.model.RefundModel;
import com.sale.panda.controller.model.SaleGoodsModel;
import com.sale.panda.controller.model.SaleOrderModel;
import com.sale.panda.dao.entity.Order;
import com.sale.panda.dao.entity.OrderDetail;
import com.sale.panda.dao.entity.OrderPageQuery;
import com.sale.panda.manager.OrderManager;
import com.sale.panda.manager.entity.PageQueryResult;
import org.springframework.beans.BeanUtils;
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
    BaseResult<List<OrderDetail>> listDetailByOrderId(@RequestParam(required = true) String orderId){
        return BaseResult.buildSuccess(orderManager.listDetailByOrderId(orderId));
    }

    @PostMapping("/listOrder")
    BaseResult<PageQueryResult<List<Order>>> listOrderByParam(@RequestBody OrderPageQuery query){
        return BaseResult.buildSuccess(orderManager.pageQueryOrder(query));
    }

    @PostMapping("/refund")
    BaseResult refund(@RequestBody RefundModel refundModel){

        return BaseResult.buildSuccess();
    }
}
