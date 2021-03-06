package com.sale.panda.controller.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

/**
 * @author: chenlu
 * @description:
 * @date:2020/2/23 12:00
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefundModel {

    String orderId;

    Integer skuId;

    Integer count;

    BigDecimal amount;

    String remark;
}
