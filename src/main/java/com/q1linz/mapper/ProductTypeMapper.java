package com.q1linz.mapper;

import com.q1linz.entity.ProductType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Qilin_
* @description 针对表【product_type(商品分类表)】的数据库操作Mapper
* @createDate 2024-01-31 14:05:20
* @Entity com.q1linz.entity.ProductType
*/
@Mapper
public interface ProductTypeMapper extends BaseMapper<ProductType> {
    int deleteProductType(Integer typeId);
}




