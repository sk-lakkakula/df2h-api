package com.df2h.lsk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.df2h.lsk.model.Administrator;


@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
	
}