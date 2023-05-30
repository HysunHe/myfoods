/***************************************************************************
 *
 * This document contains confidential and proprietary information 
 * subject to non-disclosure agreements with Hysun He. This information 
 * shall not be distributed or copied without written permission from 
 * Hysun He.
 *
 ***************************************************************************/

package com.oracle.oda.ext.pojos;

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
public class GeoJson {
    private String mediaType;
    private String geoJson;

    public String getMediaType() {
        return mediaType;
    }

    public String getGeoJson() {
        return geoJson;
    } 

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setGeoJson(String geoJson) {
        this.geoJson = geoJson;
    }
}
