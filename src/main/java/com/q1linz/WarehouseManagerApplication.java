package com.q1linz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

//mapper接口扫描器(指明mapper接口所在的包，自动为mapper接口创建代理对象并加入ioc容器)
@MapperScan(basePackages = "com.q1linz.mapper")
@SpringBootApplication
@EnableCaching
public class WarehouseManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseManagerApplication.class, args);
    }

}
