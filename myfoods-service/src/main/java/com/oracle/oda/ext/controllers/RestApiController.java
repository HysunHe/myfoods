/***************************************************************************
 *
 * This document contains confidential and proprietary information 
 * subject to non-disclosure agreements with Hysun He. This information 
 * shall not be distributed or copied without written permission from 
 * Hysun He.
 *
 ***************************************************************************/

package com.oracle.oda.ext.controllers;

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
import com.oracle.oda.ext.pojos.OnlineOrder;
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
@RequestMapping("/bot")
public class RestApiController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	private FoodsService foodsService;

	@RequestMapping(value = "/listshops", method = RequestMethod.GET)
	public ResponseEntity<List<GeoJson>> getProperty(@RequestParam("long") String longtitude,
			@RequestParam("lat") String latitude) {
		LOGGER.info("*** Got listshops request: " + longtitude + "|" + latitude);
		List<GeoJson> ls = foodsService.list(Float.parseFloat(longtitude), Float.parseFloat(latitude));
		return ResponseEntity.status(HttpStatus.OK).body(ls);
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

}
