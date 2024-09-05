package com.q1linz.controller;

import com.google.code.kaptcha.Producer;
import com.q1linz.entity.*;
import com.q1linz.service.AuthInfoService;
import com.q1linz.service.UserService;
import com.q1linz.utils.DigestUtil;
import com.q1linz.utils.TokenUtils;
import com.q1linz.utils.WarehouseConstants;
import org.apache.ibatis.ognl.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Path;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {

    //注入DefaultKaptcha对象，生成验证码图片
    @Resource(name = "captchaProducer")
    private Producer producer;

    //注入redis模板，操作redis
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthInfoService authInfoService;

    /**
     * 生成验证码图片url接口
     * @param response
     */
    @RequestMapping("/captcha/captchaImage")
    public void captchaImage(HttpServletResponse response){
        //生成验证码图片的文本
        String text = producer.createText();
        //使用文本生成验证码图片
        BufferedImage image = producer.createImage(text);
        //将验证码文本作为键保存到redis
        //得到一个操作器，操作键、值都为字符串类型的键值对的,设置过期时间30分钟
        redisTemplate.opsForValue().set(text,"",60*30, TimeUnit.SECONDS);


        //把验证码图片响应给前端
        //1、设置响应正文：image/jpeg
        response.setContentType("image/jpeg");
        //2、将验证码图片写给前端,先拿到字节输出流
        ServletOutputStream outputStream = null;
        try {

             outputStream = response.getOutputStream();
            ImageIO.write(image,"jpg",outputStream);
            //刷新
            outputStream.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            //关闭字节输出流
            if (outputStream != null ){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    /**
     * 登录url接口
     * @param loginUser 接收封装前端的登录用户信息json数据
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody LoginUser loginUser){
        //拿到验证码
        String code = loginUser.getVerificationCode();
        //与redis中进行判断
        if(!redisTemplate.hasKey(code)){
            return Result.err(Result.CODE_ERR_BUSINESS,"验证码错误");
        }
        //根据账号查询用户
        User user = userService.findUserByCode(loginUser.getUserCode());
        if(user != null){
            if(user.getUserState().equals(WarehouseConstants.USER_STATE_PASS)){
                //得到用户密码，并对其进行MD5加密
                String userPwd = DigestUtil.hmacSign(loginUser.getUserPwd());
                if(userPwd.equals(user.getUserPwd())){
                    //所有验证通过，生成JWT,存储到redis
                    CurrentUser currentUser = new CurrentUser(user.getUserId(),user.getUserCode(),user.getUserName());
                    String token = tokenUtils.loginSign(currentUser, userPwd);
                    //向客户端响应jwt
                    return Result.ok("登录成功",token);
                }else {
                    //密码错误
                    return Result.err(Result.CODE_ERR_BUSINESS,"密码错误");
                }
            }else {
                //用户未审核
                return Result.err(Result.CODE_ERR_BUSINESS,"用户未审核");
            }
        }else {
            //账号不存在
            return Result.err(Result.CODE_ERR_BUSINESS,"账号不存在");
        }
    }

    //获取当前登录用户信息
    @RequestMapping("/curr-user")
    public Result currentUser(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        return Result.ok(currentUser);
    }

    //加载用户权限菜单树
    @RequestMapping("/user/auth-list")
    public Result loadAuthTree(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
        List<AuthInfo> authTree = authInfoService.authTreeByUid(userId);
        return Result.ok(authTree);
    }

    //登出
    @RequestMapping("/logout")
    public Result logout(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        redisTemplate.delete(token);
        return Result.ok("退出成功！");
    }




}
