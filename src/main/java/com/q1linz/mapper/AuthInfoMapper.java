package com.q1linz.mapper;

import com.q1linz.entity.AuthInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Qilin_
* @description 针对表【auth_info(权限表)】的数据库操作Mapper
* @createDate 2024-01-23 14:24:55
* @Entity com.q1linz.entity.AuthInfo
*/
public interface AuthInfoMapper extends BaseMapper<AuthInfo> {

    //根据id查询用户的所有权限菜单
    public List<AuthInfo> findAuthByUid(Integer userId);


    //根据角色id删除给角色已分配的所有权限(菜单)
    public int delAuthByRoleId(Integer roleId);

    //添加角色权限(菜单)关系的方法
    public void insertRoleAuth(Integer roleId, Integer authId);

}




