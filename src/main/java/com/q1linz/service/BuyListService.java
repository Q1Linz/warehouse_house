package com.q1linz.service;

import com.q1linz.entity.BuyList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.q1linz.entity.Result;
import com.q1linz.page.Page;

import java.awt.geom.RectangularShape;

/**
* @author Qilin_
* @description 针对表【buy_list(采购单)】的数据库操作Service
* @createDate 2024-01-31 13:15:23
*/
public interface BuyListService extends IService<BuyList> {

    Page queryPurchasePage(Page page, BuyList buyList);
    Result saveBuyList(BuyList buyList);

    Result deletePurchase(Integer buyId);

    Result updatePurchase(BuyList buyList);

}
