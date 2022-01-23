package com.df2h.lsk.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.df2h.lsk.model.OrderItem;
import com.df2h.lsk.service.OrderItemsService;
import com.df2h.lsk.util.CoreUtility;

@RestController
@CrossOrigin(origins = "http://localhost:9090")
/**
 * 
 * @author slakkakula
 *
 */

public class OrderItemsController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	OrderItemsService orderItemsService;
	
	
	@Autowired
	CoreUtility coreUtility;
	
	@RequestMapping("/orderitems")
	public @ResponseBody JSONObject getAll() {
		LOGGER.info("Executing orderdetails in OrderDetailsController");
		JSONObject jsonObject = new JSONObject();
		return jsonObject ;
	}

	
	// Get a Single OrderDetails by Order id
	@GetMapping("/orderitems/{orderId}")
	public JSONObject getOrderItemsByOrderId(@PathVariable(value = "orderId") Long orderId) {
		LOGGER.info("Executing getOrderItemsById in OrderItemsController");
		JSONObject jsonObject = new JSONObject();
		List<OrderItem> orderItemsList = orderItemsService.fetchByOrderDetailsId(orderId);
		if(orderItemsList !=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Fetched OrderDetails by ID : "+orderId);
			jsonObject .put("responseData", orderItemsList );
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "failed to Fetch OrderDetails by ID : "+orderId);
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}
/*
	// Get a Single OrderDetails
		@GetMapping("/orderdetails/consumer/{id}")
		public JSONObject getOrderDetailsByConsumerId(@PathVariable(value = "id") Long consumerId) {
			LOGGER.info("Executing getOrderDetailsById in OrderDetailsController");
			JSONObject jsonObject = new JSONObject();
			List<OrderDetails> temp = orderDetailsService.fetchByConsumerId(consumerId);
			if(temp!=null ) {
				jsonObject .put("responseCode", "0000");
				jsonObject .put("responseMessage", "Successfully Fetched OrderDetails by ID : "+consumerId);
				jsonObject .put("responseData", temp);
			}else {
				jsonObject .put("responseCode", "1000");
				jsonObject .put("responseMessage", "failed to Fetch OrderDetails by ID : "+consumerId);
				jsonObject .put("responseData", null);
			}
			return jsonObject ;
		}

	@PutMapping("/orderdetails/{id}")
	public JSONObject update(@PathVariable(value = "id") Long id, @RequestBody OrderDetails OrderDetails) {
		LOGGER.info("Executing update in OrderDetails");
		JSONObject jsonObject = new JSONObject();
		OrderDetails temp =null;
		try{
			temp = orderDetailsService.update(id,OrderDetails);
			if(temp!=null ) {
				jsonObject .put("responseCode", "0000");
				jsonObject .put("responseMessage", "Successfully Uppdated OrderDetails  by ID: "+id);
				jsonObject .put("responseData", temp);
			}else {
				jsonObject .put("responseCode", "1000");
				jsonObject .put("responseMessage", "Failed to Update OrderDetails by ID: "+id);
				jsonObject .put("responseData", null);
			}
		}
		catch (NoSuchElementException e) {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Update OrderDetails there No Record with id :"+id);
			jsonObject .put("responseData", e.getMessage());
		}
		catch (Exception e) {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Update OrderDetails ");
			jsonObject .put("responseData", e.getMessage());
		}
		return jsonObject ;
	}

	// Delete a OrderDetails
	@DeleteMapping("/orderdetails/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteOrderDetails(@PathVariable(value = "id") Long OrderDetailsId) {
		LOGGER.info("Executing deleteOrderDetails in OrderDetailsController");
		return orderDetailsService.deleteOrderDetails(OrderDetailsId);
	}
*/
}