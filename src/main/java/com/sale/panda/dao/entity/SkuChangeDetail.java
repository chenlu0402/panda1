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
public class SkuChangeDetail {
    String spuId;
    Integer skuId;
    String spuName;
    String size;
    String color;
    Integer count;
    String createdTime;
    Integer action;
    String actionDesc;
}
