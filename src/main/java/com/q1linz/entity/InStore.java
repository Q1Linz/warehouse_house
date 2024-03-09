package com.q1linz.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 入库单
 * @TableName in_store
 */
@Data
@TableName("in_store")
public class InStore implements Serializable {
    /**
     * 
     */
    @TableId(value = "ins_id",type = IdType.AUTO)
    private Integer insId;

    /**
     * 
     */
    private Integer storeId;

    /**
     * 
     */
    private Integer productId;

    /**
     * 
     */
    private Integer inNum;

    /**
     * 
     */
    private Integer createBy;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Date createTime;

    /**
     * 0 否 1 是
     */
    private String isIn;


    @TableField(exist = false)
    private String productName;//商品名称
    @TableField(exist = false)
    private String startTime;//起始时间
    @TableField(exist = false)
    private String endTime;//结束时间
    @TableField(exist = false)
    private String storeName;//仓库名称
    @TableField(exist = false)
    private String userCode;//创建入库单的用户的名称
    @TableField(exist = false)
    private BigDecimal inPrice;//商品入库价格

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        InStore other = (InStore) that;
        return (this.getInsId() == null ? other.getInsId() == null : this.getInsId().equals(other.getInsId()))
            && (this.getStoreId() == null ? other.getStoreId() == null : this.getStoreId().equals(other.getStoreId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getInNum() == null ? other.getInNum() == null : this.getInNum().equals(other.getInNum()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIsIn() == null ? other.getIsIn() == null : this.getIsIn().equals(other.getIsIn()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInsId() == null) ? 0 : getInsId().hashCode());
        result = prime * result + ((getStoreId() == null) ? 0 : getStoreId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getInNum() == null) ? 0 : getInNum().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIsIn() == null) ? 0 : getIsIn().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", insId=").append(insId);
        sb.append(", storeId=").append(storeId);
        sb.append(", productId=").append(productId);
        sb.append(", inNum=").append(inNum);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", isIn=").append(isIn);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}