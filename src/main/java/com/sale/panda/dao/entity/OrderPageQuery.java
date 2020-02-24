package com.sale.panda.dao.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author: chenlu
 * @description:
 * @date:2020/2/23 10:43
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderPageQuery extends PageQuery{

    String createdStartTime;

    String createdEndTime;

    String remark;

    String orderId;
}
