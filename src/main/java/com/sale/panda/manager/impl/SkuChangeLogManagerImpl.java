package com.sale.panda.manager.impl;

import com.sale.panda.dao.SkuChangeLogMapper;
import com.sale.panda.dao.entity.GoodsPageQuery;
import com.sale.panda.dao.entity.SkuChangeDetail;
import com.sale.panda.manager.SkuChangeLogManager;
import com.sale.panda.manager.constants.SkuChangeActionEnum;
import com.sale.panda.manager.entity.PageQueryResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: chenlu
 * @description:
 * @date:2020/3/22 15:30
 */
@Component
public class SkuChangeLogManagerImpl implements SkuChangeLogManager {
    @Resource
    private SkuChangeLogMapper changeLogMapper;

    @Override
    public PageQueryResult<List<SkuChangeDetail>> pageQueryChangeDetail(GoodsPageQuery query) {
        List<SkuChangeDetail> list = changeLogMapper.pageQueryChangeLog(query);
        list.stream().forEach(detail->{detail.setActionDesc(SkuChangeActionEnum.getDesc(detail.getAction()));});
        Integer count = changeLogMapper.count(query);
        return new PageQueryResult<>(count,list);
    }
}
