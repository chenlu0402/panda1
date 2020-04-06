package com.sale.panda.dao.entity;

import lombok.Data;

/**
 * @author: chenlu
 * @description:
 * @date:2020/3/22 11:24
 */
@Data
public class SkuChangeLog {

    Integer skuId;

    Integer count;

    /**
     * 1-进货 2-销售 3-退货 4-删除进货
     */
    Integer action;
}
