package com.df2h.lsk.controller;

import java.util.NoSuchElementException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.df2h.lsk.exception.ResourceNotFoundException;
import com.df2h.lsk.model.Farmer;
import com.df2h.lsk.model.Supplier;
import com.df2h.lsk.service.AdministratorService;
import com.df2h.lsk.service.FarmerService;

/**
 * @author Dell pc 1
 *
 */
@RestController
@CrossOrigin(origins = "*")
/**
 * 
 * @author slakkakula
 *
 */
public class FarmerController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());


	@Autowired
	FarmerService farmerService;
	@Autowired
	AdministratorService administratorService;

	/**	Get All Farmers
	 * @param pageable
	 * @return
	 */
	@GetMapping("/farmer")
	public JSONObject getAll(Pageable pageable ) {
		LOGGER.info("Executing getAllFarmer in FarmerController");
		Page<Farmer> temp = farmerService.fetchAll(pageable );
		JSONObject jsonObject = new JSONObject();
		if(temp!=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Fetched all Farmers ");
			jsonObject .put("responseData", temp);
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "Failed to Fetch all Farmers ");
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}
	/**
	 *	Get All suppliers By Registration Status 
	 */
	@GetMapping("/farmer/registration/{status}")
	public JSONObject getAllByRegistrationStatus(Pageable pageable ,@PathVariable(value = "status") String status) {
		LOGGER.info("Executing getAllByRegistrationStatus in Farmer Controller");
		JSONObject jsonObject = new JSONObject();
		Page<Farmer> farmersList = null;
		farmersList = farmerService.fetchAllFarmersByRegistrationStatus(pageable, status);
		if(farmersList !=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Fetched All Farmers ");
			jsonObject .put("responseData", farmersList );
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "No Records to Fetch");
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}


	// Get a Single Farmer
	@GetMapping("/farmer/{id}")
	public JSONObject  getFarmerById(@PathVariable(value = "id") Long FarmerId) {
		LOGGER.info("Executing getFarmerById in FarmerController");
		JSONObject jsonObject = new JSONObject();

		if(FarmerId !=null ) {
			try{
				Farmer temp = farmerService.fetchById(FarmerId);
				if(temp!=null ) {
					jsonObject .put("responseCode", "0000");
					jsonObject .put("responseMessage", "Successfully fetched Framer  by ID: "+FarmerId);
					jsonObject .put("responseData", temp);
				}
			}catch (ResourceNotFoundException e) {
				jsonObject .put("responseCode", "1003");
				jsonObject .put("responseMessage", String.format("%s not found with %s : '%s'", e.getResourceName(), e.getFieldName(), e.getFieldValue()));
				jsonObject .put("responseData", null);
			}
		}else{
			jsonObject .put("responseCode", "1003");
			jsonObject .put("responseMessage", "Missing Farmer ID: ");
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}

	@PutMapping("/farmer/{id}")
	public JSONObject update(@PathVariable(value = "id") Long farmerId, @RequestBody Farmer farmer) {
		LOGGER.info("Executing update in FarmerController");
		JSONObject jsonObject = new JSONObject();
		Farmer temp= null;
		try{
			temp= farmerService.update(farmerId,farmer);
			if(temp!=null ) {
				jsonObject .put("responseCode", "0000");
				jsonObject .put("responseMessage", "Successfully Updated Farmer by ID: "+farmerId);
				jsonObject .put("responseData", temp);
			}else {
				jsonObject .put("responseCode", "1000");
				jsonObject .put("responseMessage", "Failed to Update Farmer by ID: "+farmerId);
				jsonObject .put("responseData", null);
			}
		}
		catch (NoSuchElementException e) {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Update Farmer as there No Record with id :"+farmerId);
			jsonObject .put("responseData", e.getMessage());
		}
		catch (Exception e) {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Update Farmer ");
			jsonObject .put("responseData", e.getMessage());
		}
		return jsonObject ;
	}

	@PutMapping("/farmer/updateStatus/{id}/{status}")
	public JSONObject updateByStatus(@PathVariable(value = "id") Long farmerId, @PathVariable(value = "status") String status) {
		LOGGER.info("Executing updateStatus in FarmerController");
		JSONObject jsonObject = new JSONObject();
		Farmer temp= null;
		try{
			temp= farmerService.updateRegistrationStatus(farmerId,status);
			if(temp!=null ) {
				jsonObject .put("responseCode", "0000");
				jsonObject .put("responseMessage", "Successfully Updated Farmer by ID: "+farmerId);
				jsonObject .put("responseData", temp);
			}else {
				jsonObject .put("responseCode", "1000");
				jsonObject .put("responseMessage", "Failed to Update Farmer by ID: "+farmerId);
				jsonObject .put("responseData", null);
			}
		}
		catch (NoSuchElementException e) {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Update Farmer as there No Record with id :"+farmerId);
			jsonObject .put("responseData", e.getMessage());
		}
		catch (Exception e) {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Update Farmer ");
			jsonObject .put("responseData", e.getMessage());
		}
		return jsonObject ;
	}

	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("/farmer/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		LOGGER.info("Executing deleteFarmer/{id} in FarmerController");
		return farmerService.delete(id);
	}
}