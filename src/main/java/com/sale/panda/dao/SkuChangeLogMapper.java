package com.sale.panda.dao;

import com.sale.panda.dao.entity.GoodsPageQuery;
import com.sale.panda.dao.entity.SkuChangeDetail;
import com.sale.panda.dao.entity.SkuChangeLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: chenlu
 * @description:
 * @date:2020/3/22 14:36
 */
@Mapper
public interface SkuChangeLogMapper {

    Integer insert(SkuChangeLog changeLog);

    Integer batchInsert(List<SkuChangeLog> list);

    List<SkuChangeDetail> pageQueryChangeLog(GoodsPageQuery query);

    Integer count(GoodsPageQuery query);
}
