package com.q1linz.mapper;

import com.q1linz.entity.Store;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.q1linz.page.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【store(仓库表)】的数据库操作Mapper
* @createDate 2024-01-31 16:29:46
* @Entity com.q1linz.entity.Store
*/
@Mapper
public interface StoreMapper extends BaseMapper<Store> {
    Long selectStoreCount(Store store);

    //分页查询仓库的方法
    List<Store> selectStorePage(@Param("page") Page page, @Param("store") Store store);
}




