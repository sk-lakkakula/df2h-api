package com.df2h.lsk.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.df2h.lsk.exception.ResourceNotFoundException;
import com.df2h.lsk.model.Consumer;
import com.df2h.lsk.model.Supplier;
import com.df2h.lsk.repository.SupplierRepository;

@Service("supplierService")
public class SupplierService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	SupplierRepository supplierRepository;

	public Page<Supplier> fetchAllSuppliers(Pageable pageable){
		LOGGER.info("Exec fetchAllSuppliers from SupplierService");
		return supplierRepository.findAll(pageable);
	}

	public Page<Supplier> fetchAllSuppliersByRegistrationStatus(Pageable pageable ,String status){
		LOGGER.info("Exec fetchAllSuppliersByRegistrationStatus from SupplierService");
		return supplierRepository.findByUserRegistrationStatus(pageable, status);
	}

	public Supplier fetchSupplierById(Long supplierId){
		LOGGER.info("Exec fetchSupplierByIdfrom supplierService");
		Supplier supplier =null;
		try {
			supplier = supplierRepository.findById(supplierId).get();
			LOGGER.info("Exec fetchSupplierById from supplierService  , supplierId: "+supplierId);
		}catch(Exception e ) {
			LOGGER.error("Exec fetchSupplierById from supplierService : ERROR  --> "+e);
			throw new ResourceNotFoundException("supplier", "id", supplierId);
		}
		return supplier;
	}

	public List <Consumer> fetchConsumersBySupplierIdFilterByRegStatus(Supplier supplier, String filterByRegStatus){
		LOGGER.info("Exec fetchConsumersBySupplierIdFilterByRegStatus supplierService");
		String userRegistrationStatus = null;
		List <Consumer> consumersList ,filteredList = null;
		Consumer consumer = null;
		if(supplier!=null){
			consumersList  = supplier.getConsumers();
			LOGGER.info("consumersList : "+consumersList);
			if(consumersList!=null && consumersList.size()>0){
				filteredList = new ArrayList<>();
				for (Iterator iterator = consumersList.iterator(); iterator.hasNext();) {
					consumer = (Consumer) iterator.next();
					if(consumer!=null){
						userRegistrationStatus = consumer.getRegistrationStatus();
						if(userRegistrationStatus != null && userRegistrationStatus.toUpperCase() .equalsIgnoreCase(filterByRegStatus.toUpperCase())){
							filteredList.add(consumer);
						}
					}
				}				
			}
			/*
			 * consumersList.sort(Comparator.comparing(Consumer::getFirstName));
			LOGGER.info("consumersList : "+consumersList);
			LOGGER.info("consumersList By supplierId: "+consumersList.size()+" , supplier ID : "+supplier.getId());
			*/
		}
		return filteredList;
	}
	
	
	public List <Consumer> fetchConsumersBySupplierIdApplyFilterAndSort(Supplier supplier, String filterStatus){
		LOGGER.info("Exec fetchConsumersBySupplierId supplierService");
		String userRegistrationStatus = null;
		List <Consumer> consumersList ,filteredSortedList = null;
		Consumer consumer = null;
		if(supplier!=null){
			consumersList  = supplier.getConsumers();
			LOGGER.info("consumersList : "+consumersList);
			if(consumersList!=null && consumersList.size()>0){
				filteredSortedList = new ArrayList<>();
				for (Iterator iterator = consumersList.iterator(); iterator.hasNext();) {
					consumer = (Consumer) iterator.next();
					if(consumer!=null){
						userRegistrationStatus = consumer.getRegistrationStatus();
						if(userRegistrationStatus != null && userRegistrationStatus.toUpperCase() .equalsIgnoreCase(filterStatus.toUpperCase()))	
							filteredSortedList.add(consumer);
					}
				}				
			}
			filteredSortedList.sort(Comparator.comparing(Consumer::getFirstName));
			LOGGER.info("filteredSortedList After Sort : "+filteredSortedList);
			LOGGER.info("filteredSortedListBy supplierId: "+filteredSortedList.size()+" , supplier ID : "+supplier.getId());
		}
		return filteredSortedList;
	}
	
	
	public List <Consumer> fetchConsumersBySupplierId(Supplier supplier){
		LOGGER.info("Exec fetchConsumersBySupplierId supplierService");
		List <Consumer> consumersList = null;
		if(supplier!=null){
			consumersList  = supplier.getConsumers();
			LOGGER.info("consumersList : "+consumersList);
			consumersList.sort(Comparator.comparing(Consumer::getFirstName));
			LOGGER.info("consumersList : "+consumersList);
			LOGGER.info("consumersList By supplierId: "+consumersList.size()+" , supplier ID : "+supplier.getId());
		}
		return consumersList;
	}


	public Supplier saveSupplier(Supplier supplier){
		LOGGER.info("Exec saveSupplier from Supplier Service : "+supplier);
		return supplierRepository.save(supplier);
	}

	public Supplier updateRegistrationStatus(final Long supplierId,String status) {
		LOGGER.info("Exec update RegistrationStatus-  supplier Service" + status);
		Supplier prevSupplier,updatedSupplier=null;
		try {
			prevSupplier = supplierRepository.findById(supplierId).get();
			if(prevSupplier!=null){
				LOGGER.info("Exec update - Supplier : "+supplierId+"  , prevSupplier : "+prevSupplier+ " , status : "+status);
				prevSupplier.setUserRegistrationStatus(status);
			}else 
				throw new ResourceNotFoundException("Supplier", "id", supplierId);
			updatedSupplier = supplierRepository.save(prevSupplier);
			LOGGER.info("Update Registration Status Supplier from supplierService"+updatedSupplier);
		}
		catch (NoSuchElementException e) {
			throw e;
		}
		catch (Exception e) {
			throw e;
		}
		return updatedSupplier;
	}


	public Supplier update(final Long supplierId,Supplier supplier) {
		LOGGER.info("Exec update -  Supplier Service");
		Supplier prevSupplier,updatedSupplier =null;
		String address,contactNo,email,firstName,lastName,password,userRegistrationStatus;
		address=contactNo=email=firstName = lastName=password=lastName = userRegistrationStatus = null;
		try {
			prevSupplier = supplierRepository.findById(supplierId).get();
			if(prevSupplier!=null){
				LOGGER.info("Exec updateAdministrator Supplier Service  , supplierId: "+supplierId+"  , prevSupplier  : "+prevSupplier);
				firstName = (supplier.getFirstName()!=null?supplier.getFirstName():"");
				lastName = (supplier.getLastName()!=null?supplier.getLastName():"");
				contactNo = (supplier.getContactNo()!=null?supplier.getContactNo():"");
				address = (supplier.getAddress()!=null?supplier.getAddress():"");
				email = (supplier.getEmail()!=null?supplier.getEmail():"");
				userRegistrationStatus = (supplier.getUserRegistrationStatus()!=null?supplier.getUserRegistrationStatus():"");
				prevSupplier.setFirstName(firstName);
				prevSupplier.setLastName(lastName);
				prevSupplier.setContactNo(contactNo);
				prevSupplier.setAddress(address);
				prevSupplier.setEmail(email);
				prevSupplier.setUserRegistrationStatus(userRegistrationStatus);
			}else 
				throw new ResourceNotFoundException("Supplier", "id", supplierId);
			updatedSupplier = supplierRepository.save(prevSupplier);
		}
		catch (NoSuchElementException e) {
			throw e;
		}
		catch (Exception e) {
			throw e;
		}
		return updatedSupplier;
	}


	public ResponseEntity<?> deleteSupplier(Long supplierId){
		LOGGER.info("Exec deleteSupplierfrom SupplierService");
		Supplier supplier =null;
		try {
			supplier = supplierRepository.findById(supplierId).get();
			LOGGER.info("Exec deleteItem from itemService  , SupplierId: "+supplierId);
		}catch(Exception e ) {
			LOGGER.error("Exec deleteItem from supplierService : ERROR  --> "+e);
			throw new ResourceNotFoundException("Supplier", "id", supplierId);
		}
		supplierRepository.delete(supplier);
		return ResponseEntity.ok().build();
	}
}
