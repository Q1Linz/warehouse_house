package com.q1linz.service;

import com.q1linz.dto.AssignAuthDto;
import com.q1linz.entity.AuthInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【auth_info(权限表)】的数据库操作Service
* @createDate 2024-01-23 14:24:55
*/
public interface AuthInfoService extends IService<AuthInfo> {

    List<AuthInfo> authTreeByUid(Integer uid);

    //查询整个权限(菜单)树的业务方法
    List<AuthInfo> allAuthTree();

    //给角色分配权限(菜单)的业务方法
    public void assignAuth(AssignAuthDto assignAuthDto);

}
