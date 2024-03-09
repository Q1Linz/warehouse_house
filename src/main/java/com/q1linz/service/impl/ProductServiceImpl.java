package com.q1linz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.q1linz.entity.Product;
import com.q1linz.entity.Result;
import com.q1linz.entity.Store;
import com.q1linz.page.Page;
import com.q1linz.service.ProductService;
import com.q1linz.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author Qilin_
* @description 针对表【product(商品表)】的数据库操作Service实现
* @createDate 2024-01-30 16:15:24
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService{

    @Autowired
    private ProductMapper productMapper;

    @Value("${file.access-path}")
    private String accessPath;


    @Override
    public Page queryProductPage(Page page, Product product) {

        page.setLimitIndex(page.getPageSize() * (page.getPageNum() - 1));
        //查询商品总行数
        int productCount = productMapper.selectProductCount(product);

        //分页查询商品
        List<Product> productList = productMapper.selectProductPage(page, product);

        //将查询到的总行数和当前页数据组装到Page对象
        page.setTotalNum(Long.valueOf(String.valueOf(productCount)));
        page.setResultList(productList);

        return page;
    }

    @Override
    public Result saveProduct(Product product) {

        product.setImgs(accessPath+product.getImgs());
        product.setUpDownState("0");
        int i = productMapper.insert(product);

        if(i > 0){
            return Result.ok("添加商品成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"添加商品失败");
    }

    @Override
    public Result updateProductState(Product product) {
        int i = productMapper.updateById(product);
        if(i > 0){
            return Result.ok("修改成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"修改失败");
    }

    @Override
    public Result deleteProduct(Integer productId) {
        int i = productMapper.deleteById(productId);
        if(i > 0){
            return Result.ok("商品删除成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"商品删除失败");
    }

    @Override
    public Result updateProduct(Product product) {
        if(!product.getImgs().startsWith(accessPath)){
            product.setImgs(accessPath+product.getImgs());
        }
        System.out.println("product = " + product);
        int i = productMapper.updateById(product);
        if(i > 0){
            return Result.ok("商品修改成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"商品修改失败");
    }

}




