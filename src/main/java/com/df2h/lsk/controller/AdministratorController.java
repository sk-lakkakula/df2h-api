package com.df2h.lsk.controller;

import java.util.List;
import java.util.NoSuchElementException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.df2h.lsk.exception.ResourceNotFoundException;
import com.df2h.lsk.model.Administrator;
import com.df2h.lsk.service.AdministratorService;

@RestController
@CrossOrigin(origins = "*")
/**
 * 
 * @author slakkakula
 *
 */
public class AdministratorController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	AdministratorService administratorService;

	// Get All Administrators
	@GetMapping("/administrator")
	public JSONObject getAllAdministrators() {
		LOGGER.info("Executing getAllAdministrators in AdministratorController");
		JSONObject jsonObject = new JSONObject();
		List<Administrator> AdministratorsList = administratorService.fetchAll();
		if (AdministratorsList != null && AdministratorsList.size() > 0) {
			jsonObject.put("responseCode", "0000");
			jsonObject.put("responseMessage", "Successfully Fetched All Administrators");
			jsonObject.put("responseData", AdministratorsList);
		} else {
			jsonObject.put("responseCode", "1000");
			jsonObject.put("responseMessage", "No Records to Fetch");
			jsonObject.put("responseData", null);
		}
		return jsonObject;
	}

	// Create a new Administrator
	@PostMapping("/administrator")
	public JSONObject create(@RequestBody Administrator administrator) {
		LOGGER.info("Executing create in AdministratorController");
		JSONObject jsonObject = new JSONObject();
		Administrator temp = administratorService.saveAdministrator(administrator);
		if (temp != null) {
			jsonObject.put("responseCode", "0000");
			jsonObject.put("responseMessage", "Successfully Created Administrator");
			jsonObject.put("responseData", temp);
		} else {
			jsonObject.put("responseCode", "1000");
			jsonObject.put("responseMessage", "Failed to Create Administrator");
			jsonObject.put("responseData", null);
		}
		return jsonObject;
	}

	// Get a Single Administrator
	@GetMapping("/administrator/{id}")
	public JSONObject getAdministratorById(@PathVariable(value = "id") Long administratorId) {
		LOGGER.info("Executing getAdministratorById in AdministratorController");
		JSONObject jsonObject = new JSONObject();
		try {
			Administrator temp = administratorService.fetchById(administratorId);
			if (temp != null) {
				jsonObject.put("responseCode", "0000");
				jsonObject.put("responseMessage", "Successfully fetched Administrator by ID: " + administratorId);
				jsonObject.put("responseData", temp);
			}
		} catch (ResourceNotFoundException e) {
			jsonObject.put("responseCode", "1003");
			jsonObject.put("responseMessage", String.format("%s not found with %s : '%s'", e.getResourceName(),
					e.getFieldName(), e.getFieldValue()));
			jsonObject.put("responseData", null);
		}
		return jsonObject;
	}

	// Update a Administrator
	@PutMapping("/administrator/{id}")
	public JSONObject update(@PathVariable(value = "id") Long AdministratorId,
			@RequestBody Administrator administrator) {
		LOGGER.info("Executing update in AdministratorController");
		JSONObject jsonObject = new JSONObject();
		try {
			Administrator temp = administratorService.update(AdministratorId, administrator);
			if (temp != null) {
				jsonObject.put("responseCode", "0000");
				jsonObject.put("responseMessage", "Successfully Uppdated Administrator by ID: " + AdministratorId);
				jsonObject.put("responseData", temp);
			} else {
				jsonObject.put("responseCode", "1000");
				jsonObject.put("responseMessage", "Failed to Update Administrator by ID: " + AdministratorId);
				jsonObject.put("responseData", null);
			}
		} catch (NoSuchElementException e) {
			jsonObject.put("responseCode", "1001");
			jsonObject.put("responseMessage", "Failed to Update Admin as there No Record with id :" + AdministratorId);
			jsonObject.put("responseData", e.getMessage());
		} catch (Exception e) {
			jsonObject.put("responseCode", "1001");
			jsonObject.put("responseMessage", "Failed to Update Admin ");
			jsonObject.put("responseData", e.getMessage());
		}
		return jsonObject;
	}

	// Delete a Administrator
	@DeleteMapping("/administrator/{id}")
	public ResponseEntity<?> deleteAdministrator(@PathVariable(value = "id") Long id) {
		LOGGER.info("Executing deleteAdministrator/{id} in AdministratorController");
		return administratorService.deleteAdministrator(id);
	}
}