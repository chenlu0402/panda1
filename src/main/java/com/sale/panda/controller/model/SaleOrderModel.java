package com.sale.panda.controller.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

/**
 * @author: chenlu
 * @description:
 * @date:2020/2/17 20:20
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleOrderModel {

    /**
     * 实收金额
     */
    BigDecimal totalAmount;
    /**
     * 应收金额
     */
    BigDecimal orderAmount;

    String remark;

    /**
     * 售出的详细商品信息
     */
    String saleGoodsList;
}
