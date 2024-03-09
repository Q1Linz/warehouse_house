package com.q1linz.service;

import com.q1linz.entity.OutStore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.q1linz.entity.Result;
import com.q1linz.page.Page;

/**
* @author Qilin_
* @description 针对表【out_store(出库单)】的数据库操作Service
* @createDate 2024-01-31 13:52:59
*/
public interface OutStoreService extends IService<OutStore> {

    Result saveOutStore(OutStore outStore);

    Page outStorePage(Page page, OutStore outStore);

    Result confirmOutStore(OutStore outStore);
}
