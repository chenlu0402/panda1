/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.dao;

import com.sale.panda.dao.entity.RefundGoods;
import com.sale.panda.dao.entity.RefundInfo;
import com.sale.panda.dao.entity.RefundPageQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 销售明细
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 10:33
 */
@Mapper
public interface RefundMapper {

    Integer insert(List<RefundInfo> list);

    List<RefundGoods> listRefundByParam(RefundPageQuery query);

    Integer count(RefundPageQuery query);
}
