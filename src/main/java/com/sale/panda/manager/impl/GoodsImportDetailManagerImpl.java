package com.sale.panda.manager.impl;

import com.sale.panda.dao.GoodsImportDetailMapper;
import com.sale.panda.dao.entity.Goods;
import com.sale.panda.dao.entity.GoodsPageQuery;
import com.sale.panda.manager.GoodsImportDetailManager;
import com.sale.panda.manager.entity.PageQueryResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: chenlu
 * @description:
 * @date:2020/1/31 20:36
 */
@Component
public class GoodsImportDetailManagerImpl implements GoodsImportDetailManager {

    @Resource
    private GoodsImportDetailMapper detailMapper;

    @Override
    public Integer insert(Goods good) {
        return detailMapper.insert(good);
    }

    @Override
    public Integer batchInsert(List<Goods> goods) {
        return detailMapper.batchInsert(goods);
    }

    @Override
    public Integer update(Goods good) {
        return detailMapper.update(good);
    }

    @Override
    public PageQueryResult<List<Goods>> pageQuery(GoodsPageQuery pageQuery) {
        return new PageQueryResult<>(detailMapper.count(pageQuery), detailMapper.pageQuery(pageQuery));
    }
}
