/***************************************************************************
 *
 * This document contains confidential and proprietary information 
 * subject to non-disclosure agreements with Hysun He. This information 
 * shall not be distributed or copied without written permission from 
 * Hysun He.
 *
 ***************************************************************************/

package com.oracle.oda.ext.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baomidou.dynamic.datasource.annotation.DS;

/***************************************************************************
 * <PRE>
 *  Project Name    : MyFood-Service
 * 
 *  Package Name    : com.oracle.oda.ext.services
 * 
 *  File Name       : FoodsService_AU.java
 * 
 *  Creation Date   : 2023年6月8日
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
@Service
@DS("ds_shard_au")
public class FoodsService_AU extends FoodsService {
    @Override
    protected Logger getLogger() {
        return LoggerFactory.getLogger(FoodsService_AU.class);
    }
}
