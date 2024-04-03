package com.q1linz.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.q1linz.entity.BuyList;
import com.q1linz.entity.ProductType;
import com.q1linz.entity.Result;
import com.q1linz.mapper.BuyListMapper;
import com.q1linz.page.Page;
import com.q1linz.service.ProductTypeService;
import com.q1linz.mapper.ProductTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author Qilin_
* @description 针对表【product_type(商品分类表)】的数据库操作Service实现
* @createDate 2024-01-31 14:05:20
*/
@Service
@CacheConfig(cacheNames = "com.q1linz.service.impl.ProductTypeServiceImpl")
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements ProductTypeService{

    //注入ProductTypeMapper
    @Autowired
    private ProductTypeMapper productTypeMapper;

    private LambdaQueryWrapper<ProductType> ProductTypeWrapper = new LambdaQueryWrapper<>();


    /*
      查询所有商品分类树的业务方法
     */
    //对查询到的所有商品分类树进行缓存,缓存到redis的键为all:typeTree
    @Cacheable(key = "'all:typeTree'")
    @Override
    public List<ProductType> allProductTypeTree() {
        //查询所有商品分类
        List<ProductType> allTypeList = productTypeMapper.selectList(null);
        //将所有商品分类List<ProductType>转成商品分类树List<ProductType>
        List<ProductType> typeTreeList = allTypeToTypeTree(allTypeList, 0);
        //返回商品分类树List<ProductType>
        return typeTreeList;
    }

    //将查询到的所有商品分类List<ProductType>转成商品分类树List<ProductType>的算法
    private List<ProductType> allTypeToTypeTree(List<ProductType> allTypeList, Integer parentId){

        List<ProductType> typeList = new ArrayList<>();
        for (ProductType productType : allTypeList) {
            if(productType.getParentId().equals(parentId)){
                typeList.add(productType);
            }
        }

        for (ProductType productType : typeList) {
            List<ProductType> childTypeList = allTypeToTypeTree(allTypeList, productType.getTypeId());
            productType.setChildProductCategory(childTypeList);
        }

        return typeList;
    }

    @Override
    public Result queryTypeByCode(String typeCode) {

        ProductTypeWrapper.eq(ProductType::getTypeCode,typeCode);
        ProductType productType = productTypeMapper.selectOne(ProductTypeWrapper);

        return Result.ok(productType==null);
    }

    @CacheEvict(key = "'all:typeTree'")
    @Override
    @DS("master")
    public Result saveProductType(ProductType productType) {

        ProductTypeWrapper.eq(ProductType::getTypeName,productType.getTypeName());
        ProductType oldType = productTypeMapper.selectOne(ProductTypeWrapper);
        if(oldType != null){
            return Result.err(Result.CODE_ERR_BUSINESS,"分类名称已存在");
        }
        int i = productTypeMapper.insert(productType);
        if(i > 0){
            return Result.ok("分类添加成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"分类添加失败");
    }

    @CacheEvict(key = "'all:typeTree'")
    @Override
    @DS("master")
    public Result removeProductType(Integer typeId) {
        //根据分类id删除分类及其所有子级分类

        int i = productTypeMapper.deleteProductType(typeId);
        if(i>0){
            return Result.ok("分类删除成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "分类删除失败！");
    }

    @CacheEvict(key = "'all:typeTree'")
    @Override
    public Result updateProductType(ProductType productType) {
        int i = productTypeMapper.updateById(productType);
        if(i > 0){
            return Result.ok("分类修改成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"分类修改失败");
    }


}




