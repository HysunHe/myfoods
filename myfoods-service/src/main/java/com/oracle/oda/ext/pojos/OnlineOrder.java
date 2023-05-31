/***************************************************************************
 *
 * This document contains confidential and proprietary information 
 * subject to non-disclosure agreements with Hysun He. This information 
 * shall not be distributed or copied without written permission from 
 * Hysun He.
 *
 ***************************************************************************/

package com.oracle.oda.ext.pojos;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.oracle.oda.ext.utils.StringUtil;

/***************************************************************************
 * <PRE>
 *  Project Name    : bot-gateway-springboot
 * 
 *  Package Name    : com.oracle.oda.ext.dao
 * 
 *  File Name       : FoodsMapper.java 
 * 
 *  Creation Date   : 2023年5月30日
 * 
 *  Author          : hysun
 * 
 *  Purpose         : TODO
 * 
 * 
 *  History         : TODO
 * 
 * </PRE>
 ***************************************************************************/
public class OnlineOrder {
    private String countryCode;
    private Timestamp dt;
    private String orderDateTime;
    private String lineId;
    private Integer quantity;
    private String itemId;
    private BigDecimal amount;
    private String orderId;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public Timestamp getDt() {
        return dt;
    }

    public void setDt(Timestamp dt) {
        this.dt = dt;
        if (StringUtil.isBlank(orderDateTime) && dt != null) {
            this.orderDateTime = String.valueOf(dt.getTime());
        }
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
        if (this.dt == null && !StringUtil.isBlank(orderDateTime)) {
            Timestamp ts = new Timestamp(Long.valueOf(orderDateTime));
            this.dt = ts;
        }
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "OnlineOrder [countryCode=" + countryCode + ", dt=" + dt + ", lineId=" + lineId + ", quantity="
                + quantity + ", itemId=" + itemId + ", amount=" + amount + ", orderId=" + orderId + ", latitude="
                + latitude + ", longitude=" + longitude + "]";
    }

}
