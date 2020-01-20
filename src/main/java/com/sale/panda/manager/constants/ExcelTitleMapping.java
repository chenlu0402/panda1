package com.sale.panda.manager.constants;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: chenlu
 * @description:
 * @date:2020/1/20 15:02
 */
public class ExcelTitleMapping {

    /**
     * excel表头名称和属性名的对应关系，主要在读取excel的时候得到：name=zw这样的值。
     */
    private static Map<String, String> EXCELMAPPING = new LinkedHashMap<>();

    public static Map<String, String> getEXCELMAPPING() {
        return EXCELMAPPING;
    }

    static {
        EXCELMAPPING.put("商品名称", "spuName");
        EXCELMAPPING.put("进货价", "inPrice");
        EXCELMAPPING.put("售出价", "salePrice");
        EXCELMAPPING.put("数量", "totalCount");
    }

}
