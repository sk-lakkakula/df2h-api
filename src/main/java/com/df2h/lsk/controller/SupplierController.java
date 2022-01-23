package com.df2h.lsk.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.df2h.lsk.exception.ResourceNotFoundException;
import com.df2h.lsk.model.Consumer;
import com.df2h.lsk.model.Supplier;
import com.df2h.lsk.service.SupplierService;

@RestController
@CrossOrigin(origins = "http://localhost:9090")
/**
 * 
 * @author slakkakula
 *
 */
public class SupplierController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	SupplierService supplierService;

	// Get All suppliers
	@GetMapping("/supplier")
	public JSONObject getAll(Pageable pageable ) {
		LOGGER.info("Executing getAllSuppliers in SupplierController");
		JSONObject jsonObject = new JSONObject();
		Page<Supplier> suppliersList= supplierService.fetchAllSuppliers(pageable);
		if(suppliersList!=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Fetched All Suppliers");
			jsonObject .put("responseData", suppliersList);
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "No Records to Fetch");
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}

	/**
	 *	Get All suppliers By Registration Status 
	 */
	@GetMapping("/supplier/registration/{status}")
	public JSONObject getAllByRegistrationStatus(Pageable pageable ,@PathVariable(value = "status") String status) {
		LOGGER.info("Executing getAllSuppliers in SupplierController");
		JSONObject jsonObject = new JSONObject();
		Page<Supplier> suppliersList= supplierService.fetchAllSuppliersByRegistrationStatus(pageable, status);
		if(suppliersList!=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Fetched All Suppliers");
			jsonObject .put("responseData", suppliersList);
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "No Records to Fetch");
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}


	// Create a new Supplier
	@PostMapping("/supplier")
	public JSONObject create(@RequestBody Supplier supplier) {
		LOGGER.info("Executing create in SupplierController");
		JSONObject jsonObject = new JSONObject();
		Supplier temp= supplierService.saveSupplier(supplier);
		if(temp!=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Created Supplier");
			jsonObject .put("responseData", temp);
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "Failed to Create Supplier");
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}

	// Get a Single Supplier
	@GetMapping("/supplier/details/{id}")
	public JSONObject getById(@PathVariable(value = "id") Long supplierId) {
		LOGGER.info("Executing getsupplierById in SupplierController");
		JSONObject jsonObject = new JSONObject();
		try {

			Supplier temp= supplierService.fetchSupplierById(supplierId);
			if(temp!=null ) {
				jsonObject .put("responseCode", "0000");
				jsonObject .put("responseMessage", "Successfully fetched Supplier by ID: "+supplierId);
				jsonObject .put("responseData", temp);
			}
		}catch (ResourceNotFoundException e) {
			jsonObject .put("responseCode", "1003");
			jsonObject .put("responseMessage", String.format("%s not found with %s : '%s'", e.getResourceName(), e.getFieldName(), e.getFieldValue()));
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}

/*	@GetMapping("/supplier/consumers/{id}")
	public JSONObject getConsumersBySupplierIdApplyFilter(Pageable pageable , @PathVariable(value = "id") Long supplierId) {
		LOGGER.info("Executing getsupplierById in SupplierController");
		JSONObject jsonObject = new JSONObject();
		try {

			Supplier temp= supplierService.fetchSupplierById(supplierId);
			if(temp!=null ) {
				if(temp.getConsumers()!=null && temp.getConsumers().size()>0){

					jsonObject .put("responseCode", "0000");
					jsonObject .put("responseMessage", "Successfully fetched Supplier by ID: "+supplierId);
					jsonObject .put("responseData", temp.getConsumers());
				}
				else{
					jsonObject .put("responseCode", "1000");
					jsonObject .put("responseMessage", "No Records found for Supplier ID: "+supplierId);
					jsonObject .put("responseData", null);
				}
			}
		}catch (ResourceNotFoundException e) {
			jsonObject .put("responseCode", "1003");
			jsonObject .put("responseMessage", String.format("%s not found with %s : '%s'", e.getResourceName(), e.getFieldName(), e.getFieldValue()));
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}
*/
	@GetMapping("/supplier/consumers/{id}/{filterByRegStatus}")
	public JSONObject getfConsumersBySupplierIdFilterByRegStatus(Pageable pageable , @PathVariable(value = "id") Long supplierId,@PathVariable(value = "filterByRegStatus") String filterByRegStatus) {
		LOGGER.info("Executing getfConsumersBySupplierIdFilterByRegStatus in SupplierController : id :--> "+supplierId+"filterByRegStatus :--> "+filterByRegStatus);
		JSONObject jsonObject = new JSONObject();
		try {
			Supplier supplier = supplierService.fetchSupplierById(supplierId);
			List<Consumer> consumersList = supplierService.fetchConsumersBySupplierIdFilterByRegStatus(supplier, filterByRegStatus);
			
			if(consumersList!=null && consumersList.size()>0) {
				jsonObject .put("responseCode", "0000");
				jsonObject .put("responseMessage", "Successfully fetched Supplier : "+supplier);
				jsonObject .put("responseData", consumersList);
			}
			else{
				jsonObject .put("responseCode", "1000");
				jsonObject .put("responseMessage", "No Records found for Supplier ID: "+supplierId);
				jsonObject .put("responseData", null);
			}
			
		}catch (ResourceNotFoundException e) {
			jsonObject .put("responseCode", "1003");
			jsonObject .put("responseMessage", String.format("%s not found with %s : '%s'", e.getResourceName(), e.getFieldName(), e.getFieldValue()));
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}

	@GetMapping("/supplier/consumers/{id}")
	public JSONObject getConsumersBySupplierId( @PathVariable(value = "id") Long supplierId) {
		LOGGER.info("Executing getConsumersBySupplierId in SupplierController");
		JSONObject jsonObject = new JSONObject();
		try {

			Supplier temp= supplierService.fetchSupplierById(supplierId);
			if(temp!=null ) {
				if(temp.getConsumers()!=null && temp.getConsumers().size()>0){
					jsonObject .put("responseCode", "0000");
					jsonObject .put("responseMessage", "Successfully fetched Supplier by ID: "+supplierId);
					jsonObject .put("responseData", temp.getConsumers());
				}
				else{
					jsonObject .put("responseCode", "1000");
					jsonObject .put("responseMessage", "No Records found for Supplier ID: "+supplierId);
					jsonObject .put("responseData", null);
				}
			}
		}catch (ResourceNotFoundException e) {
			jsonObject .put("responseCode", "1003");
			jsonObject .put("responseMessage", String.format("%s not found with %s : '%s'", e.getResourceName(), e.getFieldName(), e.getFieldValue()));
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}



	// Update a Supplier
	@PutMapping("/supplier/{id}")
	public JSONObject updateSupplier(@PathVariable(value = "id") Long supplierId, @RequestBody Supplier supplier) {
		LOGGER.info("Executing updateSupplier in SupplierController");
		JSONObject jsonObject = new JSONObject();
		Supplier temp = null;
		try{
			temp= supplierService.update(supplierId,supplier);
			if(temp!=null ) {
				jsonObject .put("responseCode", "0000");
				jsonObject .put("responseMessage", "Successfully Uppdated Supplier by ID: "+supplierId);
				jsonObject .put("responseData", temp);
			}else {
				jsonObject .put("responseCode", "1000");
				jsonObject .put("responseMessage", "Failed to Update Supplier by ID: "+supplierId);
				jsonObject .put("responseData", null);
			}
		}
		catch (NoSuchElementException e) {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Update Supplier as there No Record with id :"+supplierId);
			jsonObject .put("responseData", e.getMessage());
		}
		catch (Exception e) {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Update Supplier ");
			jsonObject .put("responseData", e.getMessage());
		}
		return jsonObject ;
	}

	@PutMapping("/supplier/updateStatus/{id}/{status}")
	public JSONObject updateByStatus(@PathVariable(value = "id") Long supplierId,@PathVariable(value = "status") String status) {
		LOGGER.info("Executing updateStatus in Supplier");
		JSONObject jsonObject = new JSONObject();
		Supplier temp= null;
		try{
			temp= supplierService.updateRegistrationStatus(supplierId,status);
			if(temp!=null ) {
				jsonObject .put("responseCode", "0000");
				jsonObject .put("responseMessage", "Successfully Updated Supplier by ID: "+supplierId);
				jsonObject .put("responseData", temp);
			}else {
				jsonObject .put("responseCode", "1000");
				jsonObject .put("responseMessage", "Failed to Update Supplier by ID: "+supplierId);
				jsonObject .put("responseData", null);
			}
		}
		catch (NoSuchElementException e) {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Update Supplier as there No Record with id :"+supplierId);
			jsonObject .put("responseData", e.getMessage());
		}
		catch (Exception e) {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Update Supplier ");
			jsonObject .put("responseData", e.getMessage());
		}
		return jsonObject ;
	}


	// Delete a Supplier
	@DeleteMapping("/supplier/{id}")
	public ResponseEntity<?> deleteSupplier(@PathVariable(value = "id") Long id) {
		LOGGER.info("Executing deleteSupplier/{id} in SupplierController");
		return supplierService.deleteSupplier(id);
	}
}