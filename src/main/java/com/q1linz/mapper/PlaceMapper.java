package com.q1linz.mapper;

import com.q1linz.entity.Place;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Qilin_
* @description 针对表【place(产地)】的数据库操作Mapper
* @createDate 2024-01-30 14:56:47
* @Entity com.q1linz.entity.Place
*/
@Mapper
public interface PlaceMapper extends BaseMapper<Place> {

}




