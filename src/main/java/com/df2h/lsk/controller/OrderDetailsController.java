package com.df2h.lsk.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.df2h.lsk.model.OrderDetails;
import com.df2h.lsk.pojo.OrderItem;
import com.df2h.lsk.service.ItemService;
import com.df2h.lsk.service.OrderDetailsService;
import com.df2h.lsk.util.CoreUtility;

@RestController
@CrossOrigin(origins = "http://localhost:9090")
/**
 * 
 * @author slakkakula
 *
 */

public class OrderDetailsController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	OrderDetailsService orderDetailsService;
	@Autowired
	ItemService itemService;
	
	@Autowired
	CoreUtility coreUtility;
	
	@RequestMapping("/orderdetails")
	public @ResponseBody JSONObject getAll() {
		LOGGER.info("Executing orderdetails in OrderDetailsController");
		JSONObject jsonObject = new JSONObject();
		List<OrderDetails> OrderDetailsList = orderDetailsService.fetchAll();
		if(OrderDetailsList !=null && OrderDetailsList.size()>0) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Fetched OrderDetails List  ");
			jsonObject .put("responseData", OrderDetailsList);
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "No Records to Fetch");
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}

	// Create a new OrderDetails
	@PostMapping("/orderdetails")
	public JSONObject createOrderDetails(@RequestBody com.df2h.lsk.pojo.OrderDetails orderDetails) {
		LOGGER.info("Executing createOrderDetails in OrderDetailsController");
		JSONObject jsonObject = new JSONObject();
		List<OrderItem> ordertemsList =null;
		if(orderDetails!=null) {
			System.err.println("orderDetails is NOT Null"+orderDetails);
			ordertemsList = orderDetails.getOrderItemList();
			LOGGER.info("orderDetails is NOT Null"+orderDetails);
			OrderDetails actualOrderDetails =new OrderDetails();
			actualOrderDetails .setOrderCost(orderDetails.getOrderCost());
			actualOrderDetails .setDeliveryDate(orderDetails.getDeliveryDate());
			actualOrderDetails .setOrderDate(coreUtility.getCurrentDate());
			actualOrderDetails .setOrderStatus("ORDER PLACED");
			actualOrderDetails .setConsumerId(orderDetails.getConsumerId());
			actualOrderDetails .setPaymentStatus(orderDetails.getPaymentStatus());
			actualOrderDetails .setPaymentType(orderDetails.getPaymentType());
			actualOrderDetails .setDeliveryDate(orderDetails.getDeliveryDate());
			for(int i =0;i<ordertemsList.size();i++) {
				OrderItem orderItemPojo = ordertemsList.get(i);
				com.df2h.lsk.model.OrderItem orderItemMOdel =null;
				if(orderItemPojo!=null) {
					orderItemMOdel =new com.df2h.lsk.model.OrderItem();
					orderItemMOdel .setName(orderItemPojo.getName());
					orderItemMOdel .setDescription(orderItemPojo.getDescription());
					orderItemMOdel .setQuantityOrdered(orderItemPojo.getQuantityOrdered());
					orderItemMOdel .setTotalCost(orderItemPojo.getTotalCost());
					orderItemMOdel .setUnitCost(orderItemPojo.getUnitCost());
				}
				actualOrderDetails.addOrderItem(orderItemMOdel );
			}
			
			OrderDetails temp = orderDetailsService.saveOrderDetails(actualOrderDetails);
			if(temp!=null ) {
				jsonObject .put("responseCode", "0000");
				jsonObject .put("responseMessage", "Successfully Created OrderDetails ");
				jsonObject .put("responseData", temp);
			}else {
				jsonObject .put("responseCode", "1000");
				jsonObject .put("responseMessage", "failed to Create OrderDetails ");
				jsonObject .put("responseData", null);
			}
		}else {
			LOGGER.info("orderDetails is Null");
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "failed to OrderDetails ");
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}

	// Get a Single OrderDetails
	@GetMapping("/orderdetails/{id}")
	public JSONObject getOrderDetailsById(@PathVariable(value = "id") Long OrderDetailsId) {
		LOGGER.info("Executing getOrderDetailsById in OrderDetailsController");
		JSONObject jsonObject = new JSONObject();
		OrderDetails temp = orderDetailsService.fetchById(OrderDetailsId);
		if(temp!=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Fetched OrderDetails by ID : "+OrderDetailsId);
			jsonObject .put("responseData", temp);
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "failed to Fetch OrderDetails by ID : "+OrderDetailsId);
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}

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

}