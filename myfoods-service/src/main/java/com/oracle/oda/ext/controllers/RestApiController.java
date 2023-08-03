/***************************************************************************
 *
 * This document contains confidential and proprietary information 
 * subject to non-disclosure agreements with Hysun He. This information 
 * shall not be distributed or copied without written permission from 
 * Hysun He.
 *
 ***************************************************************************/

package com.oracle.oda.ext.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oda.ext.pojos.CustomerOrder;
import com.oracle.oda.ext.pojos.GeoJson;
import com.oracle.oda.ext.pojos.JsonResponse;
import com.oracle.oda.ext.pojos.MlObj;
import com.oracle.oda.ext.pojos.OnlineOrder;
import com.oracle.oda.ext.pojos.OrderReport;
import com.oracle.oda.ext.pojos.ProdOrderDetail;
import com.oracle.oda.ext.pojos.ProdStat;
import com.oracle.oda.ext.pojos.Product;
import com.oracle.oda.ext.services.FoodsService;
import com.oracle.oda.ext.services.FoodsService_GSM;
import com.oracle.oda.ext.utils.StringUtil;

/***************************************************************************
 * <PRE>
 *  Project Name    : bot-gateway
 * 
 *  Package Name    : com.oracle.oda.ext.controllers
 * 
 *  File Name       : RestApiController.java
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
@RestController
@RequestMapping("/api")
public class RestApiController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private FoodsService_GSM foodservice_gsm;

	private FoodsService getService(String key) {
		assert !StringUtil.isBlank(key);
		return (FoodsService) applicationContext.getBean("foodsService_" + key);
	}

	@RequestMapping(value = "/listshops", method = RequestMethod.GET)
	public ResponseEntity<JsonResponse> listShops(@RequestParam("long") String longtitude,
			@RequestParam("lat") String latitude, @RequestParam("loc") String loc) {
		LOGGER.info("*** Got listshops request: " + longtitude + "|" + latitude);
		List<GeoJson> shops = getService(loc).listshops(Float.parseFloat(longtitude),
				Float.parseFloat(latitude), loc);
		return JsonResponse.inst("OK", HttpStatus.OK, shops).toResponseEntity();
	}

	@RequestMapping(value = "/insertonlineorder", method = RequestMethod.POST)
	public ResponseEntity<JsonResponse> insertOnlineOrder(@RequestBody OnlineOrder o) {
		LOGGER.info("*** Got insertOnlineOrder request: " + o);
		getService(o.getCountryCode()).insertOnlineOrder(o);
		return JsonResponse.inst("OK", HttpStatus.OK, o).toResponseEntity();
	}

	@RequestMapping(value = "/insertcustomerorder", method = RequestMethod.PUT)
	public ResponseEntity<JsonResponse> insertCustomerOrder(@RequestBody CustomerOrder o) {
		LOGGER.info("*** Got insertCustomerOrder request: " + o);
		getService(o.getCountryCode()).insertCustomerOrder(o);
		return JsonResponse.inst("OK", HttpStatus.OK, o).toResponseEntity();
	}

	@RequestMapping(value = "/ml", method = RequestMethod.GET)
	public ResponseEntity<JsonResponse> ml(@RequestParam("item") String item,
			@RequestParam("loc") String loc) {
		LOGGER.info("*** Got ml request: item = " + item);
		List<MlObj> objs = getService(loc).ml(item, loc);
		return JsonResponse.inst("OK", HttpStatus.OK, objs).toResponseEntity();
	}

	@RequestMapping(value = "/listprods", method = RequestMethod.GET)
	public ResponseEntity<JsonResponse> listProds(@RequestParam("loc") String loc) {
		LOGGER.info("*** Got listProds request.");
		List<Product> prods = foodservice_gsm.listProducts(loc);
		return JsonResponse.inst("OK", HttpStatus.OK, prods).toResponseEntity();
	}

	@RequestMapping(value = "/insertprod", method = RequestMethod.POST)
	public ResponseEntity<JsonResponse> insertProduct(@RequestBody Product o) {
		LOGGER.info("*** Got insertProduct request: " + o);
		foodservice_gsm.insertProduct(o);
		return JsonResponse.inst("OK", HttpStatus.OK, o).toResponseEntity();
	}

	@RequestMapping(value = "/prod/insert", method = RequestMethod.GET)
	public ResponseEntity<JsonResponse> insertProductLite(@RequestParam("name") String name,
			@RequestParam("price") BigDecimal price) {
		Product o = new Product();
		o.setName(name);
		o.setPrice(price);
		LOGGER.info("*** Got insertProductLite request: " + o);
		foodservice_gsm.insertProduct(o);
		return JsonResponse.inst("OK", HttpStatus.OK, o).toResponseEntity();
	}

	@RequestMapping(value = "/ordersummary", method = RequestMethod.GET)
	public ResponseEntity<JsonResponse> getOrderSummary() {
		LOGGER.info("*** Got getOrderSummary request.");
		OrderReport summary = foodservice_gsm.listOrderSummary();
		return JsonResponse.inst("OK", HttpStatus.OK, summary).toResponseEntity();
	}

	@RequestMapping(value = "/rankprods", method = RequestMethod.GET)
	public ResponseEntity<JsonResponse> randProds(@RequestParam("loc") String loc) {
		LOGGER.info("*** Got randProds request.");
		List<ProdStat> prods = getService(loc).rankProds(loc);
		return JsonResponse.inst("OK", HttpStatus.OK, prods).toResponseEntity();
	}

	@RequestMapping(value = "/orderdetails", method = RequestMethod.GET)
	public ResponseEntity<JsonResponse> getOrderDetails(@RequestParam("loc") String loc,
			@RequestParam("prod") String prod) {
		LOGGER.info("*** Got getOrderDetails request.");
		List<ProdOrderDetail> prods = getService(loc).getOrderDetails(loc, prod);
		return JsonResponse.inst("OK", HttpStatus.OK, prods).toResponseEntity();
	}
}
