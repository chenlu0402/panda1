package com.sale.panda.manager.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: chenlu
 * @description:
 * @date:2020/2/29 22:04
 */
public enum DiscountTypeEnum {

    NONE(0,"无优惠"),
    DISCOUNT(1,"折扣"),
    SUB_AMOUNT(2,"立减");

    /**
     * 类型
     */
    private Integer type;
    /**
     * 描述
     */
    private String desc;
    static Map<Integer,String> typeDescMap = new HashMap<>();

    DiscountTypeEnum(Integer type, String desc){
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
        for(DiscountTypeEnum discountTypeEnum:DiscountTypeEnum.values()){
            typeDescMap.put(discountTypeEnum.getType(),discountTypeEnum.getDesc());
        }
    }

}
