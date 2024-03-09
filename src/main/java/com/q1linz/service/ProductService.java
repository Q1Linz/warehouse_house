package com.q1linz.service;

import com.q1linz.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.q1linz.entity.Result;
import com.q1linz.page.Page;

/**
* @author Qilin_
* @description 针对表【product(商品表)】的数据库操作Service
* @createDate 2024-01-30 16:15:24
*/
public interface ProductService extends IService<Product> {
    Page queryProductPage(Page page, Product product);

    Result saveProduct(Product product);

    Result updateProductState(Product product);

    Result deleteProduct(Integer productId);

    Result updateProduct(Product product);
}
