package com.q1linz.mapper;

import com.q1linz.entity.Supply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Qilin_
* @description 针对表【supply(供货商)】的数据库操作Mapper
* @createDate 2024-01-30 14:55:50
* @Entity com.q1linz.entity.Supply
*/
@Mapper
public interface SupplyMapper extends BaseMapper<Supply> {

}




