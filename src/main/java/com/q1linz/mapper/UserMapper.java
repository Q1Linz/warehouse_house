package com.q1linz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.q1linz.entity.User;

import java.util.List;

/*
    user_info的mapper接口
 */

public interface UserMapper extends BaseMapper<User> {

    //根据账号查询用户信息
    User findUserByCode(String userCode);

    public int setUserDelete(Integer userId);




}
