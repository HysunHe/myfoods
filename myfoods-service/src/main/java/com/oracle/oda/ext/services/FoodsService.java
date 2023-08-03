/***************************************************************************
 *
 * This document contains confidential and proprietary information 
 * subject to non-disclosure agreements with Hysun He. This information 
 * shall not be distributed or copied without written permission from 
 * Hysun He.
 *
 ***************************************************************************/

package com.oracle.oda.ext.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oda.ext.MyShardingKeyContext;
import com.oracle.oda.ext.dao.FoodsMapper;
import com.oracle.oda.ext.exceptions.ApplicationException;
import com.oracle.oda.ext.pojos.CustomerOrder;
import com.oracle.oda.ext.pojos.GeoJson;
import com.oracle.oda.ext.pojos.MlObj;
import com.oracle.oda.ext.pojos.OnlineOrder;
import com.oracle.oda.ext.pojos.ProdOrderDetail;
import com.oracle.oda.ext.pojos.ProdStat;
import com.oracle.oda.ext.utils.DateUtil;

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
public class FoodsService {
    protected final Logger LOGGER = getLogger();

    @Autowired
    protected FoodsMapper mapper;

    protected Logger getLogger() {
        return LoggerFactory.getLogger(FoodsService.class);
    }

    public List<GeoJson> listshops(float longitude, float latitude, String loc) {
        MyShardingKeyContext.setShardingKey(loc);
        try {
            return mapper.getShops(longitude, latitude, loc);
        } catch (Exception e) {
            LOGGER.error("!!! list failed: ", e);
            throw new ApplicationException(e);
        }
    }

    public void insertOnlineOrder(OnlineOrder o) throws ApplicationException {
        LOGGER.info("*** Inserting OnlineOrder: " + o);
        MyShardingKeyContext.setShardingKey(o.getCountryCode());
        if (o.getDt() == null) {
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
        MyShardingKeyContext.setShardingKey(o.getCountryCode());
        try {
            mapper.insertCustomerOrder(o);
        } catch (Exception e) {
            LOGGER.error("!!! Error saving CustomerOrder: " + o, e);
            throw new ApplicationException(e);
        }
    }

    public List<MlObj> ml(String item, String countryCode) {
        MyShardingKeyContext.setShardingKey(countryCode);
        try {
            return new ArrayList<MlObj>();
        } catch (Exception e) {
            LOGGER.error("!!! ml failed: ", e);
            throw new ApplicationException(e);
        }
    }

    public List<ProdStat> rankProds(String countryCode) {
        MyShardingKeyContext.setShardingKey(countryCode);
        try {
            return mapper.rankProds();
        } catch (Exception e) {
            LOGGER.error("!!! rankProds failed: ", e);
            throw new ApplicationException(e);
        }
    }

    public List<ProdOrderDetail> getOrderDetails(String countryCode, String prod) {
        MyShardingKeyContext.setShardingKey(countryCode);
        try {
            return mapper.getOrderDetails(prod);
        } catch (Exception e) {
            LOGGER.error("!!! getOrderDetails failed: " + prod, e);
            throw new ApplicationException(e);
        }
    }
}
