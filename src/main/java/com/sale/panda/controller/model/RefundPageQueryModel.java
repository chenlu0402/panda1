package com.sale.panda.controller.model;

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
public class RefundPageQueryModel{

    String createdStartTime;

    String createdEndTime;

    String remark;

    String orderId;

    Integer limit = 10;
    Integer page = 1;

}
