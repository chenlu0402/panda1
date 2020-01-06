/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sale.panda.manager;

import com.sale.panda.dao.entity.LoginUser;

/**
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-10-27 09:50
 */
public interface UserManager {
    Integer insert(LoginUser user);

    LoginUser getUserByName(String name);
}
