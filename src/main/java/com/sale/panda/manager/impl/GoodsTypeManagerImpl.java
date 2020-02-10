package com.sale.panda.manager.impl;

import com.sale.panda.dao.GoodsTypeMapper;
import com.sale.panda.dao.entity.Goods;
import com.sale.panda.dao.entity.GoodsType;
import com.sale.panda.manager.GoodsTypeManager;
import com.sale.panda.manager.entity.PageQueryResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: chenlu
 * @description:
 * @date:2020/2/1 16:15
 */
@Component
public class GoodsTypeManagerImpl implements GoodsTypeManager {

    @Resource
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public Integer insert(GoodsType goods) {
        return goodsTypeMapper.insert(goods);
    }

    @Override
    public Integer update(GoodsType goodsType) {
        return goodsTypeMapper.update(goodsType);
    }

    @Override
    public PageQueryResult<List<GoodsType>> pageQuery(String keyword) {
        return new PageQueryResult<>(goodsTypeMapper.count(keyword), goodsTypeMapper
                .pageQuery(keyword));
    }

    @Override
    public List<GoodsType> listAllGoodsType() {
        return goodsTypeMapper.listAllGoodsType();
    }
}
