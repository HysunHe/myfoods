/***************************************************************************
 *
 * This document contains confidential and proprietary information 
 * subject to non-disclosure agreements with Hysun He. This information 
 * shall not be distributed or copied without written permission from 
 * Hysun He.
 *
 ***************************************************************************/

package com.oracle.oda.ext.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.oracle.oda.ext.pojos.CustomerOrder;
import com.oracle.oda.ext.pojos.GeoJson;
import com.oracle.oda.ext.pojos.MlObj;
import com.oracle.oda.ext.pojos.OnlineOrder;
import com.oracle.oda.ext.pojos.OrderReport;
import com.oracle.oda.ext.pojos.ProdOrderDetail;
import com.oracle.oda.ext.pojos.ProdStat;
import com.oracle.oda.ext.pojos.Product;

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
@Mapper
public interface FoodsMapper {
  List<GeoJson> getShops(@Param("longitude") float longitude, @Param("latitude") float latitude,
      @Param("location") String location);

  void insertOnlineOrder(OnlineOrder o);

  void insertCustomerOrder(CustomerOrder o);

  List<MlObj> ml(@Param("item") String item);

  List<Product> listProducts();

  void insertProduct(Product o);

  List<OrderReport> listOrderSummary();

  List<ProdStat> rankProds();

  List<ProdOrderDetail> getOrderDetails(@Param("prod") String prod);
}
