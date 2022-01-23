package com.df2h.lsk.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.df2h.lsk.exception.ResourceNotFoundException;
import com.df2h.lsk.model.Administrator;
import com.df2h.lsk.repository.AdministratorRepository;

@Service("administratoService")
public class AdministratorService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	AdministratorRepository administratorRepository;

	public List<Administrator> fetchAll(){
		LOGGER.info("Exec fetchAll from administratorService");
		return administratorRepository.findAll();
	}

	public Administrator fetchById(Long administratorId){
		LOGGER.info("Exec fetchById from administratorService");
		Administrator administrator =null;
		try {
			administrator = administratorRepository.findById(administratorId).get();
			LOGGER.info("Exec fetchById from administratorService  , administratorId : "+administratorId);
		}catch(Exception e ) {
			LOGGER.error("Exec fetchById from administratorService : ERROR  --> "+e);
			throw new ResourceNotFoundException("administrator", "id", administratorId);
		}
		return administrator;
	}

	public Administrator saveAdministrator(Administrator administrator){
		LOGGER.info("Exec saveAdministrator from Administrator Service : "+administrator);
		return administratorRepository.save(administrator);
	}

	public Administrator update(final Long administratorId,Administrator administrator) {
		LOGGER.info("Exec updateAdministratorfrom AdministratorService");
		Administrator prevAdministrator,updatedAdministrator =null;
		String address,contactNo,email,firstName,lastName,password;
		address=contactNo=email=firstName = lastName=password=lastName = null;
		try {
			prevAdministrator = administratorRepository.findById(administratorId).get();
			if(prevAdministrator!=null){
				LOGGER.info("Exec updateAdministrator AdministratorService  , AdministratorId: "+administratorId+"  , prevAdministrator  : "+prevAdministrator);
				firstName = (administrator.getFirstName()!=null?administrator.getFirstName():"");
				lastName = (administrator.getLastName()!=null?administrator.getLastName():"");
				contactNo = (administrator.getContactNo()!=null?administrator.getContactNo():"");
				address = (administrator.getAddress()!=null?administrator.getAddress():"");
				email = (administrator.getEmail()!=null?administrator.getEmail():"");
				prevAdministrator.setFirstName(firstName);
				prevAdministrator.setLastName(lastName);
				prevAdministrator.setContactNo(contactNo);
				prevAdministrator.setAddress(address);
				prevAdministrator.setEmail(email);
			}else 
				throw new ResourceNotFoundException("Administrator", "id", administratorId);
			updatedAdministrator = administratorRepository.save(prevAdministrator);
		}
		catch (NoSuchElementException e) {
			throw e;
		}
		catch (Exception e) {
			throw e;
		}
		return updatedAdministrator;
	}

	public ResponseEntity<?> deleteAdministrator(Long administratorId){
		LOGGER.info("Exec deleteAdministratorfrom AdministratorService");
		Administrator administrator =null;
		try {
			administrator = administratorRepository.findById(administratorId).get();
			LOGGER.info("Exec deleteItem from itemService  , administratorId: "+administratorId);
		}catch(Exception e ) {
			LOGGER.error("Exec deleteItem from AdministratorService : ERROR  --> "+e);
			throw new ResourceNotFoundException("Administrator", "id", administratorId);
		}
		administratorRepository.delete(administrator);
		return ResponseEntity.ok().build();
	}
}
