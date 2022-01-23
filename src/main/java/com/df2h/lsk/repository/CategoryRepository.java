package com.df2h.lsk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.df2h.lsk.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	public Category findByName(String name);
}