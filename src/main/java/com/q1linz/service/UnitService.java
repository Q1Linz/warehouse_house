package com.q1linz.service;

import com.q1linz.entity.Unit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【unit(规格单位表)】的数据库操作Service
* @createDate 2024-01-30 14:57:02
*/
public interface UnitService extends IService<Unit> {
    List<Unit> queryAllUnit();
}
