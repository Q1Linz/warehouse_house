package com.q1linz.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 出库单
 * @TableName out_store
 */
@Data
@TableName("out_store")
public class OutStore implements Serializable {
    /**
     * 
     */
    @TableId(value = "outs_id",type = IdType.AUTO)
    private Integer outsId;

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
    private Integer tallyId;

    /**
     * 
     */
    private BigDecimal outPrice;

    /**
     * 
     */
    private Integer outNum;

    /**
     * 
     */
    private Integer createBy;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 0 否 1 是
     */
    private String isOut;


    //------------------追加的属性-------------------------
    @TableField(exist = false)
    private String productName;//商品名称
    @TableField(exist = false)
    private String startTime;//起始时间
    @TableField(exist = false)
    private String endTime;//结束时间
    @TableField(exist = false)
    private String storeName;//仓库名称
    @TableField(exist = false)
    private String userCode;//创建出库单的用户的名称

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
        OutStore other = (OutStore) that;
        return (this.getOutsId() == null ? other.getOutsId() == null : this.getOutsId().equals(other.getOutsId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getStoreId() == null ? other.getStoreId() == null : this.getStoreId().equals(other.getStoreId()))
            && (this.getTallyId() == null ? other.getTallyId() == null : this.getTallyId().equals(other.getTallyId()))
            && (this.getOutPrice() == null ? other.getOutPrice() == null : this.getOutPrice().equals(other.getOutPrice()))
            && (this.getOutNum() == null ? other.getOutNum() == null : this.getOutNum().equals(other.getOutNum()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIsOut() == null ? other.getIsOut() == null : this.getIsOut().equals(other.getIsOut()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOutsId() == null) ? 0 : getOutsId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getStoreId() == null) ? 0 : getStoreId().hashCode());
        result = prime * result + ((getTallyId() == null) ? 0 : getTallyId().hashCode());
        result = prime * result + ((getOutPrice() == null) ? 0 : getOutPrice().hashCode());
        result = prime * result + ((getOutNum() == null) ? 0 : getOutNum().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIsOut() == null) ? 0 : getIsOut().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", outsId=").append(outsId);
        sb.append(", productId=").append(productId);
        sb.append(", storeId=").append(storeId);
        sb.append(", tallyId=").append(tallyId);
        sb.append(", outPrice=").append(outPrice);
        sb.append(", outNum=").append(outNum);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", isOut=").append(isOut);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}