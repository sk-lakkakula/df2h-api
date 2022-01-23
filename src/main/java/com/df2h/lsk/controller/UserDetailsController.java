package com.df2h.lsk.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.df2h.lsk.model.Administrator;
import com.df2h.lsk.model.Consumer;
import com.df2h.lsk.model.Farmer;
import com.df2h.lsk.model.Supplier;
import com.df2h.lsk.model.UserDetails;
import com.df2h.lsk.pojo.AdministratorPojo;
import com.df2h.lsk.pojo.FarmerPojo;
import com.df2h.lsk.pojo.SupplierPojo;
import com.df2h.lsk.pojo.UserBean;
import com.df2h.lsk.service.SupplierService;
import com.df2h.lsk.service.UserDetailsService;
import com.df2h.lsk.util.ModelToPojoConverter;

@RestController
@CrossOrigin(origins = "http://localhost:9090")
public class UserDetailsController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	SupplierService supplierService;

	// Get All UserDetails
	@GetMapping("/userdetails")
	public JSONObject getAllUserDetails() {
		JSONObject jsonObject =new JSONObject ();
		LOGGER.info("Executing getAllUserDetails in UserDetailsController");
		try {
			List<UserDetails> listUsers = userDetailsService.fetchAllUserDetails();
			if(listUsers!=null && listUsers.size()>0) {
				jsonObject .put("responseCode", "0000");
				jsonObject .put("responseMessage", "Fetched All Records from User Details");
				jsonObject .put("responseData", userDetailsService.fetchAllUserDetails());
			}else {
				jsonObject .put("responseCode", "1000");
				jsonObject .put("responseMessage", "NO Records from User Details");
				jsonObject .put("responseData", null);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject ;
	}

	// Create a new UserDetails
	@PostMapping("/userdetails")
	public JSONObject createUserDetails(@Valid @RequestBody UserBean userBean ) {
		LOGGER.info("Executing createUserDetails in UserDetailsController userBean : "+userBean );
		UserDetails userDetails =null;
		JSONObject jsonObject = new JSONObject();
		Consumer consumer = null;
		Supplier supplier = null;
		Farmer farmer = null;
		Administrator administrator = null;
		String userName ,password,userRole ,firstName ,lastName,address,contactNo,email,userRegistrationStatus;
		userName = password=userRole =firstName =lastName=address=contactNo=email=userRegistrationStatus = null;
		long supplierId = 0;

		if(userBean !=null){
			LOGGER.info("userBean : "+userBean);
			System.err.println("******* userBean *********: "+userBean);
			userName = userBean.getName();
			password= userBean.getPassword();
			userRole = userBean.getRole().toLowerCase();
			firstName = userBean.getFirstName();
			lastName = userBean.getLastName();
			address = userBean.getAddress();
			contactNo = userBean.getContactNo();
			email = userBean.getEmail();

			switch (userRole) {
			case "administrator":
				userDetails = new UserDetails(userName,userRole,password);
				administrator = new Administrator(firstName, lastName,address,email,contactNo);
				userDetails.setAdministrator(administrator);
				break;

			case "farmer":
				userDetails = new UserDetails(userName,userRole,password);
				farmer = new Farmer(firstName,lastName,address,email,contactNo,"pending");
				userDetails.setFarmer(farmer);
				break;
			case "supplier":
				userDetails = new UserDetails(userName,userRole,password);
				supplier= new Supplier();
				supplier.setFirstName(firstName);
				supplier.setLastName(lastName);
				supplier.setAddress(address);
				supplier.setEmail(email);
				supplier.setContactNo(contactNo);
				supplier.setUserRegistrationStatus("pending");
				userDetails.setSupplier(supplier);
				break;
			case "consumer":
				if(userBean.getSupplierId()!=null){
					supplierId = userBean.getSupplierId();
					userRegistrationStatus = "pending";
					supplier = supplierService.fetchSupplierById(supplierId);
					userDetails = new UserDetails(userName,userRole,password);
					consumer = new Consumer();
					consumer.setFirstName(firstName);
					consumer.setLastName(lastName);
					consumer.setAddress(address);
					consumer.setContactNo(contactNo);
					consumer.setEmail(email);
					consumer.setSupplier(supplier);
					consumer.setRegistrationStatus("Pending");
					/*Survey survey = new Survey();
					survey.setSurveyStatus("Pending");
					consumer.setSurvey(survey);*/
					userDetails.setConsumer(consumer);					
				}else{
					jsonObject .put("responseCode", "1003");
					jsonObject .put("responseMessage", "Missing Supplier ID");
					jsonObject .put("responseData", null);
					return jsonObject;
				}
				break;

			default:
				break;
			}
			LOGGER.info("After Setting "+userRole+"the User Details Obj : "+userDetails);
			System.err.println("After Setting "+userRole+"the User Details Obj : "+userDetails);
			try{
				userDetails = userDetailsService.saveUserDetails(userDetails);

				jsonObject .put("responseCode", "0000");
				jsonObject .put("responseMessage", "Successfully Created user");
				jsonObject .put("responseData", userDetails);

			}catch (ConstraintViolationException e) {
				jsonObject .put("responseCode", "1006");
				jsonObject .put("responseMessage", "Duplicate User");
				jsonObject .put("responseData", null);
			}catch (DataIntegrityViolationException e) {
				jsonObject .put("responseCode", "1006");
				jsonObject .put("responseMessage", "Duplicate "+userRole);
				jsonObject .put("responseData", null);
			}
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "Missing valid Parameters");
			jsonObject .put("responseData", null);
		}
		return jsonObject;
	}

	// Create a new UserDetails
	@PostMapping("/userdetails/login")
	public JSONObject validateUser(@Valid @RequestBody UserBean userBean ) {
		LOGGER.info("Executing validateUser in UserDetailsController");
		JSONObject jsonObject = new JSONObject();
		UserDetails tempUserDetails = null;
		Administrator administrator =null;
		Farmer farmer  =null;
		Supplier supplier =null;
		Consumer consumer =null;
		String role = "";
		if(userBean !=null){ 
			tempUserDetails  = userDetailsService.fetchUserDetailsByNamePassword(userBean);
			if(tempUserDetails !=null ) {
				role  = tempUserDetails.getRole().toLowerCase();
				switch (role ) {
				case "administrator":
					administrator = tempUserDetails.getAdministrator();
					AdministratorPojo apojo = ModelToPojoConverter.convertAdminModelToPojo(administrator); 
					jsonObject .put("responseCode", "0000");
					jsonObject .put("responseMessage", "Succesfully Logged in as : "+role);
					jsonObject .put("responseData", apojo);
					jsonObject .put("role",role);
					break;
				case "farmer":
					farmer = tempUserDetails.getFarmer();
					FarmerPojo fpojo = ModelToPojoConverter.convertFarmerModelToPojo(farmer);
					jsonObject .put("responseCode", "0000");
					jsonObject .put("responseMessage", "Succesfully Logged in as : "+role);
					jsonObject .put("responseData", fpojo);
					jsonObject .put("role",role);
					break;

				case "supplier":
					supplier = tempUserDetails.getSupplier();
					SupplierPojo spojo = ModelToPojoConverter.convertSupplierModelToPojo(supplier);
					jsonObject .put("responseCode", "0000");
					jsonObject .put("responseMessage", "Succesfully Logged in as : "+role);
					jsonObject .put("responseData", spojo);
					jsonObject .put("role",role);
					break;

				case "consumer":
					consumer = tempUserDetails.getConsumer();
					jsonObject .put("responseCode", "0000");
					jsonObject .put("responseMessage", "Succesfully Logged in as : "+role);
					jsonObject .put("responseData", consumer );
					jsonObject .put("role",role);
					break;

				default:
					break;
				}
				return jsonObject;
			}
			else {
				jsonObject .put("responseCode", "1000");
				jsonObject .put("responseMessage", "Invalid Credentials ");
				jsonObject .put("responseData", null);
			}
		}else {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Missing Mandatory Fields");
			jsonObject .put("responseData", null);
		}
		return jsonObject;
	}

	// Get a Single UserDetails
	@GetMapping("/userdetails/id/{id}")
	public JSONObject  getUserDetailsById(@PathVariable(value = "id") Long userDetailsId) {
		LOGGER.info("Executing getUserDetailsById in UserDetailsController");
		UserDetails userDetails = userDetailsService.fetchUserDetailsById(userDetailsId);
		JSONObject jsonObject = new JSONObject();
		if(userDetails !=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Fetched User Details for ID: "+userDetailsId);
			jsonObject .put("responseData", userDetails );
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "failed to get the User Details for ID :"+userDetailsId);
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}
	
	// Get a Single UserDetails
/*	@GetMapping("/userdetails/userName/{userName}")
	public JSONObject  getUserIdByUserName(@PathVariable(value = "userName") String userName) {
		LOGGER.info("Executing getUserIdByUserName in UserDetailsController");
		UserDetails userDetails = userDetailsService.fetchUserDetailsByUserName(userName);
		JSONObject jsonObject = new JSONObject();
		if(userDetails !=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Fetched User Details for Name : "+userName);
			jsonObject .put("responseData", userDetails );
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "failed to get the User Details for Name :"+userName);
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}
*/

	// Update a UserDetails
	@PutMapping("/userdetails/{id}")
	public JSONObject updateUserDetails(@PathVariable(value = "id") Long UserDetailsId) {
		LOGGER.info("Executing updateUserDetails in UserDetailsController");
		UserDetails  temp = userDetailsService.updateUserDetails(UserDetailsId);
		JSONObject jsonObject = new JSONObject();
		if(temp!=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Updated User Details for ID: "+UserDetailsId);
			jsonObject .put("responseData", temp );
		}
		else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "failed to Update the User Details for ID :"+UserDetailsId);
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}

	// Delete a UserDetails
	@DeleteMapping("/userdetails/id/{id}")
	public ResponseEntity<?> deleteUserDetails(@PathVariable(value = "id") Long userId) {
		LOGGER.info("Executing /UserDetails/{id} in UserDetailsController");
		return userDetailsService.deleteUserDetails(userId);
	}

	// Delete a UserDetails
	@DeleteMapping("/userdetails/name/{name}")
	public ResponseEntity<?> deleteUserDetailsByName(@PathVariable(value = "name") String userName) {
		LOGGER.info("Executing /UserDetails/{userName} in UserDetailsController");
		return userDetailsService.deleteUserDetailsByName(userName);
	}

	@PostMapping("/userdetails/role")
	public String getUserRoleByUserName(@Valid @RequestBody UserDetails userDetails) {
		LOGGER.info("Executing getUserRoleByUserName in UserDetailsController");
		if(userDetails!=null){
			return userDetailsService.fetchByUserName(userDetails.getUserName());
		}
		return null;
	}
	
	@PutMapping("/userdetails/{userName}/{newPassword}")
	public JSONObject updateUserPassword(@PathVariable(value = "userName") String userName, @PathVariable (value = "newPassword")String newPassword) {
		LOGGER.info("Executing updateUserPassword in UserDetailsController");
		UserDetails  temp = userDetailsService.updateUserPassword(userName,newPassword);
		JSONObject jsonObject = new JSONObject();
		if(temp!=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Updated User Password for  ID: "+userName);
			jsonObject .put("responseData", temp );
		}
		else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "failed to Update the User Password for  ID :"+userName);
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}

}


