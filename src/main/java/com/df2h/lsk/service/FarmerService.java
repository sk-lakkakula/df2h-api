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
import com.df2h.lsk.model.Farmer;
import com.df2h.lsk.repository.FarmerRepository;

@Service("farmerService")
public class FarmerService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	FarmerRepository farmerRepository;

	public Page<Farmer> fetchAll(Pageable pageable ){
		LOGGER.info("Exec fetchAllFarmers from farmerService");
		return farmerRepository.findAll(pageable );
	}
	
	public Page<Farmer> fetchAllFarmersByRegistrationStatus(Pageable pageable ,String status){
		LOGGER.info("Exec fetchAllFarmersByRegistrationStatus from SupplierService");
		return farmerRepository.findByRegistrationStatus(pageable, status);
	}
	public Farmer fetchById(Long farmerId){
		LOGGER.info("Exec fetchFarmerById from farmerService");
		Farmer Farmer =null;
		try {
			Farmer = farmerRepository.findById(farmerId).get();
		 LOGGER.info("Exec fetchById from farmerService  , farmerId: "+farmerId);
		}catch(Exception e ) {
			LOGGER.error("Exec fetchById from farmerService : ERROR  --> "+e);
			throw new ResourceNotFoundException("Framer", "id", farmerId);
		}
		return Farmer;
	}
	
	public Farmer save(Farmer Farmer){
		return farmerRepository.save(Farmer);
	}
	
	public Farmer update(final Long farmerId,Farmer farmer) {
		LOGGER.info("Exec update -  farmer Service");
		Farmer prevFarmer,updatedFarmer =null;
		String address,contactNo,email,firstName,lastName,password,registrationStatus;
		address=contactNo=email=firstName = lastName=password=lastName = registrationStatus = null;
		try {
			prevFarmer = farmerRepository.findById(farmerId).get();
			if(prevFarmer!=null){
				LOGGER.info("Exec update - Farmer : "+farmerId+"  , prevFarmer : "+prevFarmer);
				firstName = (farmer.getFirstName()!=null?farmer.getFirstName():"");
				lastName = (farmer.getLastName()!=null?farmer.getLastName():"");
				contactNo = (farmer.getContactNo()!=null?farmer.getContactNo():"");
				address = (farmer.getAddress()!=null?farmer.getAddress():"");
				email = (farmer.getEmail()!=null?farmer.getEmail():"");
				registrationStatus = (farmer.getRegistrationStatus()!=null?farmer.getRegistrationStatus():"");
				
				prevFarmer.setFirstName(firstName);
				prevFarmer.setLastName(lastName);
				prevFarmer.setContactNo(contactNo);
				prevFarmer.setAddress(address);
				prevFarmer.setEmail(email);
				prevFarmer.setRegistrationStatus(registrationStatus);
			}else 
				throw new ResourceNotFoundException("Farmer", "id", farmerId);
			updatedFarmer = farmerRepository.save(prevFarmer);
			LOGGER.info("Update Farmer from farmerService"+updatedFarmer);
		}
		catch (NoSuchElementException e) {
			throw e;
		}
		catch (Exception e) {
			throw e;
		}
		return updatedFarmer;
	}

	public Farmer updateRegistrationStatus(final Long farmerId,String status) {
		LOGGER.info("Exec update RegistrationStatus-  farmer Service" + status);
		Farmer prevFarmer,updatedFarmer =null;
		try {
			prevFarmer = farmerRepository.findById(farmerId).get();
			if(prevFarmer!=null){
				LOGGER.info("Exec update - Farmer : "+farmerId+"  , prevFarmer : "+prevFarmer+ " , status : "+status);
				prevFarmer.setRegistrationStatus(status);
			}else 
				throw new ResourceNotFoundException("Farmer", "id", farmerId);
			updatedFarmer = farmerRepository.save(prevFarmer);
			LOGGER.info("Update Registration Status Farmer from farmerService"+updatedFarmer);
		}
		catch (NoSuchElementException e) {
			throw e;
		}
		catch (Exception e) {
			throw e;
		}
		return updatedFarmer;
	}

	public ResponseEntity<?> delete(Long farmerId){
		LOGGER.info("Exec deletecategory from farmerService");
		Farmer farmer =null;
		try {
			farmer = farmerRepository.findById(farmerId).get();
		 LOGGER.info("Exec deleteFarmer from farmerService  , farmerId: "+farmerId);
		}catch(Exception e ) {
			LOGGER.error("Exec deleteFarmer from farmerService : ERROR  --> "+e);
			throw new ResourceNotFoundException("category", "id", farmerId);
		}
		
		farmerRepository.delete(farmer);
		return ResponseEntity.ok().build();
	}
}
