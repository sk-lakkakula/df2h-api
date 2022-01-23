package com.df2h.lsk.controller;

import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.df2h.lsk.model.Category;
import com.df2h.lsk.model.Item;
import com.df2h.lsk.service.CategoryService;
import com.df2h.lsk.util.ModelToPojoConverter;

@RestController
@CrossOrigin(origins = "*")
/**
 * 
 * @author slakkakula
 *
 */

public class CategoryController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	CategoryService categoryService;

	@RequestMapping("/category")
	public @ResponseBody JSONObject getAll() {
		LOGGER.info("Executing getAllCategory in CategoryController");
		JSONObject jsonObject = new JSONObject();
		int total = 0;
		List <com.df2h.lsk.pojo.Category> categoryPojoList = null;
		List<Category> categoryList = categoryService.fetchAll();
		categoryPojoList  = ModelToPojoConverter.convertModelToPojo(categoryList);
		for (Iterator iterator = categoryList.iterator(); iterator.hasNext();) {
			Category category = (Category) iterator.next();
			LOGGER.info("Category Id :"+category.getId()+":Category Name :"+category.getName()+"Category Image : "+category.getImage()+" , Items Count  : "+category.getItems().size());
		}
//		total = categoryService.fetchItemsCountByCategoryName(name);
		
		if(categoryList !=null && categoryList.size()>0) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Fetched Category List  ");
			jsonObject .put("responseData", categoryPojoList);
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "No Records to Fetch");
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}


	// Create a new Category
	@PostMapping("/category")
	public JSONObject createCategory(@RequestBody Category category) {
		LOGGER.info("Executing createCategory in CategoryController");
		JSONObject jsonObject = new JSONObject();
		Category temp = categoryService.saveCategory(category);
		if(temp!=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Created Category ");
			jsonObject .put("responseData", temp);
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "failed to Create Category ");
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}

	// Get a Single Category
	@GetMapping("/category/id/{id}")
	public JSONObject getCategoryById(@PathVariable(value = "id") Long categoryId) {
		LOGGER.info("Executing getCategoryById in CategoryController");
		JSONObject jsonObject = new JSONObject();
		Category temp = categoryService.fetchById(categoryId);
		if(temp!=null ) {
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfully Fetched Category by ID : "+categoryId);
			jsonObject .put("responseData", temp);
		}else {
			jsonObject .put("responseCode", "1000");
			jsonObject .put("responseMessage", "failed to Fetch Category by ID : "+categoryId);
			jsonObject .put("responseData", null);
		}
		return jsonObject ;
	}

	// Get a Single Category By Name
		@GetMapping("/category/name/{name}")
		public JSONObject getCategoryByName(@PathVariable(value = "name") String  name) {
			LOGGER.info("Executing getCategoryById in CategoryController");
			JSONObject jsonObject = new JSONObject();
			Category temp = categoryService.fetchCategoryByName(name);
			if(temp!=null ) {
				jsonObject .put("responseCode", "0000");
				jsonObject .put("responseMessage", "Successfully Fetched Category by Name : "+name);
				jsonObject .put("responseData", temp);
			}else {
				jsonObject .put("responseCode", "1000");
				jsonObject .put("responseMessage", "failed to Fetch Category by Name : "+name);
				jsonObject .put("responseData", null);
			}
			return jsonObject ;
		}

	// Get a Single Category
	@GetMapping("/category/itemscount/{name}")
	public JSONObject getItemsCountByCategoryName(@PathVariable(value = "name") String  name) {
		LOGGER.info("Executing getItemsCountByCategoryName in CategoryController");
		JSONObject jsonObject = new JSONObject();
		int total = 0;
		total = categoryService.fetchItemsCountByCategoryName(name);
		jsonObject .put("responseCode", "0000");
		jsonObject .put("responseMessage", "Successfully Fetched Count of Items under One Category : "+name);
		jsonObject .put("responseData", total);
		return jsonObject ;
	}

	// Get a Single Category
	@GetMapping("/category/items/{name}")
	public JSONObject getItemsByCategoryName(@PathVariable(value = "name") String  name) {
		LOGGER.info("Executing getItemsByCategoryName in CategoryController");
		List<Item> itemsList = null;
		JSONObject jsonObject = new JSONObject();
		itemsList = categoryService.fetchItemsByCategoryName(name);
		jsonObject .put("responseCode", "0000");
		jsonObject .put("responseMessage", "Successfully Fetched Items under One Category : "+name+" , itemsList:--> "+itemsList);
		jsonObject .put("responseData", itemsList);
		return jsonObject ;
	}

	@PutMapping("/category/{id}")
	public JSONObject update(@PathVariable(value = "id") Long id, @RequestBody Category category) {
		LOGGER.info("Executing update in category");
		JSONObject jsonObject = new JSONObject();
		Category temp =null;
		try{
			temp = categoryService.update(id,category);
			if(temp!=null ) {
				jsonObject .put("responseCode", "0000");
				jsonObject .put("responseMessage", "Successfully Uppdated category  by ID: "+id);
				jsonObject .put("responseData", temp);
			}else {
				jsonObject .put("responseCode", "1000");
				jsonObject .put("responseMessage", "Failed to Update category by ID: "+id);
				jsonObject .put("responseData", null);
			}
		}
		catch (NoSuchElementException e) {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Update category there No Record with id :"+id);
			jsonObject .put("responseData", e.getMessage());
		}
		catch (Exception e) {
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed to Update category ");
			jsonObject .put("responseData", e.getMessage());
		}
		return jsonObject ;
	}

	// Delete a Category
	@DeleteMapping("/category/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteCategory(@PathVariable(value = "id") Long categoryId) {
		LOGGER.info("Executing deleteCategory in CategoryController");
		return categoryService.deleteCategory(categoryId);
	}

}