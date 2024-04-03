package com.q1linz.mapper;

import com.q1linz.entity.Unit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Qilin_
* @description 针对表【unit(规格单位表)】的数据库操作Mapper
* @createDate 2024-01-30 14:57:02
* @Entity com.q1linz.entity.Unit
*/
@Mapper
public interface UnitMapper extends BaseMapper<Unit> {

}




