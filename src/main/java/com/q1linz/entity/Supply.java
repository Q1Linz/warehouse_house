package com.q1linz.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 供货商
 * @TableName supply
 */
@Data
@TableName("supply")
public class Supply implements Serializable {
    /**
     * 
     */
    @TableId("supply_id")
    private Integer supplyId;

    /**
     * 
     */
    private String supplyNum;

    /**
     * 
     */
    private String supplyName;

    /**
     * 
     */
    private String supplyIntroduce;

    /**
     * 
     */
    private String concat;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private String address;

    /**
     * 0:可用  1:不可用
     */
    private String isDelete;

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
        Supply other = (Supply) that;
        return (this.getSupplyId() == null ? other.getSupplyId() == null : this.getSupplyId().equals(other.getSupplyId()))
            && (this.getSupplyNum() == null ? other.getSupplyNum() == null : this.getSupplyNum().equals(other.getSupplyNum()))
            && (this.getSupplyName() == null ? other.getSupplyName() == null : this.getSupplyName().equals(other.getSupplyName()))
            && (this.getSupplyIntroduce() == null ? other.getSupplyIntroduce() == null : this.getSupplyIntroduce().equals(other.getSupplyIntroduce()))
            && (this.getConcat() == null ? other.getConcat() == null : this.getConcat().equals(other.getConcat()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSupplyId() == null) ? 0 : getSupplyId().hashCode());
        result = prime * result + ((getSupplyNum() == null) ? 0 : getSupplyNum().hashCode());
        result = prime * result + ((getSupplyName() == null) ? 0 : getSupplyName().hashCode());
        result = prime * result + ((getSupplyIntroduce() == null) ? 0 : getSupplyIntroduce().hashCode());
        result = prime * result + ((getConcat() == null) ? 0 : getConcat().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", supplyId=").append(supplyId);
        sb.append(", supplyNum=").append(supplyNum);
        sb.append(", supplyName=").append(supplyName);
        sb.append(", supplyIntroduce=").append(supplyIntroduce);
        sb.append(", concat=").append(concat);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}