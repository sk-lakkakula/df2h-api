package com.df2h.lsk.service;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.df2h.lsk.exception.ResourceNotFoundException;
import com.df2h.lsk.model.Administrator;
import com.df2h.lsk.model.UserDetails;
import com.df2h.lsk.pojo.UserBean;
import com.df2h.lsk.repository.UserDetailsRepository;

@Service("userDetialsService")
public class UserDetailsService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	UserDetailsRepository userDetailsRepository;

	public UserDetails fetchUserDetailsById(Long id){
		UserDetails userDetails =null;
		try {
			userDetails = userDetailsRepository.findById(id).get();
		}catch(Exception e ) {
			LOGGER.info("Unable to fetch the userdetails by Id : "+id);
		}
		return userDetails;
	}

	public UserDetails saveUserDetails(UserDetails userDetails){
		System.err.println("Exec saveUserDetails () userDetails :--> "+userDetails);
		UserDetails tempUserDetails =null;
		try{
			tempUserDetails  = userDetailsRepository.save(userDetails);
			if(tempUserDetails  !=null){

			}else
				LOGGER.info("Could nt save the User Details.!");
		}catch (Exception e) {
			throw e;
		}
		return tempUserDetails;
	}

	public UserDetails updateUserDetails(final Long id) {
		UserDetails userDetails =null;
		try {
			userDetails = userDetailsRepository.findById(id).get();
		}catch(Exception e ) {
			LOGGER.info("Unable to Update  the userdetails by Id : "+id);
			throw new ResourceNotFoundException("userDetails", "id", id);
		}
		userDetails.setRole(userDetails.getRole());
		return userDetailsRepository.save(userDetails);
	}

	public ResponseEntity<?> deleteUserDetails(Long id){
		LOGGER.info("Exec deleteUserDetails "+id);
		UserDetails userDetails =null;
		try {
			userDetails = userDetailsRepository.findById(id).get();
		}catch(Exception e ) {
			LOGGER.info("Unable to Delete the userdetails by Id : "+id);
			throw new ResourceNotFoundException("UserDetails", "id", id);
		}
		userDetailsRepository.delete(userDetails);
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<?> deleteUserDetailsByName(String userName){
		LOGGER.info("Exec deleteUserDetailsByName "+userName);
		UserDetails userDetails =null;
		try {
			userDetails = userDetailsRepository.findByUserName(userName);
		}catch(Exception e ) {
			LOGGER.info("Unable to Delete the userdetails by User Name : "+userName);
			throw new ResourceNotFoundException("UserDetails", "userName", userName);
		}
		userDetailsRepository.delete(userDetails);
		return ResponseEntity.ok().build();
	}

	public List<UserDetails> fetchAllUserDetails(){
		LOGGER.info("Exec fetchAllUserDetails ");
		List<UserDetails> userDetails =null;
		try {
			userDetails = userDetailsRepository.findAll();
		}catch(Exception e ) {
		}
		return userDetails;
	}

	public String fetchUserRoleById(Long id){
		LOGGER.info("Exec fetchUserRoleById "+id);
		String role = userDetailsRepository.findUserRoleById(id);
		return role;
	}

	public String fetchByUserName(String userName){
		LOGGER.info("Exec fetchByUserName "+userName);
		UserDetails userDetails=null;
		String role = "";
		userDetails = userDetailsRepository.findByUserName(userName);
		if(userDetails !=null ) {
			LOGGER.info(" userDetails :--> "+userDetails);
			role = userDetails.getRole();
		}else{
			LOGGER.info(" Invalid User  Cfredentails ");
			role =  "Invalid Credentials";
		}
		return role;
	}

	public UserDetails fetchByUserDetailsByName(String userName){
		LOGGER.info("Exec fetchByUserDetailsByName "+userName);
		UserDetails userDetails=null;
		userDetails = userDetailsRepository.findByUserName(userName);
		if(userDetails !=null ) {
			LOGGER.info(" userDetails :--> "+userDetails);
			return userDetails ;
		}else{
			return null;
		}
	}

	public UserDetails fetchUserDetailsByNamePassword(UserBean userBean){
		LOGGER.info("Exec fetchByUserDetailsByNamePassword"+userBean);
		String role ,userName,password ;
		role = userName = password =null;
		userName = userBean.getName();
		password = userBean.getPassword();
		UserDetails tempUserDetails = userDetailsRepository.findByUserName(userName);
		if(tempUserDetails !=null && tempUserDetails.getPassword().equalsIgnoreCase(password)) {
			LOGGER.info(" userDetails :--> "+tempUserDetails);
		}else{
			LOGGER.info(" Invalid User  Credentails ");
			return null;
		}
		return tempUserDetails;
	}

	public UserDetails fetchUserDetailsByUserName(String userName) {
		LOGGER.info("Exec fetchUserIdByName : "+userName);
		String role ,password ;
		role = userName = password =null;
		UserDetails tempUserDetails = userDetailsRepository.findByUserName(userName);
		if(tempUserDetails !=null && tempUserDetails.getPassword().equalsIgnoreCase(password)) {
			LOGGER.info(" userDetails :--> "+tempUserDetails);
		}else{
			LOGGER.info(" Invalid User  Credentails ");
			return null;
		}
		return tempUserDetails;
	}

	public UserDetails updateUserPassword(String userName, String newPassword) {
		UserDetails userDetails =null;
		UserDetails updatedUserDetails =null;

		try {
			userDetails = userDetailsRepository.findByUserName(userName);
			userDetails.setPassword(newPassword);
			updatedUserDetails  = userDetailsRepository.save(userDetails);
		}catch(Exception e ) {
			LOGGER.info("Unable to Update  the userdetai+ls by userName: "+userName);
			throw new ResourceNotFoundException("userDetails", "id", userName);
		}
		return updatedUserDetails;
	}
}
