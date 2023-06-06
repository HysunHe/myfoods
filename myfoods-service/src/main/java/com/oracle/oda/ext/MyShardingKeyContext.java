/***************************************************************************
 *
 * This document contains confidential and proprietary information 
 * subject to non-disclosure agreements with Hysun He. This information 
 * shall not be distributed or copied without written permission from 
 * Hysun He.
 *
 ***************************************************************************/

package com.oracle.oda.ext;

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
public class MyShardingKeyContext {
    private static ThreadLocal<String> context = new ThreadLocal<String>();

    public static ThreadLocal<String> getContext() {
        return context;
    }

    public static void setShardingKey(String countryCode) {
        context.set(countryCode);
    }

    public static String getShardingKey() {
        return context.get();
    }
}
