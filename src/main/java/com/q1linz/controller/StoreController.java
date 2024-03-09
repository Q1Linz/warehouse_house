package com.q1linz.controller;

import com.q1linz.entity.Result;
import com.q1linz.entity.Store;
import com.q1linz.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.q1linz.page.Page;

import java.util.List;

@RequestMapping("/store")
@RestController
public class StoreController {

    //注入StoreService
    @Autowired
    private StoreService storeService;

    @RequestMapping("/store-page-list")
    public Result storePageList(Page page, Store store){
        //执行业务
        page = storeService.queryStorePage(page, store);
        //响应
        return Result.ok(page);
    }

    @RequestMapping("/store-num-check")
    public Result checkStoreNum(String storeNum){
        //执行业务
        Result result = storeService.checkStoreNum(storeNum);
        //响应
        return result;
    }

    @RequestMapping("/store-add")
    public Result addStore(@RequestBody Store store){
        //执行业务
        Result result = storeService.saveStore(store);
        //响应
        return result;
    }

    @RequestMapping("/store-update")
    public Result updateStore(@RequestBody Store store){
        //执行业务
        Result result = storeService.updateStore(store);
        //响应
        return  result;
    }

    @RequestMapping("/store-delete/{storeId}")
    public Result deleteStore(@PathVariable Integer storeId){
        //执行业务
        Result result = storeService.deleteStore(storeId);
        //响应
        return result;
    }

    @RequestMapping("/exportTable")
    public Result exportTable(Page page, Store store){
        //分页查询仓库
        page = storeService.queryStorePage(page, store);
        //拿到当前页数据
        List<?> resultList = page.getResultList();
        //响应
        return Result.ok(resultList);
    }
}