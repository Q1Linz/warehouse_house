package com.q1linz.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 采购单
 * @TableName buy_list
 */
@Data
@TableName("buy_list")
public class BuyList implements Serializable {
    /**
     * 
     */
    @TableId(value = "buy_id",type = IdType.AUTO)
    private Integer buyId;

    /**
     * 
     */
    private Integer productId;

    /**
     * 
     */
    private Integer storeId;

    /**
     * 
     */
    private Integer buyNum;

    /**
     * 
     */
    private Integer factBuyNum;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Date buyTime;

    /**
     * 
     */
    private Integer supplyId;

    /**
     * 
     */
    private Integer placeId;

    /**
     * 
     */
    private String buyUser;

    /**
     * 
     */
    private String phone;

    /**
     * 0 否 1 是
     */
    private String isIn;

    //---------------追加属性---------------------------

    @TableField(exist = false)
    private String productName;//商品名称
    @TableField(exist = false)
    private String storeName;//仓库名称
    @TableField(exist = false)
    private String startTime;//搜索起始时间
    @TableField(exist = false)
    private String endTime;//搜索结束时间

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
        BuyList other = (BuyList) that;
        return (this.getBuyId() == null ? other.getBuyId() == null : this.getBuyId().equals(other.getBuyId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getStoreId() == null ? other.getStoreId() == null : this.getStoreId().equals(other.getStoreId()))
            && (this.getBuyNum() == null ? other.getBuyNum() == null : this.getBuyNum().equals(other.getBuyNum()))
            && (this.getFactBuyNum() == null ? other.getFactBuyNum() == null : this.getFactBuyNum().equals(other.getFactBuyNum()))
            && (this.getBuyTime() == null ? other.getBuyTime() == null : this.getBuyTime().equals(other.getBuyTime()))
            && (this.getSupplyId() == null ? other.getSupplyId() == null : this.getSupplyId().equals(other.getSupplyId()))
            && (this.getPlaceId() == null ? other.getPlaceId() == null : this.getPlaceId().equals(other.getPlaceId()))
            && (this.getBuyUser() == null ? other.getBuyUser() == null : this.getBuyUser().equals(other.getBuyUser()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getIsIn() == null ? other.getIsIn() == null : this.getIsIn().equals(other.getIsIn()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBuyId() == null) ? 0 : getBuyId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getStoreId() == null) ? 0 : getStoreId().hashCode());
        result = prime * result + ((getBuyNum() == null) ? 0 : getBuyNum().hashCode());
        result = prime * result + ((getFactBuyNum() == null) ? 0 : getFactBuyNum().hashCode());
        result = prime * result + ((getBuyTime() == null) ? 0 : getBuyTime().hashCode());
        result = prime * result + ((getSupplyId() == null) ? 0 : getSupplyId().hashCode());
        result = prime * result + ((getPlaceId() == null) ? 0 : getPlaceId().hashCode());
        result = prime * result + ((getBuyUser() == null) ? 0 : getBuyUser().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getIsIn() == null) ? 0 : getIsIn().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", buyId=").append(buyId);
        sb.append(", productId=").append(productId);
        sb.append(", storeId=").append(storeId);
        sb.append(", buyNum=").append(buyNum);
        sb.append(", factBuyNum=").append(factBuyNum);
        sb.append(", buyTime=").append(buyTime);
        sb.append(", supplyId=").append(supplyId);
        sb.append(", placeId=").append(placeId);
        sb.append(", buyUser=").append(buyUser);
        sb.append(", phone=").append(phone);
        sb.append(", isIn=").append(isIn);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}