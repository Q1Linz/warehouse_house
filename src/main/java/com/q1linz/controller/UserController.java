package com.q1linz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.q1linz.dto.AssignRoleDto;
import com.q1linz.entity.CurrentUser;
import com.q1linz.entity.Result;
import com.q1linz.entity.Role;
import com.q1linz.entity.User;

import com.q1linz.mapper.UserMapper;
import com.q1linz.service.RoleService;
import com.q1linz.service.UserService;
import com.q1linz.utils.TokenUtils;
import com.q1linz.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private RoleService roleService;

    //MP 分页查询用户
    @RequestMapping("/user-list")
    public Result userList(Integer pageNum,Integer pageSize, User user){

        Page<User> userByPage = userService.findUserByPage(pageNum, pageSize, user);

        List<User> records = userByPage.getRecords();

        com.q1linz.page.Page page = new com.q1linz.page.Page();

        page.setPageNum(userByPage.getCurrent());
        page.setPageSize(userByPage.getSize());
        page.setTotalNum(userByPage.getTotal());
        page.setPageCount(userByPage.getPages());
        page.setLimitIndex(userByPage.getCurrent());
        page.setResultList(userByPage.getRecords());

        return Result.ok(page);
    }

    //添加用户 /user/addUser
    @RequestMapping("/addUser")
    public Result addUser(@RequestBody User user, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        user.setCreateBy(currentUser.getUserId());
        return userService.saveUser(user);
    }

    //启用禁用用户
    @RequestMapping("/updateState")
    public Result updateState(@RequestBody User user,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        user.setUpdateBy(currentUser.getUserId());
        return userService.updateUserState(user);
    }


    /**
     * 查询用户已分配的角色的url接口/user/user-role-list/{userId}
     */
    @RequestMapping("/user-role-list/{userId}")
    public Result userRoleList(@PathVariable Integer userId){
        //执行业务
        List<Role> roleList = roleService.queryRolesByUserId(userId);
        //响应
        return Result.ok(roleList);
    }


    /**
     * 给用户分配角色的url接口/user/assignRole
     *
     * @RequestBody AssignRoleDto assignRoleDto将请求传递的json数据
     * 封装到参数AssignRoleDto对象中;
     */
    @RequestMapping("/assignRole")
    public Result assignRole(@RequestBody AssignRoleDto assignRoleDto){
        //执行业务
        roleService.assignRole(assignRoleDto);
        //响应
        return Result.ok("分配角色成功！");
    }

    /**
     * 删除用户的url接口/user/deleteUser/{userId}
     */
    @RequestMapping("/deleteUser/{userId}")
    public Result deleteUser(@PathVariable Integer userId){
        //执行业务
        userService.deleteUserById(userId);
        //响应
        return Result.ok("用户删除成功！");
    }

    /**
     * 修改用户的url接口/user/updateUser
     *
     * @RequestBody User user将请求传递的json数据封装到参数User对象;
     * @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token
     * 将请求头Token的值即客户端归还的token赋值给参数变量token;
     */
    @RequestMapping("/updateUser")
    public Result updateUser(@RequestBody User user,
                             @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        //获取当前登录的用户
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        //获取当前登录的用户id -- 修改用户的用户id
        int updateBy = currentUser.getUserId();

        user.setUpdateBy(updateBy);

        //执行业务
        Result result = userService.updateUserName(user);

        //响应
        return result;
    }


    /**
     * 重置密码的url接口/user/updatePwd/{userId}
     */
    @RequestMapping("/updatePwd/{userId}")
    public Result resetPassWord(@PathVariable Integer userId){
        //执行业务
        Result result = userService.resetPwd(userId);
        //响应
        return result;
    }



}
