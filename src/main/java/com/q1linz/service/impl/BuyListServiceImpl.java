package com.q1linz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.q1linz.entity.BuyList;
import com.q1linz.entity.Result;
import com.q1linz.entity.Store;
import com.q1linz.page.Page;
import com.q1linz.service.BuyListService;
import com.q1linz.mapper.BuyListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【buy_list(采购单)】的数据库操作Service实现
* @createDate 2024-01-31 13:15:23
*/
@Service
public class BuyListServiceImpl extends ServiceImpl<BuyListMapper, BuyList> implements BuyListService{
    @Autowired
    private BuyListMapper buyListMapper;


    @Override
    public Page queryPurchasePage(Page page, BuyList buyList) {

        //查询采购单总行数
        Long purchaseCount = buyListMapper.selectPurchaseCount(buyList);

        //分页查询采购单
        List<BuyList> purchaseList = buyListMapper.selectPurchasePage(page, buyList);

        //将查询到的总行数和当前页数据组装到Page对象
        page.setTotalNum(purchaseCount);
        page.setResultList(purchaseList);

        return page;
    }



    @Override
    public Result saveBuyList(BuyList buyList) {
        buyList.setIsIn("0");
        int i = buyListMapper.insert(buyList);
        if(i > 0){
            return Result.ok("添加采购单成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"添加采购单失败");
    }

    @Override
    public Result deletePurchase(Integer buyId) {
        //根据id删除采购单
        int i = buyListMapper.deleteById(buyId);
        if(i>0){
            return Result.ok("采购单删除成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "采购单删除失败！");
    }

    @Override
    public Result updatePurchase(BuyList buyList) {
        //根据id修改采购单
        int i = buyListMapper.updateById(buyList);
        if(i>0){
            return Result.ok("采购单修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "采购单修改失败！");
    }


}




