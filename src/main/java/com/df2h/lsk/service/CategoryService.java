package com.df2h.lsk.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.df2h.lsk.exception.ResourceNotFoundException;
import com.df2h.lsk.model.Category;
import com.df2h.lsk.model.Item;
import com.df2h.lsk.repository.CategoryRepository;

@Service("categoryService")
public class CategoryService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	CategoryRepository categoryRepository;

	public List<Category> fetchAll(){
		LOGGER.info("Exec fetchAll from categoryService");
		return categoryRepository.findAll();
	}

	public Category fetchById(Long categoryId){
		LOGGER.info("Exec fetchById from categoryService");
		Category category =null;
		try {
			category = categoryRepository.findById(categoryId).get();
			LOGGER.info("Exec fetchById from categoryService  , categoryId: "+categoryId);
		}catch(Exception e ) {
			LOGGER.error("Exec fetchById from categoryService : ERROR  --> "+e);
			throw new ResourceNotFoundException("category", "id", categoryId);
		}
		return category;
	}

	public Category saveCategory(Category category){
		LOGGER.info("Exec saveCategory from categoryService category : "+category);
		Category catgry = categoryRepository.save(category);
		LOGGER.info("Executed saveCategory from categoryService category : "+category);
		return catgry ;
	}

	/*	public Category updateCategory(final Long categoryId) {
		LOGGER.info("Exec updateCategory from categoryService categoryId :"+categoryId);
		Category ctg =null;
		try {
			ctg = categoryRepository.findById(categoryId).get();
		 LOGGER.info("Exec updateCategory from categoryService  , categoryId: "+categoryId);
		}catch(Exception e ) {
			LOGGER.error("Exec updateCategory from categoryService : ERROR  --> "+e);
			throw new ResourceNotFoundException("category", "id", categoryId);
		}
		return categoryRepository.save(ctg);
	}
	 */	
	public Category update(final Long categoryId,Category  category) {
		LOGGER.info("Exec updateCategory    - Category   Service");
		Category  prevCategory  ,updatedCategory  =null;
		String name,image;
		name = image = null;
		try {
			prevCategory = categoryRepository.findById(categoryId).get();
			if(prevCategory!=null){
				LOGGER.info("Exec Category Service  , Category Id: "+categoryId+"  , prevCategory  : "+prevCategory);
				name = (category.getName()!=null?category.getName():"");
				image= (category.getImage()!=null?category.getImage():"");
				prevCategory.setName(name);
				prevCategory.setImage(image);
			}else 
				throw new ResourceNotFoundException("Category", "id", categoryId);
			updatedCategory= categoryRepository.save(prevCategory);
			LOGGER.info(" updatedCategory : "+updatedCategory);
		}
		catch (NoSuchElementException e) {
			throw e;
		}
		catch (Exception e) {
			throw e;
		}
		return updatedCategory;
	}


	public ResponseEntity<?> deleteCategory(Long categoryId){
		LOGGER.info("Exec deleteCategory from categoryService");
		Category category =null;
		try {
			category = categoryRepository.findById(categoryId).get();
			LOGGER.info("Exec updatecategory from categoryService  , categoryId: "+categoryId);
		}catch(Exception e ) {
			LOGGER.error("Exec deleteCategory from categoryService : ERROR  --> "+e);
			throw new ResourceNotFoundException("category", "id", categoryId);
		}

		categoryRepository.delete(category);
		return ResponseEntity.ok().build();
	}
	public Category fetchCategoryByName(String categoryName){
		LOGGER.info("Exec fetchCategoryByName from categoryService");
		Category category =null;
		try {
			category = categoryRepository.findByName(categoryName);
			LOGGER.info("Exec fetchCategoryByName from categoryService  , categoryName: "+categoryName);
		}catch(Exception e ) {
			LOGGER.error("Exec fetchCategoryByName from categoryService : ERROR  --> "+e);
			throw new ResourceNotFoundException("category", "categoryName", categoryName);
		}
		return category;
	}

	public int fetchItemsCountByCategoryName(String categoryName){
		LOGGER.info("Exec fetchItemsCountByCategoryName from categoryService");
		Category category =null;
		int count = 0;
		try {
			category = categoryRepository.findByName(categoryName);
			if(category!=null){
				count = category.getItems().size();
				LOGGER.info("Exec fetchItemsCountByCategoryName from categoryService  , categoryName: "+categoryName);			 
			}
		}catch(Exception e ) {
			LOGGER.error("Exec fetchItemsCountByCategoryName from categoryService : ERROR  --> "+e);
			throw new ResourceNotFoundException("category", "categoryName", categoryName);
		}
		return count;
	}
	
	public List<Item> fetchItemsByCategoryName(String categoryName){
		LOGGER.info("Exec fetchItemsByCategoryName from categoryService");
		Category category =null;
		List<Item> itemsList = null;
		try {
			category = categoryRepository.findByName(categoryName);
			if(category!=null){
				itemsList  = category.getItems();
				LOGGER.info("Exec fetchItemsByCategoryName from categoryService  , categoryName: "+categoryName+", itemsList   :--> "+itemsList  );			 
			}
		}catch(Exception e ) {
			LOGGER.error("Exec fetchItemsCountByCategoryName from categoryService : ERROR  --> "+e);
			throw new ResourceNotFoundException("category", "categoryName", categoryName);
		}
		return itemsList ;
	}

}
