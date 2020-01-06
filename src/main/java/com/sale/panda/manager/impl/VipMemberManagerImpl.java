/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.manager.impl;

import com.sale.panda.dao.VipMemberMapper;
import com.sale.panda.dao.entity.VipMember;
import com.sale.panda.manager.VipMemberManager;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author chenlu
 * @version V1.0
 * @since 2020-01-05 10:37
 */
public class VipMemberManagerImpl implements VipMemberManager {
    @Resource
    private VipMemberMapper memberMapper;

    @Override
    public Integer insert(VipMember member) {
        return memberMapper.insert(member);
    }

    @Override
    public VipMember getMember(String mobile) {
        return memberMapper.getMember(mobile);
    }
}
