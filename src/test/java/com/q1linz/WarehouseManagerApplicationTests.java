package com.q1linz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.q1linz.entity.AuthInfo;
import com.q1linz.entity.Role;
import com.q1linz.entity.User;
import com.q1linz.mapper.AuthInfoMapper;
import com.q1linz.mapper.RoleMapper;
import com.q1linz.service.AuthInfoService;
import com.q1linz.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WarehouseManagerApplicationTests {

    @Autowired
    private AuthInfoService authInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleMapper roleMapper;


    @Test
    void contextLoads() {

    }

    @Test
    void pageHelperTest(){

        Page<AuthInfo> page = new Page<>();
        page.setCurrent(1);
        page.setSize(5);

        IPage<AuthInfo> authInfoIPage = authInfoService.page(page);

        System.out.println("total: " + authInfoIPage.getTotal());
        System.out.println("pages:" + authInfoIPage.getPages());
        System.out.println("size:" + authInfoIPage.getSize());
        System.out.println("current:" + authInfoIPage.getCurrent());

        List<AuthInfo> records = authInfoIPage.getRecords();

        for (AuthInfo record : records) {
            System.out.println(record);
        }


    }

}
