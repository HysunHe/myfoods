/***************************************************************************
 *
 * This document contains confidential and proprietary information 
 * subject to non-disclosure agreements with Hysun He. This information 
 * shall not be distributed or copied without written permission from 
 * Hysun He.
 *
 ***************************************************************************/

package com.oracle.oda.ext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(MyShardingKeyContext.class);

    private static ThreadLocal<String> context = new ThreadLocal<String>();

    public static ThreadLocal<String> getContext() {
        return context;
    }

    public static void setShardingKey(String countryCode) {
        LOGGER.info("*** Set sharding key: " + countryCode);
        context.set(countryCode);
    }

    public static String getShardingKey() {
        String key = context.get();
        LOGGER.info("*** Got sharding key: " + key);
        return key;
    }
}
