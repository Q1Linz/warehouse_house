package com.q1linz.mapper;

import com.q1linz.entity.OutStore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.q1linz.page.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【out_store(出库单)】的数据库操作Mapper
* @createDate 2024-01-31 13:52:59
* @Entity com.q1linz.entity.OutStore
*/
@Mapper
public interface OutStoreMapper extends BaseMapper<OutStore> {
    Long outStoreCount(OutStore outStore);

    //分页查询出库单的方法
    List<OutStore> outStorePage(@Param("page") Page page, @Param("outStore") OutStore outStore);

    int updateIsOutById(Integer outsId);
}




