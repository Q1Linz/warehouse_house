package com.q1linz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.q1linz.entity.Brand;
import com.q1linz.service.BrandService;
import com.q1linz.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【brand(品牌)】的数据库操作Service实现
* @createDate 2024-01-30 14:56:03
*/
@CacheConfig(cacheNames = "com.q1linz.service.impl.BrandServiceImpl")
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService{

    @Autowired
    private BrandMapper brandMapper;
    @Override
    @Cacheable(key = "'all:brand'")
    public List<Brand> queryAllBrand() {
        List<Brand> brandList = brandMapper.selectList(null);
        return brandList;
    }
}




