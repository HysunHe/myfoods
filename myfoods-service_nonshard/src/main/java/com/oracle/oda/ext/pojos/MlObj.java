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
public class MlObj {
    private Integer rank;
    private String recommendation;
    private Integer num;
    private BigDecimal support;
    private BigDecimal confidence;
    private BigDecimal lift;
    private BigDecimal reverseConfidence;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getSupport() {
        return support;
    }

    public void setSupport(BigDecimal support) {
        this.support = support;
    }

    public BigDecimal getConfidence() {
        return confidence;
    }

    public void setConfidence(BigDecimal confidence) {
        this.confidence = confidence;
    }

    public BigDecimal getLift() {
        return lift;
    }

    public void setLift(BigDecimal lift) {
        this.lift = lift;
    }

    public BigDecimal getReverseConfidence() {
        return reverseConfidence;
    }

    public void setReverseConfidence(BigDecimal reverseConfidence) {
        this.reverseConfidence = reverseConfidence;
    }

    @Override
    public String toString() {
        return "MlObj [rank=" + rank + ", recommendation=" + recommendation + ", num=" + num + ", support=" + support
                + ", confidence=" + confidence + ", lift=" + lift + ", reverseConfidence=" + reverseConfidence + "]";
    }

}
