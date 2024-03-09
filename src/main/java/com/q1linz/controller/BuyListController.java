package com.q1linz.controller;

import com.q1linz.entity.*;
import com.q1linz.page.Page;
import com.q1linz.service.BuyListService;
import com.q1linz.service.InStoreService;
import com.q1linz.service.StoreService;
import com.q1linz.utils.TokenUtils;
import com.q1linz.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisPipelineException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/purchase")
@RestController
public class BuyListController {

    //注入PurchaseService
    @Autowired
    private BuyListService buyListService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private InStoreService inStoreService;

    @RequestMapping("/purchase-page-list")
    public Result purchasePageList(Page page, BuyList buyList){
        //执行业务
        page = buyListService.queryPurchasePage(page, buyList);
        //响应
        return Result.ok(page);
    }

    @RequestMapping("/store-list")
    public Result storeList(){
        //执行业务
        List<Store> storeList = storeService.queryAllStore();
        //响应
        return Result.ok(storeList);
    }

    /**
     * 添加采购单的url接口/purchase/purchase-add
     */
    @RequestMapping("/purchase-add")
    public Result addPurchase(@RequestBody BuyList buyList){
        //执行业务
        Result result = buyListService.saveBuyList(buyList);
        //响应
        return result;
    }

    @RequestMapping("/purchase-delete/{buyId}")
    public Result deletePurchase(@PathVariable Integer buyId){
        //执行业务
        Result result = buyListService.deletePurchase(buyId);
        //响应
        return result;
    }

    @RequestMapping("/purchase-update")
    public Result updatePurchase(@RequestBody BuyList buyList){
        //执行业务
        Result result = buyListService.updatePurchase(buyList);
        //响应
        return result;
    }

    @RequestMapping("/in-warehouse-record-add")
    public Result addInStore(@RequestBody BuyList buyList,
                             @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        //获取当前登录的用户
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        //获取当前登录的用户id -- 创建入库单的用户id
        int createBy = currentUser.getUserId();

        //创建InStore对象封装添加的入库单的信息
        InStore inStore = new InStore();
        inStore.setStoreId(buyList.getStoreId());
        inStore.setProductId(buyList.getProductId());
        inStore.setInNum(buyList.getFactBuyNum());
        inStore.setCreateBy(createBy);

        //执行业务
        Result result = inStoreService.saveInStore(inStore, buyList.getBuyId());

        //响应
        return result;
    }


}