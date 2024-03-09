package com.q1linz.service;

import com.q1linz.entity.Result;
import com.q1linz.entity.Store;
import com.baomidou.mybatisplus.extension.service.IService;
import com.q1linz.page.Page;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【store(仓库表)】的数据库操作Service
* @createDate 2024-01-31 16:29:46
*/
public interface StoreService extends IService<Store> {
    Page queryStorePage(Page page, Store store);

    List<Store> queryAllStore();

    Result saveStore(Store store);

    Result checkStoreNum(String storeNum);

    Result updateStore(Store store);

    Result deleteStore(Integer storeId);
}
