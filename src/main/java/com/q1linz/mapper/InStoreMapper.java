package com.q1linz.mapper;

import com.q1linz.entity.InStore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.q1linz.page.Page;
import org.apache.ibatis.annotations.Param;

import java.awt.print.PrinterGraphics;
import java.util.List;

/**
* @author Qilin_
* @description 针对表【in_store(入库单)】的数据库操作Mapper
* @createDate 2024-01-31 15:26:13
* @Entity com.q1linz.entity.InStore
*/
public interface InStoreMapper extends BaseMapper<InStore> {
    //查询入库单总行数的方法
    Long selectInStoreCount(InStore inStore);

    //分页查询入库单的方法
    List<InStore> selectInStorePage(@Param("page") Page page, @Param("inStore") InStore inStore);

    Integer updateIsInById(Integer insId);

}




