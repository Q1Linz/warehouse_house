package com.q1linz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.q1linz.dto.AssignRoleDto;
import com.q1linz.entity.Result;
import com.q1linz.entity.Role;
import com.q1linz.mapper.AuthInfoMapper;
import com.q1linz.mapper.RoleMapper;
import com.q1linz.mapper.UserMapper;
import com.q1linz.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AuthInfoMapper authInfoMapper;

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Role> getAllRole() {
        LambdaQueryWrapper<Role> roleWrapper = new LambdaQueryWrapper<>();
        roleWrapper.eq(Role::getRoleState,1);
        List<Role> roleList = roleMapper.selectList(roleWrapper);
        return roleList;
    }

    @Override
    public Role queryRoleByRoleId(Integer id) {
        return roleMapper.selectById(id);
    }

    @Override
    public List<Role> queryRolesByUserId(Integer userId) {
        List<Role> roles = roleMapper.findRolesByUserId(userId);
        return roles;
    }

    @Transactional
    @Override
    public void assignRole(AssignRoleDto assignRoleDto) {

        //拿到用户id
        Integer userId = assignRoleDto.getUserId();
        //拿到给用户分配的所有角色名
        List<String> roleIdList = assignRoleDto.getRoleCheckList();

        //根据用户id删除给用户已分配的所有角色
        roleMapper.delRoleByUserId(userId);

        //循环添加用户角色关系
        for (String roleId : roleIdList) {
            roleMapper.insertUserRole(userId,Integer.valueOf(roleId));
        }
    }

    @Override
    public Page<Role> queryRolePage(Integer pageNum, Integer pageSize, Role role) {
        Page<Role> page = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<Role> roleWrapper = new LambdaQueryWrapper<>();
        roleWrapper
                .eq(StringUtils.hasText(role.getRoleState()),Role::getRoleState,role.getRoleState())
                .eq(StringUtils.hasText(role.getRoleCode()),Role::getRoleCode,role.getRoleCode())
                .eq(StringUtils.hasText(role.getRoleName()),Role::getRoleName,role.getRoleName());

        Page<Role> rolePage = roleMapper.selectPage(page, roleWrapper);

        for (Role record : rolePage.getRecords()) {
            record.setGetCode(userMapper.selectById(record.getCreateBy()).getUserCode());
        }


        return rolePage;
    }

    @Transactional
    @Override
    public Result saveRole(Role role) {
        LambdaQueryWrapper<Role> roleWrapper = new LambdaQueryWrapper<>();
        roleWrapper.eq(Role::getRoleName,role.getRoleName());
        Role oldRole = roleMapper.selectOne(roleWrapper);
        if(oldRole != null){
            return Result.err(Result.CODE_ERR_BUSINESS,"角色已存在！");
        }

        roleMapper.insert(role);
        return Result.ok("添加角色成功");

    }

    @Override
    public Result updateRoleState(Role role) {
        int i = roleMapper.updateById(role);
        if(i > 0){
            return Result.ok("修改成功！");
        }

        return Result.err(Result.CODE_ERR_BUSINESS,"修改失败");
    }

    //查询角色已分配的权限(菜单)的业务方法
    @Override
    public List<Integer> queryAuthIds(Integer roleId) {
        //根据角色id查询角色已分配的所有权限(菜单)的id
        return roleMapper.findAuthIds(roleId);
    }

    @Transactional//事务处理
    @Override
    public void deleteRole(Integer roleId) {
        //根据角色id删除角色
        int i = roleMapper.deleteById(roleId);
        if(i>0){
            //根据角色id删除给角色已分配的所有权限(菜单)
            authInfoMapper.delAuthByRoleId(roleId);
        }
    }

    @Override
    public Result updateRoleDesc(Role role) {

        int i = roleMapper.updateById(role);
        if(i > 0){
            return Result.ok("角色修改成功!");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"角色修改失败!");

    }


}
