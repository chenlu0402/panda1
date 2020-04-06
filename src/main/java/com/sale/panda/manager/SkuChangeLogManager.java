package com.sale.panda.manager;

import com.sale.panda.dao.entity.GoodsPageQuery;
import com.sale.panda.dao.entity.SkuChangeDetail;
import com.sale.panda.manager.entity.PageQueryResult;

import java.util.List;

/**
 * @author: chenlu
 * @description:
 * @date:2020/3/22 15:24
 */
public interface SkuChangeLogManager {

    PageQueryResult<List<SkuChangeDetail>> pageQueryChangeDetail(GoodsPageQuery query);
}
