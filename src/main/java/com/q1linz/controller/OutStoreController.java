package com.q1linz.controller;


import com.q1linz.entity.CurrentUser;
import com.q1linz.entity.OutStore;
import com.q1linz.entity.Result;
import com.q1linz.entity.Store;
import com.q1linz.page.Page;
import com.q1linz.service.OutStoreService;
import com.q1linz.service.StoreService;
import com.q1linz.utils.TokenUtils;
import com.q1linz.utils.WarehouseConstants;
import org.apache.ibatis.ognl.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/outstore")
public class OutStoreController {

    @Autowired
    private OutStoreService outStoreService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private StoreService storeService;


    @RequestMapping("/outstore-add")
    public Result addOutStore(@RequestBody OutStore outStore, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token){

        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        outStore.setCreateBy(currentUser.getUserId());

        Result result = outStoreService.saveOutStore(outStore);
        return result;

    }

    /**
     * 查询所有仓库的url接口/outstore/store-list
     */
    @RequestMapping("/store-list")
    public Result storeList(){
        //执行业务
        List<Store> storeList = storeService.queryAllStore();
        //响应
        return Result.ok(storeList);
    }

    @RequestMapping("/outstore-page-list")
    public Result outStorePageList(Page page, OutStore outStore){
        //执行业务
        page = outStoreService.outStorePage(page, outStore);
        //响应
        return Result.ok(page);
    }

    @RequestMapping("/outstore-confirm")
    public Result confirmOutStore(@RequestBody OutStore outStore){
        //执行业务
        Result result = outStoreService.confirmOutStore(outStore);
        //响应
        return result;
    }

}
