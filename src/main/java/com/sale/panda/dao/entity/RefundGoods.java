package com.sale.panda.dao.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

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
    String feature1;
    String feature2;
    String feature3;
    Integer count;
    String remark;
    String createdTime;
}
