package com.q1linz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.q1linz.entity.Unit;
import com.q1linz.service.UnitService;
import com.q1linz.mapper.UnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【unit(规格单位表)】的数据库操作Service实现
* @createDate 2024-01-30 14:57:02
*/
@CacheConfig(cacheNames = "com.q1linz.service.impl.UnitServiceImpl")
@Service
public class UnitServiceImpl extends ServiceImpl<UnitMapper, Unit> implements UnitService{
    //注入UnitMapper
    @Autowired
    private UnitMapper unitMapper;

    /*
      查询所有单位的业务方法
     */
    //对查询到的所有单位进行缓存,缓存到redis的键为all:unit
    @Cacheable(key = "'all:unit'")
    @Override
    public List<Unit> queryAllUnit() {
        //查询所有单位
        return unitMapper.selectList(null);
    }

}




