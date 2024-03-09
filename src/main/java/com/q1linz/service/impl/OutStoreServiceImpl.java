package com.q1linz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.q1linz.entity.OutStore;
import com.q1linz.entity.Product;
import com.q1linz.entity.Result;
import com.q1linz.mapper.ProductMapper;
import com.q1linz.page.Page;
import com.q1linz.service.OutStoreService;
import com.q1linz.mapper.OutStoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【out_store(出库单)】的数据库操作Service实现
* @createDate 2024-01-31 13:52:59
*/
@Service
public class OutStoreServiceImpl extends ServiceImpl<OutStoreMapper, OutStore> implements OutStoreService{
    @Autowired
    private OutStoreMapper outStoreMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Result saveOutStore(OutStore outStore) {
        outStore.setIsOut("0");

        int i = outStoreMapper.insert(outStore);
        if(i > 0){
            return Result.ok("添加出库单成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"添加出库单失败");
    }

    @Override
    public Page outStorePage(Page page, OutStore outStore) {
        Long count = outStoreMapper.outStoreCount(outStore);

        List<OutStore> outStoreList = outStoreMapper.outStorePage(page, outStore);

        page.setResultList(outStoreList);
        page.setTotalNum(count);

        return page;
    }

    @Transactional//事务处理
    @Override
    public Result confirmOutStore(OutStore outStore) {

        //根据商品id查询商品
        Product product = productMapper.selectById(outStore.getProductId());
        if(outStore.getOutNum()>product.getProductInvent()){
            return Result.err(Result.CODE_ERR_BUSINESS, "商品库存不足");
        }

        //根据id将出库单状态改为已出库
        int i = outStoreMapper.updateIsOutById(outStore.getOutsId());
        if(i>0){
            //根据商品id减商品库存
            int j = productMapper.addInventById(outStore.getProductId(), -outStore.getOutNum());
            if(j>0){
                return Result.ok("出库成功！");
            }
            return Result.err(Result.CODE_ERR_BUSINESS, "出库失败！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "出库失败！");
    }
}




