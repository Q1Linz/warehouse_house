package com.q1linz.config;


import com.q1linz.filter.LoginCheckFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.FilterRegistration;


/*
原生Servlet配置类
 */
@Configuration
public class ServletConfig {

    @Autowired
    private RedisTemplate redisTemplate;
    /*
    配置FilterRegistrationBean的bean对象，注册原生servlet的过滤器
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        //创建FilterRegistrationBean的bean对象
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        //创建自定义的过滤器
        LoginCheckFilter loginCheckFilter = new LoginCheckFilter();
        //给自定义过滤器注入redis模板
        loginCheckFilter.setRedisTemplate(redisTemplate);
        //将自定义的过滤器注册到FilterRegistrationBean
        filterRegistrationBean.setFilter(loginCheckFilter);
        //给过滤器指定拦截的请求
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
