package com.df2h.lsk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.df2h.lsk.model.Farmer;


@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {
	Page<Farmer>findAll(Pageable pageable );
	Page<Farmer> findByRegistrationStatus(Pageable pageable,String registrationStatus);
}