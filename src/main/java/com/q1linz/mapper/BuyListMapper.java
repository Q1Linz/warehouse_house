package com.q1linz.mapper;

import com.q1linz.entity.BuyList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.q1linz.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【buy_list(采购单)】的数据库操作Mapper
* @createDate 2024-01-31 13:15:23
* @Entity com.q1linz.entity.BuyList
*/
public interface BuyListMapper extends BaseMapper<BuyList> {
    //查询采购单总行数的方法
    Long selectPurchaseCount(BuyList purchase);

    //分页查询采购单的方法
    List<BuyList> selectPurchasePage(@Param("page") Page page, @Param("purchase") BuyList buyList);

    Integer updateIsInById(Integer buyId);
}




