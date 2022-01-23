package com.df2h.lsk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.df2h.lsk.model.OrderDetails;
import com.df2h.lsk.model.OrderItem;


@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {
//List<OrderItem> findByOrderId(Long orderId);
}
