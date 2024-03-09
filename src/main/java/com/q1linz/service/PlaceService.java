package com.q1linz.service;

import com.q1linz.entity.Place;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【place(产地)】的数据库操作Service
* @createDate 2024-01-30 14:56:47
*/
public interface PlaceService extends IService<Place> {
    List<Place> queryAllPlace();

}
