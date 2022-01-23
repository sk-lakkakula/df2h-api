package com.df2h.lsk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.df2h.lsk.model.Supplier;


@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	Page<Supplier> findAll(Pageable pageable);
	Page<Supplier> findByUserRegistrationStatus(Pageable pageable,String userRegistrationStatus);
	//Page<Supplier> findById(Pageable pageable,Long id);
}