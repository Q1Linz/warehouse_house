package com.q1linz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.q1linz.dto.AssignRoleDto;
import com.q1linz.entity.Result;
import com.q1linz.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<Role> getAllRole();

    Role queryRoleByRoleId(Integer id);

    //查询用户已分配的角色的业务方法
    List<Role> queryRolesByUserId(Integer userId);

    //给用户分配角色的业务方法
    void assignRole(AssignRoleDto assignRoleDto);

    Page<Role> queryRolePage(Integer pageNum,Integer pageSize, Role role);

    //添加角色的业务方法
    Result saveRole(Role role);

    Result updateRoleState(Role role);

    //查询角色已分配的权限(菜单)的业务方法
    List<Integer> queryAuthIds(Integer roleId);

    void deleteRole(Integer roleId);

    Result updateRoleDesc(Role role);



}
