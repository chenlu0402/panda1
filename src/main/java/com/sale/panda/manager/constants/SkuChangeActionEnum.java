package com.sale.panda.manager.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: chenlu
 * @description:
 * @date:2020/3/22 14:54
 */
public enum SkuChangeActionEnum {

    ADD(1,"进货"),
    SALE(2,"销售"),
    REFUND(3,"退货"),
    DELETE(4,"删除");

    /**
     * 类型
     */
    private Integer type;
    /**
     * 描述
     */
    private String desc;
    static Map<Integer,String> typeDescMap = new HashMap<>();

    SkuChangeActionEnum(Integer type, String desc){
        this.type = type;
        this.desc = desc;
    }

    public static String getDesc(Integer type){
        return typeDescMap.get(type);
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    static{
        for(SkuChangeActionEnum changeActionEnum:SkuChangeActionEnum.values()){
            typeDescMap.put(changeActionEnum.getType(),changeActionEnum.getDesc());
        }
    }

}
