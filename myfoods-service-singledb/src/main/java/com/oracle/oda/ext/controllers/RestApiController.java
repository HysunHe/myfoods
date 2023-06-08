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
import com.oracle.oda.ext.pojos.Product;
import com.oracle.oda.ext.services.FoodsService;

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
	private FoodsService foodsService;

	@RequestMapping(value = "/listshops", method = RequestMethod.GET)
	public ResponseEntity<JsonResponse> listShops(@RequestParam("long") String longtitude,
			@RequestParam("lat") String latitude, @RequestParam("loc") String loc) {
		LOGGER.info("*** Got listshops request: " + longtitude + "|" + latitude);
		List<GeoJson> shops = foodsService.listshops(Float.parseFloat(longtitude),
				Float.parseFloat(latitude), loc);
		return JsonResponse.inst("OK", HttpStatus.OK, shops).toResponseEntity();
	}

	@RequestMapping(value = "/insertonlineorder", method = RequestMethod.POST)
	public ResponseEntity<JsonResponse> insertOnlineOrder(@RequestBody OnlineOrder o) {
		LOGGER.info("*** Got insertOnlineOrder request: " + o);
		foodsService.insertOnlineOrder(o);
		return JsonResponse.inst("OK", HttpStatus.OK, o).toResponseEntity();
	}

	@RequestMapping(value = "/insertcustomerorder", method = RequestMethod.PUT)
	public ResponseEntity<JsonResponse> insertCustomerOrder(@RequestBody CustomerOrder o) {
		LOGGER.info("*** Got insertCustomerOrder request: " + o);
		foodsService.insertCustomerOrder(o);
		return JsonResponse.inst("OK", HttpStatus.OK, o).toResponseEntity();
	}

	@RequestMapping(value = "/ml", method = RequestMethod.GET)
	public ResponseEntity<JsonResponse> ml(@RequestParam("item") String item,
			@RequestParam("loc") String loc) {
		LOGGER.info("*** Got ml request: item = " + item);
		List<MlObj> objs = foodsService.ml(item, loc);
		return JsonResponse.inst("OK", HttpStatus.OK, objs).toResponseEntity();
	}

	@RequestMapping(value = "/listprods", method = RequestMethod.GET)
	public ResponseEntity<JsonResponse> listProds(@RequestParam("loc") String loc) {
		LOGGER.info("*** Got listProds request.");
		List<Product> prods = foodsService.listProducts(loc);
		return JsonResponse.inst("OK", HttpStatus.OK, prods).toResponseEntity();
	}

	@RequestMapping(value = "/insertprod", method = RequestMethod.POST)
	public ResponseEntity<JsonResponse> insertProduct(@RequestBody Product o) {
		LOGGER.info("*** Got insertProduct request: " + o);
		foodsService.insertProduct(o);
		return JsonResponse.inst("OK", HttpStatus.OK, o).toResponseEntity();
	}

	@RequestMapping(value = "/prod/insert", method = RequestMethod.GET)
	public ResponseEntity<JsonResponse> insertProductLite(@RequestParam("name") String name,
			@RequestParam("price") BigDecimal price) {
		Product o = new Product();
		o.setName(name);
		o.setPrice(price);
		LOGGER.info("*** Got insertProductLite request: " + o);
		foodsService.insertProduct(o);
		return JsonResponse.inst("OK", HttpStatus.OK, o).toResponseEntity();
	}
}
