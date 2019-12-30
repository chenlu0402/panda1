/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.dao;

import com.sale.panda.dao.entity.VipMember;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 会员
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 15:56
 */
@Mapper
public interface VipMemberMapper {

    Integer insert(VipMember member);

    VipMember getMember(@Param("mobile") String mobile);
}
