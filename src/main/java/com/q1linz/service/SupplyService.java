package com.q1linz.service;

import com.q1linz.entity.Supply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【supply(供货商)】的数据库操作Service
* @createDate 2024-01-30 14:55:50
*/
public interface SupplyService extends IService<Supply> {

    List<Supply> queryAllSupply();

}
