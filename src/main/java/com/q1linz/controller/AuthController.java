package com.q1linz.controller;


import com.q1linz.entity.AuthInfo;
import com.q1linz.entity.Result;
import com.q1linz.service.AuthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/auth")
@RestController
public class AuthController {


    @Autowired
    private AuthInfoService authInfoService;

    @RequestMapping("/auth-tree")
    public Result allAuthTree(){
        List<AuthInfo> authInfoList = authInfoService.allAuthTree();

        return Result.ok(authInfoList);
    }




}
