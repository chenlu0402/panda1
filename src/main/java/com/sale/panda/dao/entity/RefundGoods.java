package com.sale.panda.dao.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

/**
 * @author: chenlu
 * @description:
 * @date:2020/1/28 11:44
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefundGoods {
    String orderId;
    String spuId;
    String spuName;
    String size;
    String color;
    /**
     * 退货数量
     */
    Integer count;
    /**
     * 退款金额
     */
    BigDecimal amount;
    String remark;
    String createdTime;
}
