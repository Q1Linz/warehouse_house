package com.q1linz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.q1linz.entity.Supply;
import com.q1linz.service.SupplyService;
import com.q1linz.mapper.SupplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【supply(供货商)】的数据库操作Service实现
* @createDate 2024-01-30 14:55:50
*/
@Service
@CacheConfig(cacheNames = "com.q1linz.service.impl.SupplyServiceImpl")
public class SupplyServiceImpl extends ServiceImpl<SupplyMapper, Supply> implements SupplyService{
    @Autowired
    private SupplyMapper supplyMapper;

    @Override
    @Cacheable(key = "'all:supply'")
    public List<Supply> queryAllSupply() {
        List<Supply> supplyList = supplyMapper.selectList(null);
        return supplyList;
    }

}




