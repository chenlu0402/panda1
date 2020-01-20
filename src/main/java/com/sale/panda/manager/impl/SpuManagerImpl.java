package com.sale.panda.manager.impl;

import com.sale.panda.dao.SpuMapper;
import com.sale.panda.dao.entity.Spu;
import com.sale.panda.dao.entity.SpuPageQuery;
import com.sale.panda.manager.SpuManager;
import com.sale.panda.manager.constants.ObjectTypeContant;
import com.sale.panda.manager.constants.ResponseStatus;
import com.sale.panda.manager.entity.ExcelModel;
import com.sale.panda.manager.entity.PageQueryResult;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.sale.panda.manager.entity.ServiceException;
import com.sale.panda.manager.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * TODO
 *
 * @author chenlu
 * @version V1.0
 * @since 2019-12-28 17:38
 */
@Component
@Slf4j
public class SpuManagerImpl implements SpuManager {

    @Resource
    private SpuMapper spuMapper;

    @Override
    public void handleUpload(MultipartFile file) {
        try {
            InputStream is = file.getInputStream();
            String fileName = file.getOriginalFilename();
            ExcelModel excelModel = ExcelUtils.readExcel(is, fileName);
            if (excelModel == null) {
                throw new ServiceException(ResponseStatus.EMPTY_UPLOAD);
            }
            List<Map<String, String>> rows = excelModel.getContextList();
            List<Spu> spus = rows.stream().map(row -> {
                return getSpuByRowList(row);
            }).collect(Collectors.toList());
            spuMapper.batchInsert(spus);
        } catch (Exception e) {
            log.error("导入商品数据异常，message={}", e.getMessage());
        }
    }

    public Spu getSpuByRowList(Map<String, String> resultMap) {
        if (MapUtils.isEmpty(resultMap)) {
            return null;
        }
        Spu spu = new Spu();
        //把excel的值按照对应类型保存到对象
        Field[] fields = Spu.class.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            //得到属性的名称
            String modelValue = field.getName();
            //得到属性的值
            String value = resultMap.get(modelValue);
            if (StringUtils.isBlank(value)) {
                continue;
            }
            field.setAccessible(true);
            try {
                if(Objects.equals(modelValue,"inPrice") || Objects.equals(modelValue,"salePrice")){
                    field.set(spu, new BigDecimal(value));
                }else if (field.getGenericType().getTypeName().equals(ObjectTypeContant.STRINGTYPE)) {
                    field.set(spu, value);
                } else if (field.getGenericType().getTypeName().equals(ObjectTypeContant.INTEGERTYPE)) {
                    field.set(spu, Integer.valueOf(value));
                } else if (field.getGenericType().getTypeName().equals(ObjectTypeContant.LONGTYPE)) {
                    field.set(spu, Long.valueOf(value));
                } else if (field.getGenericType().getTypeName().equals(ObjectTypeContant.DOUBLETYPE)) {
                    field.set(spu, Double.valueOf(value));
                }
            } catch (Exception e) {
                log.info("转化失败，modelValue={},value={}", modelValue, value);
            }
        }
        return spu;
    }

    @Override
    public Integer insert(Spu spu) {
        return spuMapper.insert(spu);
    }

    @Override
    public Integer batchInsert(List<Spu> goods) {
        return spuMapper.batchInsert(goods);
    }

    @Override
    public Integer update(Spu spu) {
        return spuMapper.update(spu);
    }

    @Override
    public PageQueryResult<List<Spu>> pageQuery(SpuPageQuery pageQuery) {
        List<Spu> result = spuMapper.pageQuery(pageQuery);
        Integer total = spuMapper.count(pageQuery);
        return new PageQueryResult<List<Spu>>(total, result);
    }
}
