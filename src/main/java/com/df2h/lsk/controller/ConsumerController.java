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
import org.springframework.web.bind.annotation.RestController;

import com.df2h.lsk.exception.ResourceNotFoundException;
import com.df2h.lsk.model.Consumer;
import com.df2h.lsk.service.ConsumerService;
import com.df2h.lsk.service.ItemService;
import com.df2h.lsk.service.SupplierService;

@RestController
@CrossOrigin(origins = "http://localhost:9090")
/**
 * 
 * @author slakkakula
 *
 */
public class ConsumerController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());


	@Autowired
	ConsumerService consumerService;

	@Autowired
	SupplierService supplierService;

	//	@Autowired
	//	SurveyService surveyService;

	@Autowired
	ItemService itemService;

	/*@PutMapping("/consumer/{id}/survey/submit")
	public JSONObject submitSurvey(@RequestBody Item[] items,@PathVariable(value = "id") Long consumerId) {
		LOGGER.info("Executing createconsumerin consumerController");
		JSONObject jsonObject = new JSONObject();
		Consumer oldConsumer = consumerService.fetchConsumerById(consumerId);
		LOGGER.info("tempConsumer : "+oldConsumer+",Survey : "+oldConsumer.getSurvey());
		LOGGER.info("items : "+items);
		//List<Item> newItemsList = new ArrayList<Item>();
		Survey survey = oldConsumer .getSurvey();
		//surveyService.fetchById(survey.getId());
		survey.setId(survey.getId());
		survey.setSurveyStatus("Completed");
//		survey.setItems(newItemsList);
		Item temp = null;
		for (int i = 0; i < items.length; i++) {
			items[i].setSurvey(survey);
			survey.addItem(items[i]);
		}
		for (int i = 0; i < items.length; i++) {
			itemService.saveItem(items[i]);
		}
		for (Iterator iterator = newItemsList.iterator(); iterator.hasNext();) {
			Item item2 = (Item) iterator.next();
			survey.addItem(item2);
		}

//		Survey newSurvey = surveyService.update(survey.getId(), survey);
//		oldConsumer.setSurvey(survey);

//		Survey newSurvey = surveyService.update(survey.getId(), survey);
		//Consumer newConsmr = consumerService.update(consumerId, oldConsumer);
		if(newSurvey !=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Updated Consumer with Survey Data");
			jsonObject .put("responseData", newSurvey );
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "failed to Update Consumer with Survey Data");
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}
	 */
	/*Get All Consumers*/
	@GetMapping("/consumer")
	public JSONObject getAll(Pageable pageable ) {
		LOGGER.info("Executing getAllConsumer in ConsumerController");
		Page<Consumer> consumersList= consumerService.fetchAllConsumers(pageable);
		LOGGER.info("Consumers List : "+consumersList);
		JSONObject jsonObject = new JSONObject();
		if(consumersList!=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Fetched All Consumers");
			jsonObject .put("responseData", consumersList);
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
	@GetMapping("/consumer/registration/{status}")
	public JSONObject getAllByRegistrationStatus(Pageable pageable ,@PathVariable(value = "status") String status) {
		LOGGER.info("Executing getAllByRegistrationStatus By Status in ConsumerController");
		JSONObject jsonObject = new JSONObject();
		Page<Consumer> consumersList= consumerService.fetchAllConsumersByRegistrationStatus(pageable, status);
		if(consumersList!=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Fetched All Consumrs");
			jsonObject .put("responseData", consumersList);
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "No Records to Fetch");
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}


	// Get a Single Consumer
	@GetMapping("/consumer/{id}")
	public JSONObject  getById(@PathVariable(value = "id") Long consumerId) {
		LOGGER.info("Executing getById in ConsumerController");
		JSONObject jsonObject = new JSONObject();

		if(consumerId !=null ) {
			try{
				Consumer temp = consumerService.fetchConsumerById(consumerId);
				if(temp!=null){
					LOGGER.info(" fetched Consumer : "+temp );
					jsonObject .put("responseCode", "0000");
					jsonObject .put("responseMessage", "Successfully Fetched Consumer by ID :"+consumerId);
					jsonObject .put("responseData", temp);
				}
			}catch (ResourceNotFoundException e) {
				jsonObject .put("responseCode", "1003");
				jsonObject .put("responseMessage", String.format("%s not found with %s : '%s'", e.getResourceName(), e.getFieldName(), e.getFieldValue()));
				jsonObject .put("responseData", null);
			}
		}else {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Fetch Consumer as Missing Parameter Consumer ID ");
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}
	@PutMapping("/consumer/updateStatus/{id}/{status}")
	public JSONObject updateByStatus(@PathVariable(value = "id") Long consumerId,@PathVariable(value = "status") String status) {
		LOGGER.info("Executing updateStatus in Consumer");
		JSONObject jsonObject = new JSONObject();
		Consumer temp= null;
		try{
			temp= consumerService.updateRegistrationStatus(consumerId,status);
			if(temp!=null ) {
				jsonObject .put("responseCode", "0000");
				jsonObject .put("responseMessage", "Successfully Updated Consumer by ID: "+consumerId);
				jsonObject .put("responseData", temp);
			}else {
				jsonObject .put("responseCode", "1000");
				jsonObject .put("responseMessage", "Failed to Update Consumer by ID: "+consumerId);
				jsonObject .put("responseData", null);
			}
		}
		catch (NoSuchElementException e) {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Update Consumer as there No Record with id :"+consumerId);
			jsonObject .put("responseData", e.getMessage());
		}
		catch (Exception e) {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Update Consumer ");
			jsonObject .put("responseData", e.getMessage());
		}
		return jsonObject ;
	}


	@PutMapping("/consumer/{id}")
	public JSONObject update(@PathVariable(value = "id") Long consumerId, @RequestBody Consumer consumer) {
		LOGGER.info("Executing update in ConsumerController");
		JSONObject jsonObject = new JSONObject();
		try{
			Consumer temp= consumerService.update(consumerId,consumer);
			if(temp!=null ) {
				jsonObject .put("responseCode", "0000");
				jsonObject .put("responseMessage", "Successfully Uppdated Consumer by ID: "+consumerId);
				jsonObject .put("responseData", temp);
			}else {
				jsonObject .put("responseCode", "1000");
				jsonObject .put("responseMessage", "Failed to Update Consumer by ID: "+consumerId);
				jsonObject .put("responseData", null);
			}
		}
		catch (NoSuchElementException e) {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Update Admin as there No Record with id :"+consumerId);
			jsonObject .put("responseData", e.getMessage());
		}
		catch (Exception e) {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Update Admin ");
			jsonObject .put("responseData", e.getMessage());
		}
		return jsonObject ;
	}

	// Delete a Consumer
	@DeleteMapping("/consumer/{id}")
	public ResponseEntity<?> deleteConsumer(@PathVariable(value = "id") Long id) {
		LOGGER.info("Executing deleteConsumer/{id} in ConsumerController");
		return consumerService.deleteConsumer(id);
	}

	public static void main(String[] args) {

	}

}