/***************************************************************************
 *
 * This document contains confidential and proprietary information 
 * subject to non-disclosure agreements with Hysun He. This information 
 * shall not be distributed or copied without written permission from 
 * Hysun He.
 *
 ***************************************************************************/

package com.oracle.oda.ext.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oda.ext.dao.FoodsMapper;
import com.oracle.oda.ext.exceptions.ApplicationException;
import com.oracle.oda.ext.pojos.CustomerOrder;
import com.oracle.oda.ext.pojos.GeoJson;
import com.oracle.oda.ext.pojos.OnlineOrder;
import com.oracle.oda.ext.utils.DateUtil;
import com.oracle.oda.ext.utils.StringUtil;

/***************************************************************************
 * <PRE>
 *  Project Name    : bot-gateway-springboot
 * 
 *  Package Name    : com.oracle.oda.ext.services
 * 
 *  File Name       : TopicService.java
 * 
 *  Creation Date   : 2019年2月22日
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
public class FoodsService { 
    private static final Logger LOGGER = LoggerFactory.getLogger(FoodsService.class);

    @Autowired
    private FoodsMapper mapper;

    public List<GeoJson> list(float longitude, float latitude) {
        try {
            return mapper.list(longitude, latitude);
        } catch (Exception e) {
            LOGGER.error("!!! list failed: ", e);
            throw new ApplicationException(e);
        }
    }

    public void insertOnlineOrder(OnlineOrder o) throws ApplicationException {
        LOGGER.info("*** Inserting OnlineOrder: " + o);
        if(StringUtil.isBlank(o.getId())) {
			o.setId(StringUtil.uuid());
		}
		if(o.getDt() == null) {
			o.setDt(DateUtil.nowTs());
		}
        try {
            mapper.insertOnlineOrder(o);
        } catch (Exception e) {
            LOGGER.error("!!! Error saving OnlineOrder: " + o, e);
            throw new ApplicationException(e);
        }
    }

    public void insertCustomerOrder(CustomerOrder o) throws ApplicationException {
        LOGGER.info("*** Inserting CustomerOrder: " + o);
        try {
            mapper.insertCustomerOrder(o);
        } catch (Exception e) {
            LOGGER.error("!!! Error saving CustomerOrder: " + o, e);
            throw new ApplicationException(e);
        }
    }
}
