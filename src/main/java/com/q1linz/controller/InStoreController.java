package com.q1linz.controller;

import com.q1linz.entity.InStore;
import com.q1linz.entity.Result;

import com.q1linz.entity.Store;
import com.q1linz.page.Page;
import com.q1linz.service.InStoreService;
import com.q1linz.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/instore")
@RestController
public class InStoreController {

    //注入StoreService
    @Autowired
    private StoreService storeService;
    @Autowired
    private InStoreService inStoreService;

    /**
     * 查询所有仓库的url接口/instore/store-list
     */
    @RequestMapping("/store-list")
    public Result storeList(){
        //执行业务
        List<Store> storeList = storeService.queryAllStore();
        //响应
        return Result.ok(storeList);
    }

    @RequestMapping("/instore-page-list")
    public Result inStorePageList(Page page, InStore inStore){
        //执行业务
        page = inStoreService.queryInStorePage(page, inStore);
        //响应
        return Result.ok(page);
    }

    @RequestMapping("/instore-confirm")
    public Result confirmInStore(@RequestBody InStore inStore){
        //执行业务
        Result result = inStoreService.confirmInStore(inStore);
        //响应
        return result;
    }

}