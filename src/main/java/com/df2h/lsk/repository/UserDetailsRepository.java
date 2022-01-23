package com.df2h.lsk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.df2h.lsk.model.UserDetails;


@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
	public String findUserRoleById(Long id);
	public UserDetails findByUserName(String userName);
}