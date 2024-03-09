package com.q1linz.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.q1linz.dto.AssignAuthDto;
import com.q1linz.entity.AuthInfo;
import com.q1linz.service.AuthInfoService;
import com.q1linz.mapper.AuthInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
* @author Qilin_
* @description 针对表【auth_info(权限表)】的数据库操作Service实现
* @createDate 2024-01-23 14:24:55
*/
@Service
public class AuthInfoServiceImpl extends ServiceImpl<AuthInfoMapper, AuthInfo> implements AuthInfoService{

    @Autowired
    private AuthInfoMapper authInfoMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /*
        查询用户菜单树

        向redis缓存 ---- 键：authTree：userId  值：菜单树List<authInfo>转的json串
     */
    @Override
    public List<AuthInfo> authTreeByUid(Integer uid) {
        //先从redis缓存中查对应的用户菜单
        String authTreeJson = (String) redisTemplate.opsForValue().get("authTree:" + uid);
        //如果有
        if(StringUtils.hasText(authTreeJson)){
            //把json形式的菜单树转回list
            List<AuthInfo> authTreeList = JSON.parseArray(authTreeJson, AuthInfo.class);
            return authTreeList;
        }

        //如果没有,就从数据库中查，存进redis
        List<AuthInfo> authInfoList = authInfoMapper.findAuthByUid(uid);
        //把所有菜单list，转成菜单树
        List<AuthInfo> authTreeList = allAuthToAuthTree(authInfoList, 0);

        redisTemplate.opsForValue().set("authTree:"+uid,JSON.toJSONString(authTreeList));

        return authTreeList;
    }

    @Override
    public List<AuthInfo> allAuthTree() {
        String allAuthTree = (String) redisTemplate.opsForValue().get("authTree:all");
        if(StringUtils.hasText(allAuthTree)){
            List<AuthInfo> allAuthTreeList = JSON.parseArray(allAuthTree, AuthInfo.class);
            return allAuthTreeList;
        }
        List<AuthInfo> allAuthList = authInfoMapper.selectList(null);
        List<AuthInfo> allAuthTreeList = allAuthToAuthTree(allAuthList, 0);
        redisTemplate.opsForValue().set("authTree:all",JSON.toJSONString(allAuthTreeList));

        return allAuthTreeList;
    }

    @Override
    public void assignAuth(AssignAuthDto assignAuthDto) {
        Integer roleId = assignAuthDto.getRoleId();
        List<Integer> authIds = assignAuthDto.getAuthIds();

        authInfoMapper.delAuthByRoleId(roleId);

        for (Integer authId : authIds) {
            authInfoMapper.insertRoleAuth(roleId,authId);
        }

    }


    /**
     *
     * @param allAuthList
     * @param pid
     * @return
     */
    private List<AuthInfo> allAuthToAuthTree(List<AuthInfo> allAuthList,Integer pid){

        List<AuthInfo> firstLevelAuthList = new ArrayList<>();
        for (AuthInfo authInfo : allAuthList) {
            if(authInfo.getParentId().equals(pid)){
                firstLevelAuthList.add(authInfo);
            }
        }

        for (AuthInfo firstAuth : firstLevelAuthList) {
            List<AuthInfo> secondLevelAuthList = allAuthToAuthTree(allAuthList, firstAuth.getAuthId());
            firstAuth.setChildAuth(secondLevelAuthList);
        }

        return firstLevelAuthList;
    }


}




