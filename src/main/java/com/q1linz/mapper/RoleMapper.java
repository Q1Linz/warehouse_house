package com.q1linz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.q1linz.entity.Role;

import java.util.List;


public interface RoleMapper extends BaseMapper<Role> {
     List<Role> findRolesByUserId(Integer userId);


    //根据用户id删除给用户已分配的所有角色
    Integer delRoleByUserId(Integer userId);


    //添加用户角色关系的方法
    void insertUserRole(Integer userId, Integer roleId);

    //根据角色id查询角色已分配的所有权限(菜单)的id
    public List<Integer> findAuthIds(Integer roleId);




}
