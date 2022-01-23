package com.df2h.lsk.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.df2h.lsk.exception.ResourceNotFoundException;
import com.df2h.lsk.model.OrderDetails;
import com.df2h.lsk.model.Item;
import com.df2h.lsk.repository.OrderDetailsRepository;

@Service("orderDetailsService")
public class OrderDetailsService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	OrderDetailsRepository orderDetailsRepository;

	public List<OrderDetails> fetchAll(){
		LOGGER.info("Exec fetchAll from orderDetailsService");
		return orderDetailsRepository.findAll();
	}

	public OrderDetails fetchById(Long orderDetailsId){
		LOGGER.info("Exec fetchById from orderDetailsService");
		OrderDetails orderDetails =null;
		try {
			orderDetails = orderDetailsRepository.findById(orderDetailsId).get();
			LOGGER.info("Exec fetchById from orderDetailsService  , orderDetailsId: "+orderDetailsId);
		}catch(Exception e ) {
			LOGGER.error("Exec fetchById from orderDetailsService : ERROR  --> "+e);
			throw new ResourceNotFoundException("orderDetails", "id", orderDetailsId);
		}
		return orderDetails;
	}
	
	public List<OrderDetails> fetchByConsumerId(Long consumerId){
		LOGGER.info("Exec fetchByConsumerId from orderDetailsService");
		List<OrderDetails> orderDetailsList =null;
		try {
			orderDetailsList = orderDetailsRepository.findByConsumerId(consumerId);
			LOGGER.info("Exec fetchByConsumerId from orderDetailsService  , consumerId: "+consumerId);
		}catch(Exception e ) {
			LOGGER.error("Exec fetchByConsumerId from orderDetailsService : ERROR  --> "+e);
			throw new ResourceNotFoundException("orderDetails", "id", consumerId);
		}
		return orderDetailsList;
	}
	public OrderDetails saveOrderDetails(OrderDetails orderDetails){
		LOGGER.info("Exec saveOrderDetails from orderDetailsService orderDetails : "+orderDetails);
		System.err.println("Exec saveOrderDetails from orderDetailsService orderDetails : "+orderDetails);
		OrderDetails newOrderDetails = orderDetailsRepository.save(orderDetails);
		LOGGER.info("Executed saveOrderDetails from orderDetailsService orderDetails : "+newOrderDetails);
		return newOrderDetails ;
	}

	/*	public OrderDetails updateOrderDetails(final Long orderDetailsId) {
		LOGGER.info("Exec updateOrderDetails from orderDetailsService orderDetailsId :"+orderDetailsId);
		OrderDetails ctg =null;
		try {
			ctg = orderDetailsRepository.findById(orderDetailsId).get();
		 LOGGER.info("Exec updateOrderDetails from orderDetailsService  , orderDetailsId: "+orderDetailsId);
		}catch(Exception e ) {
			LOGGER.error("Exec updateOrderDetails from orderDetailsService : ERROR  --> "+e);
			throw new ResourceNotFoundException("orderDetails", "id", orderDetailsId);
		}
		return orderDetailsRepository.save(ctg);
	}
	 */	
	public OrderDetails update(final Long orderDetailsId,OrderDetails  orderDetails) {
		LOGGER.info("Exec updateOrderDetails    - OrderDetails   Service");
		OrderDetails  prevOrderDetails  ,updatedOrderDetails  =null;
		try {
			prevOrderDetails = orderDetailsRepository.findById(orderDetailsId).get();
			if(prevOrderDetails!=null){
				LOGGER.info("Exec OrderDetails Service  , OrderDetails Id: "+orderDetailsId+"  , prevOrderDetails  : "+prevOrderDetails);
				prevOrderDetails.setOrderCost(orderDetails.getOrderCost());
				prevOrderDetails.setOrderDate(orderDetails.getOrderDate());
				prevOrderDetails.setDeliveryDate(orderDetails.getDeliveryDate());
				prevOrderDetails.setOrderStatus(orderDetails.getOrderStatus());
				prevOrderDetails.setPaymentType(orderDetails.getPaymentType());
				prevOrderDetails.setOrderItems(orderDetails.getOrderItems());
			}else 
				throw new ResourceNotFoundException("OrderDetails", "id", orderDetailsId);
			updatedOrderDetails= orderDetailsRepository.save(prevOrderDetails);
			LOGGER.info(" updatedOrderDetails : "+updatedOrderDetails);
		}
		catch (NoSuchElementException e) {
			throw e;
		}
		catch (Exception e) {
			throw e;
		}
		return updatedOrderDetails;
	}


	public ResponseEntity<?> deleteOrderDetails(Long orderDetailsId){
		LOGGER.info("Exec deleteOrderDetails from orderDetailsService");
		OrderDetails orderDetails =null;
		try {
			orderDetails = orderDetailsRepository.findById(orderDetailsId).get();
			LOGGER.info("Exec updateorderDetails from orderDetailsService  , orderDetailsId: "+orderDetailsId);
		}catch(Exception e ) {
			LOGGER.error("Exec deleteOrderDetails from orderDetailsService : ERROR  --> "+e);
			throw new ResourceNotFoundException("orderDetails", "id", orderDetailsId);
		}

		orderDetailsRepository.delete(orderDetails);
		return ResponseEntity.ok().build();
	}

	
}
