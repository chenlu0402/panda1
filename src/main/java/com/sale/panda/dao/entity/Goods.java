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
public class Goods {
    String spuId;
    Integer skuId;
    String spuName;
    BigDecimal inPrice;
    BigDecimal salePrice;
    Integer type;
    String typeName;
    String size;
    String color;
    Integer count;
    String feature1;
    String feature2;
    String feature3;
    String createdTime;
    String updatedTime;
    Integer isDeleted;
}
