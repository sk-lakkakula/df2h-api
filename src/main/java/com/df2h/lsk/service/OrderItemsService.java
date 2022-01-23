package com.df2h.lsk.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df2h.lsk.exception.ResourceNotFoundException;
import com.df2h.lsk.model.OrderItem;
import com.df2h.lsk.repository.OrderItemsRepository;

@Service("orderItemsService")
public class OrderItemsService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	OrderItemsRepository orderItemsRepository;


	public List<OrderItem>fetchByOrderDetailsId(Long orderDetailsId){
		LOGGER.info("Exec fetchByOrderDetailsId from orderDetailsService");
		List<OrderItem> orderItemsList =null;
		List<OrderItem> filteredOrderItemsList =new ArrayList<OrderItem>();
		try {
			orderItemsList = orderItemsRepository.findAll();
			for (Iterator iterator = orderItemsList.iterator(); iterator.hasNext();) {
				OrderItem orderItem = (OrderItem) iterator.next();
				if(orderDetailsId==orderItem.getOrderDetails().getId().longValue())
				{
					filteredOrderItemsList.add(orderItem);
				}
			}
			LOGGER.info("Exec fetchByOrderDetailsId from orderDetailsService  , orderDetailsId: "+orderDetailsId);
		}catch(Exception e ) {
			LOGGER.error("Exec fetchById from orderDetailsService : ERROR  --> "+e);
			throw new ResourceNotFoundException("orderDetails", "id", orderDetailsId);
		}
		return filteredOrderItemsList;
	}
		
}
