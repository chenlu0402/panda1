package com.sale.panda.manager;

import com.sale.panda.dao.entity.GoodsType;
import com.sale.panda.manager.entity.PageQueryResult;

import java.util.List;

/**
 * @author: chenlu
 * @description:
 * @date:2020/2/1 11:17
 */
public interface GoodsTypeManager {

    Integer insert(GoodsType goods);

    Integer update(GoodsType goodsType);

    PageQueryResult<List<GoodsType>> pageQuery(String keyword);

    List<GoodsType> listAllGoodsType();
}
