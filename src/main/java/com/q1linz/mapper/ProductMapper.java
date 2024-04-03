package com.q1linz.mapper;

import com.q1linz.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.q1linz.page.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【product(商品表)】的数据库操作Mapper
* @createDate 2024-01-30 16:15:24
* @Entity com.q1linz.entity.Product
*/
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    //查询商品总行数的方法
    int selectProductCount(Product product);

    //分页查询商品的方法
    List<Product> selectProductPage(@Param("page") Page page, @Param("product") Product product);

    Integer addInventById(Integer productId, Integer invent);
}




