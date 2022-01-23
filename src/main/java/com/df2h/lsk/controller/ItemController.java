package com.df2h.lsk.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.df2h.lsk.exception.ResourceNotFoundException;
import com.df2h.lsk.model.Category;
import com.df2h.lsk.model.Item;
import com.df2h.lsk.service.CategoryService;
import com.df2h.lsk.service.ItemService;

@RestController
@CrossOrigin(origins = "*")
/**
 * 
 * @author slakkakula
 *
 */
public class ItemController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());


	@Autowired
	ItemService itemService;

	@Autowired
	CategoryService categoryService;
	// Get All Items
	@GetMapping("/item")
	@ResponseBody
	public JSONObject getAll(Pageable pageable ) {
		LOGGER.info("Executing getAllItems in ItemController");
		JSONObject jsonObject = new JSONObject();
		Page<Item>  temp= itemService.fetchAllItems(pageable );
		if(temp!=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Fetched All Items");
			jsonObject .put("responseData", temp);
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "No Records to Fetch");
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}
	

	// Create a new Item
	@PostMapping("/item/categoryName/{categoryName}")
	public JSONObject createItem(@RequestBody Item item ,@PathVariable (name = "categoryName") String categoryName ) {
		LOGGER.info("Executing createItem in ItemController , item : "+item);
		JSONObject jsonObject = new JSONObject();
		if(item!= null ){
			if(categoryName!=null ){
				Item tempItem = new Item();
				tempItem .setName(item.getName());
				tempItem .setDescription(item.getDescription());
				tempItem .setPhotoUrl(item.getPhotoUrl());
				tempItem .setUnitCost(item.getUnitCost());
				tempItem .setQuantityOrdered(item.getQuantityOrdered());
				tempItem .setUnitOfSale(item.getUnitOfSale());
				tempItem .setStockRemaining(item.getStockRemaining());
				
				Category tempCategory = categoryService.fetchCategoryByName(categoryName);
				LOGGER.info("temp Item: "+tempItem+", tempCategory : "+tempCategory +" are gettinbg saved ");
				tempCategory.add(tempItem);
				//tempItem.setCategory(tempCategory);
				Item temp = itemService.saveItem(tempItem);
				if(temp!=null ) {
					jsonObject .put("responseCode", "0000");
					jsonObject .put("responseMessage", "Successfully Created Item");
					jsonObject .put("responseData", temp);
				}else {
					jsonObject .put("responseCode", "1000");
					jsonObject .put("responseMessage", "failed to Create Item");
					jsonObject .put("responseData", null);
				}
			}else{
				jsonObject .put("responseCode", "1001");
				jsonObject .put("responseMessage", "failed to Create Item as categoryName is Misssing");
				jsonObject .put("responseData", null);
			}
		}else {
			jsonObject .put("responseCode", "1002");
			jsonObject .put("responseMessage", "failed to Create Item as Item is is missing Mandatory Fields");
			jsonObject .put("responseData", null);
		}
		return jsonObject;
	}


	// Get a Single Item
	@GetMapping("/item/{id}")
	@ResponseBody
	public JSONObject getItemById(@PathVariable(value = "id") Long id) {
		LOGGER.info("Executing getItemById in ItemController");
		JSONObject jsonObject = new JSONObject();
		Item  temp = null;
		if(id !=null ) {
			try{
				temp = itemService.fetchItemById(id);
				if(temp!=null ) {
					jsonObject .put("responseCode", "0000");
					jsonObject .put("responseMessage", "Successfully fetched Administrator by ID: "+id);
					jsonObject .put("responseData", temp);
				}
			}catch (ResourceNotFoundException e) {
				jsonObject .put("responseCode", "1003");
				jsonObject .put("responseMessage", String.format("%s not found with %s : '%s'", e.getResourceName(), e.getFieldName(), e.getFieldValue()));
				jsonObject .put("responseData", null);
			}
		}else{
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Fetch Consumer as Missing Parameter Consumer ID ");
			jsonObject .put("responseData", null);
		}

		return jsonObject ;
	}

	// Update a Item
	@PutMapping("/item/{id}")
	public JSONObject updateItem(@PathVariable(value = "id") Long id,@RequestBody Item item) {
		LOGGER.info("Executing updateItem in ItemController");
		JSONObject jsonObject = new JSONObject();
		Item  temp= itemService.updateItem(id, item);
		if(temp!=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Updated Item "+id);
			jsonObject .put("responseData", temp);
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "failed to Updarte Item by : "+id);
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}

	// Delete a Item
	@DeleteMapping("/item/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable(value = "id") Long itemId) {
		LOGGER.info("Executing /item/{id} in ItemController");
		return itemService.deleteItem(itemId);
	}
}