package com.q1linz.service;

import com.q1linz.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【brand(品牌)】的数据库操作Service
* @createDate 2024-01-30 14:56:03
*/
public interface BrandService extends IService<Brand> {

    List<Brand> queryAllBrand();

}
