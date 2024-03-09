package com.q1linz.service;

import com.q1linz.entity.InStore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.q1linz.entity.Result;
import com.q1linz.page.Page;

/**
* @author Qilin_
* @description 针对表【in_store(入库单)】的数据库操作Service
* @createDate 2024-01-31 15:26:13
*/
public interface InStoreService extends IService<InStore> {
    Result saveInStore(InStore inStore, Integer buyId);

    Page queryInStorePage(Page page, InStore inStore);

    Result confirmInStore(InStore inStore);
}
