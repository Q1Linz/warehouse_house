package com.q1linz.service;

import com.q1linz.entity.BuyList;
import com.q1linz.entity.ProductType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.q1linz.entity.Result;
import com.q1linz.page.Page;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【product_type(商品分类表)】的数据库操作Service
* @createDate 2024-01-31 14:05:20
*/
public interface ProductTypeService extends IService<ProductType> {
    List<ProductType> allProductTypeTree();

    Result queryTypeByCode(String typeCode);

    Result saveProductType(ProductType productType);

    Result removeProductType(Integer typeId);

    Result updateProductType(ProductType productType);


}
