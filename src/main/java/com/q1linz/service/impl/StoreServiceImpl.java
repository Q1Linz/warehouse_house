package com.q1linz.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.q1linz.entity.Result;
import com.q1linz.entity.Store;
import com.q1linz.page.Page;
import com.q1linz.service.StoreService;
import com.q1linz.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【store(仓库表)】的数据库操作Service实现
* @createDate 2024-01-31 16:29:46
*/
@CacheConfig(cacheNames = "com.q1linz.service.impl.StoreServiceImpl")
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService{
    //注入StoreMapper
    @Autowired
    private StoreMapper storeMapper;

    //分页查询仓库的业务方法
    @Override
    public Page queryStorePage(Page page, Store store) {

        //查询仓库总行数
        Long storeCount = storeMapper.selectStoreCount(store);

        //分页查询仓库
        List<Store> storeList = storeMapper.selectStorePage(page, store);

        //将查到的总行数和当前页数据封装到Page对象
        page.setTotalNum(storeCount);
        page.setResultList(storeList);

        return page;
    }

    @Override
    public Result checkStoreNum(String storeNum) {
        //根据仓库编号查询仓库

        LambdaQueryWrapper<Store> storeWrapper = new LambdaQueryWrapper<>();
        storeWrapper.eq(Store::getStoreNum,storeNum);
        Store store = storeMapper.selectOne(storeWrapper);
        return Result.ok(store==null);
    }

    @Override
    public List<Store> queryAllStore() {
        return storeMapper.selectList(null);
    }

    @Override
    public Result saveStore(Store store) {
        //添加仓库
        int i = storeMapper.insert(store);
        if(i>0){
            return Result.ok("仓库添加成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "仓库添加失败！");
    }

    @Override
    public Result updateStore(Store store) {
        //根据仓库id修改仓库
        int i = storeMapper.updateById(store);
        if(i>0){
            return Result.ok("仓库修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "仓库修改失败！");
    }

    @Override
    public Result deleteStore(Integer storeId) {
        //根据仓库id删除仓库
        int i = storeMapper.deleteById(storeId);
        if(i>0){
            return Result.ok("仓库删除成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "仓库删除失败！");
    }
}




