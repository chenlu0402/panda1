package com.sale.panda.dao.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author: chenlu
 * @description:
 * @date:2020/2/1 11:08
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GoodsType {

    Integer id;
    String typeName;
    String createdTime;
    String updatedTime;
}
