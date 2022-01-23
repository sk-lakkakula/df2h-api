package com.df2h.lsk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.df2h.lsk.model.Consumer;


@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
	Page<Consumer> findAll(Pageable pagebale);
	Page<Consumer> findByRegistrationStatus(Pageable pageable, String status);	
}