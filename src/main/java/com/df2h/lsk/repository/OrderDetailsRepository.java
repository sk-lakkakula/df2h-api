package com.df2h.lsk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.df2h.lsk.model.OrderDetails;


@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
List<OrderDetails> findByConsumerId(Long consumerId);
}
