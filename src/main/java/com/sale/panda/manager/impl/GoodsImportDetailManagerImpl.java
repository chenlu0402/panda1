package com.sale.panda.manager.impl;

import com.sale.panda.dao.GoodsImportDetailMapper;
import com.sale.panda.dao.SkuMapper;
import com.sale.panda.dao.SpuMapper;
import com.sale.panda.dao.entity.Goods;
import com.sale.panda.dao.entity.GoodsPageQuery;
import com.sale.panda.dao.entity.Sku;
import com.sale.panda.dao.entity.Spu;
import com.sale.panda.manager.GoodsImportDetailManager;
import com.sale.panda.manager.entity.PageQueryResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private SpuMapper spuMapper;

    @Override
    public Integer insert(Goods good) {
        return detailMapper.insert(good);
    }

    @Override
    public Integer batchInsert(List<Goods> goods) {
        List<Sku> skusForInsert = new ArrayList<>();
        List<Spu> spusForInsert = new ArrayList<>();

        List<Sku> skusForUpdate = new ArrayList<>();
        List<Spu> spusForUpdate = new ArrayList<>();

        boolean updateFlag = false;
        boolean insertFlag = false;
        for (Goods good:goods) {
            Sku sku = new Sku();
            BeanUtils.copyProperties(good,sku);
            Spu spu = new Spu();
            BeanUtils.copyProperties(good,spu);
            if(good.getSkuId() == null){
                insertFlag = true;
                skuMapper.insert(sku);
                good.setSkuId(sku.getSkuId());
                spusForInsert.add(spu);
            }else{
                updateFlag = true;
                skusForUpdate.add(sku);
                spusForUpdate.add(spu);
            }
        }

        if(insertFlag){
            spuMapper.batchInsert(spusForInsert);
        }

        if(updateFlag){
            skuMapper.batchUpdate(skusForUpdate);
            spuMapper.batchUpdate(spusForUpdate);
        }
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
