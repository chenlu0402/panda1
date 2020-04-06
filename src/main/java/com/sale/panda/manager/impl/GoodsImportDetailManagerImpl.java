package com.sale.panda.manager.impl;

import com.sale.panda.dao.GoodsImportDetailMapper;
import com.sale.panda.dao.SkuChangeLogMapper;
import com.sale.panda.dao.SkuMapper;
import com.sale.panda.dao.SpuMapper;
import com.sale.panda.dao.entity.*;
import com.sale.panda.manager.GoodsImportDetailManager;
import com.sale.panda.manager.constants.SkuChangeActionEnum;
import com.sale.panda.manager.entity.PageQueryResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private SkuChangeLogMapper changeLogMapper;

    @Override
    public Integer insert(Goods good) {
        return detailMapper.insert(good);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer batchInsert(List<Goods> goods) {
        List<SkuChangeLog> logs = new ArrayList<>();
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

            //记录库存变化日志
            SkuChangeLog changeLog = new SkuChangeLog();
            changeLog.setAction(SkuChangeActionEnum.ADD.getType());
            changeLog.setSkuId(good.getSkuId());
            changeLog.setCount(good.getCount());
            if(good.getSkuId() == null){
                insertFlag = true;
                skuMapper.insert(sku);
                good.setSkuId(sku.getSkuId());
                spusForInsert.add(spu);

                changeLog.setSkuId(sku.getSkuId());
            }else{
                updateFlag = true;
                skusForUpdate.add(sku);
                spusForUpdate.add(spu);
            }
            logs.add(changeLog);
        }
        changeLogMapper.batchInsert(logs);

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
    @Transactional(rollbackFor = Exception.class)
    public Integer delete(Goods good) {
        SkuChangeLog changeLog = new SkuChangeLog();
        changeLog.setSkuId(good.getSkuId());
        changeLog.setCount(0 - good.getCount());
        changeLog.setAction(SkuChangeActionEnum.DELETE.getType());
        changeLogMapper.insert(changeLog);

        List<SubCountInfo> subCountInfos = new ArrayList<>();
        SubCountInfo subCountInfo = new SubCountInfo();
        subCountInfo.setCount(good.getCount());
        subCountInfo.setSkuId(good.getSkuId());
        subCountInfos.add(subCountInfo);
        skuMapper.subCount(subCountInfos);
        return detailMapper.delete(good);
    }

    @Override
    public PageQueryResult<List<Goods>> pageQuery(GoodsPageQuery pageQuery) {
        return new PageQueryResult<>(detailMapper.count(pageQuery), detailMapper.pageQuery(pageQuery));
    }
}
