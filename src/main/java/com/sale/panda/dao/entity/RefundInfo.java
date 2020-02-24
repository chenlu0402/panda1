package com.sale.panda.dao.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author: chenlu
 * @description:
 * @date:2020/2/23 11:17
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefundInfo {

    String orderId;

    Integer skuId;

    Integer count;

    String remark;
}
