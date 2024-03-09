package com.q1linz.filter;

import com.alibaba.fastjson.JSON;
import com.q1linz.entity.Result;
import com.q1linz.utils.TokenUtils;
import com.q1linz.utils.WarehouseConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.undo.CannotUndoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
自定义登录限制过滤器
 */
public class LoginCheckFilter implements Filter {

    private RedisTemplate redisTemplate;

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    //过滤器拦截到请求执行的方法
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //白名单请求，直接放行
        //创建白名单url的list
        List<String> urlList= new ArrayList<>();
        urlList.add("/captcha/captchaImage");
        urlList.add("/login");
        urlList.add("/logout");
        urlList.add("/product/img-upload");

        //获取当前请求的url接口
        String servletPath = request.getServletPath();
        if(urlList.contains(servletPath)||servletPath.contains("/img/upload")) {
            filterChain.doFilter(request, response);
            return;
        }
        //校验其他请求是否携带token，判断redis中是否存在redis的键
        String token = request.getHeader(WarehouseConstants.HEADER_TOKEN_NAME);
        //如果请求中有token，且匹配redis，就放行
        if(StringUtils.hasText(token) && redisTemplate.hasKey(token)){
            filterChain.doFilter(request,response);
            return;
        }

        //没有，不放行，给前端做响应
        Result err = Result.err(Result.CODE_ERR_UNLOGINED, "LoginCheckFilter拦截：您尚未登录!");
        String jsonString = JSON.toJSONString(err);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(jsonString);
        out.close();
    }
}
