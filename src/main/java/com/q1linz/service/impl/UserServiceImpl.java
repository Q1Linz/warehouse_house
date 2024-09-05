package com.q1linz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.q1linz.entity.AuthInfo;
import com.q1linz.entity.Result;
import com.q1linz.entity.Role;
import com.q1linz.entity.User;
import com.q1linz.mapper.AuthInfoMapper;
import com.q1linz.mapper.UserMapper;
import com.q1linz.service.UserService;
import com.q1linz.utils.DigestUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByCode(String userCode) {
        return userMapper.findUserByCode(userCode);
    }

    @Override
    public Page<User> findUserByPage(Integer pageNum, Integer pageSize, User user) {
        Page<User> page = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper
                .eq(StringUtils.hasText(user.getUserState()),User::getUserState,user.getUserState())
                .eq(StringUtils.hasText(user.getUserCode()),User::getUserCode,user.getUserCode())
                .eq(StringUtils.hasText(user.getUserType()),User::getUserType,user.getUserType())
                .eq(User::getIsDelete,0);

        Page<User> userPage = userMapper.selectPage(page, userWrapper);

        List<User> records = userPage.getRecords();
        for (User record : records) {
            record.setGetCode(userMapper.selectById(record.getCreateBy()).getUserCode());
        }

        return userPage;
    }


    @Override
    public Result saveUser(User user) {
        User oldUser = userMapper.findUserByCode(user.getUserCode());
        //用户已存在：返回错误信息
        if(oldUser != null){
            return Result.err(Result.CODE_ERR_BUSINESS,"用户已存在！");
        }
        //用户不存在：加密密码，添加用户
        user.setUserPwd(DigestUtil.hmacSign(user.getUserPwd()));
        user.setUserState("0");
        user.setIsDelete("0");
        userMapper.insert(user);
        return Result.ok("添加用户成功");
    }

    @Override
    public Result updateUserState(User user) {

        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(User::getUserId,user.getUserId());
        //根据用户id修改用户状态
        int i = userMapper.update(user,userWrapper);
        if(i>0){
            return Result.ok("修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "修改失败！");
    }

    @Override
    public int deleteUserById(Integer userId) {
        return userMapper.setUserDelete(userId);
    }

    @Override
    public Result updateUserName(User user) {
        int i = userMapper.updateById(user);
        if(i>0){//修改成功
            return Result.ok("用户修改成功！");
        }
        //修改失败
        return Result.err(Result.CODE_ERR_BUSINESS, "用户修改失败！");

    }

    //重置密码的业务方法
    @Override
    public Result resetPwd(Integer userId) {

        //创建User对象并保存用户id和加密后的重置密码123456
        User user = userMapper.selectById(userId);
        user.setUserPwd(DigestUtil.hmacSign("123456"));

        //根据用户id修改密码
        int i = userMapper.updateById(user);

        if(i>0){//密码修改成功
            return Result.ok("密码重置成功！");
        }
        //密码修改失败
        return Result.err(Result.CODE_ERR_BUSINESS, "密码重置失败！");
    }






}
