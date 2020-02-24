package com.sale.panda.controller.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

/**
 * @author: chenlu
 * @description:
 * @date:2020/2/17 20:29
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleGoodsModel {

    String spuId;
    Integer skuId;
    Integer count;
    BigDecimal amount;
    BigDecimal salePrice;
    Integer discountType;
    BigDecimal discountAmount;

}
