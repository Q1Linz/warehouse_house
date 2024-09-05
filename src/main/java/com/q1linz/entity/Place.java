package com.q1linz.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 产地
 * @TableName place
 */
@Data
@TableName("place")
public class Place implements Serializable {
    /**
     * 
     */
    @TableId(value = "place_id",type = IdType.AUTO)
    private Integer placeId;

    /**
     * 
     */
    private String placeName;

    /**
     * 
     */
    private String placeNum;

    /**
     * 
     */
    private String introduce;

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
        Place other = (Place) that;
        return (this.getPlaceId() == null ? other.getPlaceId() == null : this.getPlaceId().equals(other.getPlaceId()))
            && (this.getPlaceName() == null ? other.getPlaceName() == null : this.getPlaceName().equals(other.getPlaceName()))
            && (this.getPlaceNum() == null ? other.getPlaceNum() == null : this.getPlaceNum().equals(other.getPlaceNum()))
            && (this.getIntroduce() == null ? other.getIntroduce() == null : this.getIntroduce().equals(other.getIntroduce()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPlaceId() == null) ? 0 : getPlaceId().hashCode());
        result = prime * result + ((getPlaceName() == null) ? 0 : getPlaceName().hashCode());
        result = prime * result + ((getPlaceNum() == null) ? 0 : getPlaceNum().hashCode());
        result = prime * result + ((getIntroduce() == null) ? 0 : getIntroduce().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", placeId=").append(placeId);
        sb.append(", placeName=").append(placeName);
        sb.append(", placeNum=").append(placeNum);
        sb.append(", introduce=").append(introduce);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}