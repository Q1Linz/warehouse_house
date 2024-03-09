package com.q1linz.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 角色表的实体类
 */
@Data
@ToString
@TableName("role")
public class Role {

    @TableId(value = "role_id",type = IdType.AUTO)
    private Integer roleId;//角色id

    private String roleName;//角色名称

    private String roleDesc;//角色描述

    private String roleCode;//角色标识

    private String roleState;//角色状态

    private int createBy;//创建角色的用户id

    //json转换的日期格式
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;//创建时间

    private int updateBy;//修改角色的用户id

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;//修改时间

    @TableField(exist = false)
    private String getCode;//追加的属性--创建角色的用户的用户名


    public Role(){

    }

    public Role(Integer roleId, String roleName, String roleDesc, String roleCode, String roleState, int createBy, Date createTime, int updateBy, Date updateTime, String getCode) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.roleCode = roleCode;
        this.roleState = roleState;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.getCode = getCode;
    }
}