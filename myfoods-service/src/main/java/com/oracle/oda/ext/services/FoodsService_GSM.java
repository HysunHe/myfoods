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
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.oracle.oda.ext.dao.FoodsMapper;
import com.oracle.oda.ext.exceptions.ApplicationException;
import com.oracle.oda.ext.pojos.OrderReport;
import com.oracle.oda.ext.pojos.Product;

/***************************************************************************
 * <PRE>
 *  Project Name    : MyFood-Service
 * 
 *  Package Name    : com.oracle.oda.ext.services
 * 
 *  File Name       : FoodsService_SG.java
 * 
 *  Creation Date   : 2023年6月14日
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
@DS("ds_shard_gsm")
public class FoodsService_GSM {
  protected final Logger LOGGER = LoggerFactory.getLogger(FoodsService_GSM.class);

  @Autowired
  protected FoodsMapper mapper;

  public List<Product> listProducts(String countryCode) {
    // MyShardingKeyContext.setShardingKey(countryCode);
    try {
      return mapper.listProducts();
    } catch (Exception e) {
      LOGGER.error("!!! listProducts failed: ", e);
      throw new ApplicationException(e);
    }
  }

  public void insertProduct(Product o) throws ApplicationException {
    LOGGER.info("*** Inserting Product: " + o);
    // MyShardingKeyContext.setShardingKey(o.getCountryCode());
    try {
      mapper.insertProduct(o);
    } catch (Exception e) {
      LOGGER.error("!!! Error saving Product: " + o, e);
      throw new ApplicationException(e);
    }
  }

  public OrderReport listOrderSummary() throws ApplicationException {
    LOGGER.info("*** listOrderSummary");
    List<OrderReport> dataset = mapper.listOrderSummary();
    final OrderReport result = new OrderReport();
    result.setCat("ALL");
    result.setCount(0);
    result.setItems(dataset);
    dataset.forEach(new Consumer<OrderReport>() {
      @Override
      public void accept(OrderReport t) {
        int total = result.getCount();
        total += t.getCount();
        result.setCount(total);
      }
    });
    return result;
  }
}
