package com.q1linz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.q1linz.entity.AuthInfo;
import com.q1linz.entity.Result;
import com.q1linz.entity.User;

/*
    user_info的service接口
 */
public interface UserService extends IService<User> {
    User findUserByCode(String userCode);

    Page<User> findUserByPage(Integer pageNum,Integer pageSize, User user);

    Result saveUser(User user);
    Result updateUserState(User user);

    int deleteUserById(Integer userId);
    //修改用户昵称的业务方法
     Result updateUserName(User user);

    Result resetPwd(Integer userId);

}
