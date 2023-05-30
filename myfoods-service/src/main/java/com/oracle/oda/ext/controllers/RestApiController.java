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
import com.oracle.oda.ext.pojos.MlObj;
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
@RequestMapping("/api")
public class RestApiController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	private FoodsService foodsService;

	@RequestMapping(value = "/lsco", method = RequestMethod.GET)
	public ResponseEntity<List<CustomerOrder>> listCustomerOrders() {
		LOGGER.info("*** Got listCustomerOrders request");
		List<CustomerOrder> lsco = foodsService.listCustomerOrders();
		return ResponseEntity.status(HttpStatus.OK).body(lsco);
	}

	/*
	 * {
	 * "type": "FeatureCollection",
	 * "features": [{
	 * "type": "Feature",
	 * "properties": {
	 * "pk_col": "110",
	 * "pin_code": "247911",
	 * "store_address":
	 * "29 Tanglin Road, Lobby Floor, St. Regis Hotel, Singapore 247911",
	 * "store_name": "yellowstore - myfoods",
	 * "distance_m": "4.14413618104846"
	 * },
	 * "geometry": {
	 * "type": "Point",
	 * "coordinates": [
	 * 103.8854,
	 * 1.3428
	 * ]
	 * }
	 * },
	 * {
	 * "type": "Feature",
	 * "properties": {
	 * "pk_col": "4",
	 * "pin_code": "178882",
	 * "store_address":
	 * "2 Stamford Rd, Level 70, Swiss���� The Stamford, Singapore 178882",
	 * "store_name": "bluestore - myfoods",
	 * "distance_m": "4.50166962282964"
	 * },
	 * "geometry": {
	 * "type": "Point",
	 * "coordinates": [
	 * 103.8243,
	 * 1.3057
	 * ]
	 * }
	 * },
	 * {
	 * "type": "Feature",
	 * "properties": {
	 * "pk_col": "97",
	 * "pin_code": "228208",
	 * "store_address": "1 Scotts Road, #01-16 Shaw Centre, Singapore 228208",
	 * "store_name": "freshstore - myfoods",
	 * "distance_m": "4.5822083270557"
	 * },
	 * "geometry": {
	 * "type": "Point",
	 * "coordinates": [
	 * 103.8497,
	 * 1.3798
	 * ]
	 * }
	 * },
	 * {
	 * "type": "Feature",
	 * "properties": {
	 * "pk_col": "48",
	 * "pin_code": "88461",
	 * "store_address": "38 Tanjong Pagar Road, Singapore 088461",
	 * "store_name": "seastore - myfoods",
	 * "distance_m": "8.30889153738108"
	 * },
	 * "geometry": {
	 * "type": "Point",
	 * "coordinates": [
	 * 103.7755,
	 * 1.3545
	 * ]
	 * }
	 * },
	 * {
	 * "type": "Feature",
	 * "properties": {
	 * "pk_col": "53",
	 * "pin_code": "18972",
	 * "store_address":
	 * "2 Bayfront Avenue B1-71, Galleria Level The Shoppes at Marina Bay Sands, Singapore 018972"
	 * ,
	 * "store_name": "organicstore - myfoods",
	 * "distance_m": "10.6081090601211"
	 * },
	 * "geometry": {
	 * "type": "Point",
	 * "coordinates": [
	 * 103.7769,
	 * 1.4018
	 * ]
	 * }
	 * },
	 * {
	 * "type": "Feature",
	 * "properties": {
	 * "pk_col": "50",
	 * "pin_code": "18956",
	 * "store_address":
	 * "Level 2 Dining, L2-01 The Shoppes at Marina Bay Sands, 2 Bayfront Ave, Singapore 018956"
	 * ,
	 * "store_name": "onlinestore - myfoods",
	 * "distance_m": "12.593379722446"
	 * },
	 * "geometry": {
	 * "type": "Point",
	 * "coordinates": [
	 * 103.7549,
	 * 1.4025
	 * ]
	 * }
	 * }
	 * ]
	 * }
	 */
	@RequestMapping(value = "/listshops", method = RequestMethod.GET)
	public ResponseEntity<String> listshops(@RequestParam("long") String longtitude,
			@RequestParam("lat") String latitude) {
		LOGGER.info("*** Got listshops request: " + longtitude + "|" + latitude);
		GeoJson geo = foodsService.listshops(Float.parseFloat(longtitude), Float.parseFloat(latitude));
		return ResponseEntity.status(HttpStatus.OK).body(geo.getGeoJson());
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

	/*
	 * {
	 * "items": [{
	 * "rank": 1,
	 * "recommendation": "pasta",
	 * "num": 2,
	 * "support": 0.059,
	 * "confidence": 0.324,
	 * "lift": 0.951,
	 * "reverse_confidence": 0.172
	 * },
	 * {
	 * "rank": 2,
	 * "recommendation": "wine",
	 * "num": 2,
	 * "support": 0.044,
	 * "confidence": 0.242,
	 * "lift": 0.898,
	 * "reverse_confidence": 0.162
	 * }
	 * ],
	 * "hasMore": false,
	 * "limit": 25,
	 * "offset": 0,
	 * "count": 2,
	 * "links": [
	 * {
	 * "rel": "self",
	 * "href": "http://129.154.214.178:7001/ords/ws_shard/myfoods/ml?item=cherries"
	 * },
	 * {
	 * "rel": "describedby",
	 * "href":
	 * "http://129.154.214.178:7001/ords/ws_shard/metadata-catalog/myfoods/item"
	 * },
	 * {
	 * "rel": "first",
	 * "href": "http://129.154.214.178:7001/ords/ws_shard/myfoods/ml?item=cherries"
	 * }]
	 * }
	 */
	@RequestMapping(value = "/ml", method = RequestMethod.GET)
	public ResponseEntity<List<MlObj>> ml(@RequestParam("item") String item) {
		LOGGER.info("*** Got ml request: item = " + item);
		List<MlObj> ls = foodsService.ml(item);
		return ResponseEntity.status(HttpStatus.OK).body(ls);
	}
}
