package com.q1linz.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 仓库表
 * @TableName store
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("store")
public class Store implements Serializable {
    /**
     * 
     */
    @TableId(value = "store_id",type = IdType.AUTO)
    private Integer storeId;

    /**
     * 
     */
    private String storeName;

    /**
     * 
     */
    private String storeNum;

    /**
     * 
     */
    private String storeAddress;

    /**
     * 
     */
    private String concat;

    /**
     * 
     */
    private String phone;

    private static final long serialVersionUID = 1L;

}