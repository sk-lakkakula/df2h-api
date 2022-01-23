package com.df2h.lsk.service;

import java.util.NoSuchElementException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.df2h.lsk.exception.ResourceNotFoundException;
import com.df2h.lsk.model.Item;
import com.df2h.lsk.repository.ItemRepository;

@Service("itemService")
public class ItemService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	ItemRepository itemRepository;

	public Page<Item> fetchAllItems(Pageable pageable ){
		LOGGER.info("Exec fetchAllItems from itemService");
		return itemRepository.findAll(pageable);
	}

	public Item fetchItemById(Long itemId){
		LOGGER.info("Exec fetchItemById from itemService");
		Item item =null;
		
		try {
			item = itemRepository.findById(itemId).get();
			LOGGER.info("Exec fetchItemById from itemService  , itemId: "+itemId);
		}catch(Exception e ) {
			LOGGER.error("Exec fetchItemById from itemService : ERROR  --> "+e);
			throw new ResourceNotFoundException("Item", "id", itemId);
		}
		return item;
	}

	public Item saveItem(Item item){
		LOGGER.info("Exec saveItem from itemService"+item);
		return itemRepository.save(item);
	}

	public Item updateItem(final Long itemId,Item item) {
		LOGGER.info("Exec update  - Consumer Service");
		Item  prevItem,updatedItem=null;
		String name,description,photoUrl,unitOfSale;
		int stockRemaining =0 ;
		float unitCost ;
		
		name = description = photoUrl = null;
		try {
			prevItem= itemRepository.findById(itemId).get();
			if(prevItem!=null){
				LOGGER.info("Exec Update, Item ID : "+itemId+"  , prevItem : "+prevItem);
				name = (item.getName()!=null?item.getName():"");
				description = (item.getDescription()!=null?item.getDescription():"");
				photoUrl = (item.getPhotoUrl()!=null?item.getPhotoUrl():"");
				unitCost= (item.getUnitCost()!=null?item.getUnitCost():new Float(0));
				stockRemaining = (item.getStockRemaining()!=null?item.getStockRemaining():0);
				unitOfSale = (item.getUnitOfSale()!=null?item.getUnitOfSale():"kg");

				prevItem.setName(name);
				prevItem.setDescription(description);;
				prevItem.setPhotoUrl(photoUrl);
				prevItem.setStockRemaining(stockRemaining);
				prevItem.setUnitCost(unitCost);
				prevItem.setUnitOfSale(unitOfSale);

			}else 
				throw new ResourceNotFoundException("Item ", "id", itemId);
			updatedItem= itemRepository.save(prevItem);
		}
		catch (NoSuchElementException e) {
			throw e;
		}
		catch (Exception e) {
			throw e;
		}
		return updatedItem;
	}


	public ResponseEntity<?> deleteItem(Long itemId){
		LOGGER.info("Exec deletecategory from itemService");
		try {
			LOGGER.info("Exec deleteItem from itemService  , itemId: "+itemId);
			itemRepository.deleteById(itemId);
			return ResponseEntity.ok().build();
		}catch(Exception e ) {
			LOGGER.error("Exec deleteItem from itemService : ERROR  --> "+e);
			throw new ResourceNotFoundException("category", "id", itemId);
		}
	}

}
