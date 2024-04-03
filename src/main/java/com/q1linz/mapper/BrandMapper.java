package com.q1linz.mapper;

import com.q1linz.entity.Brand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Qilin_
* @description 针对表【brand(品牌)】的数据库操作Mapper
* @createDate 2024-01-30 14:56:03
* @Entity com.q1linz.entity.Brand
*/
@Mapper
public interface BrandMapper extends BaseMapper<Brand> {

}




