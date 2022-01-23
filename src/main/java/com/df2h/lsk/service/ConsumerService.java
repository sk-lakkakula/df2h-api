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
import com.df2h.lsk.model.Consumer;
import com.df2h.lsk.repository.ConsumerRepository;

@Service("consumerService")
public class ConsumerService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	ConsumerRepository consumerRepository;

	public Page<Consumer> fetchAllConsumers(Pageable pageable){
		LOGGER.info("Exec fetchAllConsumers from itemService");
		return consumerRepository.findAll(pageable);
	}

	public Consumer fetchConsumerById(Long consumerId){
		LOGGER.info("Exec fetchConsumerById from Consumer Service");
		Consumer consumer =null;
		try{
			consumer = consumerRepository.findById(consumerId).get();
			if(consumer!=null){
				LOGGER.info("Exec fetchConsumerById from Consumer Service  , consumerId: "+consumerId +" , consumer : "+consumer);
				return consumer;
			}else
				throw new ResourceNotFoundException("Consumer", "id", consumerId);
		}catch (Exception e) {
			throw new ResourceNotFoundException("Consumer", "id", consumerId);
		}
	}

	public Consumer saveConsumer(Consumer Consumer){
		LOGGER.info("Exec saveConsumer from Consumer Service");
		Consumer temp =null;
		temp = consumerRepository.save(Consumer);
		if(temp!=null){
			LOGGER.info("Success :Saved Consumer from Consumer Service  ");
			return temp;
		}else
			return null;
	}

	/*public Survey saveSurvey(Survey survey ){
		LOGGER.info("Exec saveSurvey from Survey Service");
		Consumer temp =null;
		temp = consumerRepository.save(survey);
		if(temp!=null){
			LOGGER.info("Success :Saved Consumer from Consumer Service  ");
			return temp;
		}else
			return null;
	}*/

	public Consumer updateRegistrationStatus(final Long consumerId,String status) {
		LOGGER.info("Exec update RegistrationStatus-  supplier Service" + status);
		Consumer prevConsumer,updatedConsumer=null;
		try {
			prevConsumer = consumerRepository.findById(consumerId).get();
			if(prevConsumer!=null){
				LOGGER.info("Exec update - Consumer : "+consumerId+"  , prevConsumer : "+prevConsumer+ " , status : "+status);
				prevConsumer.setRegistrationStatus(status);
			}else 
				throw new ResourceNotFoundException("Consumer", "id", consumerId);
			updatedConsumer = consumerRepository.save(prevConsumer);
			LOGGER.info("Update Registration Status Consumer from Consumer "+updatedConsumer);
		}
		catch (NoSuchElementException e) {
			throw e;
		}
		catch (Exception e) {
			throw e;
		}
		return updatedConsumer;
	}
	
	public Consumer update(final Long consumerId,Consumer consumer) {
		LOGGER.info("Exec update  - Consumer Service");
		Consumer prevConsumer,updatedConsumer =null;
		String address,contactNo,email,firstName,lastName,password,registrationStatus;
		address=contactNo=email=firstName = lastName=password=lastName = registrationStatus = null;
		try {
			prevConsumer = consumerRepository.findById(consumerId).get();
			if(prevConsumer!=null){
				LOGGER.info("Exec Update, consumer ID : "+consumerId+"  , prevConsumer  : "+prevConsumer);
				firstName = (consumer.getFirstName()!=null?consumer.getFirstName():"");
				lastName = (consumer.getLastName()!=null?consumer.getLastName():"");
				contactNo = (consumer.getContactNo()!=null?consumer.getContactNo():"");
				address = (consumer.getAddress()!=null?consumer.getAddress():"");
				email = (consumer.getEmail()!=null?consumer.getEmail():"");
				registrationStatus = (consumer.getRegistrationStatus()!=null?consumer.getRegistrationStatus():"");
				prevConsumer.setFirstName(firstName);
				prevConsumer.setLastName(lastName);
				prevConsumer.setContactNo(contactNo);
				prevConsumer.setAddress(address);
				prevConsumer.setEmail(email);
				prevConsumer.setRegistrationStatus(registrationStatus);
			}else 
				throw new ResourceNotFoundException("Consumer", "id", consumerId);
			updatedConsumer = consumerRepository.save(prevConsumer);
		}
		catch (NoSuchElementException e) {
			throw e;
		}
		catch (Exception e) {
			throw e;
		}
		return updatedConsumer;
	}

	public ResponseEntity<?> deleteConsumer(Long consumerId){
		LOGGER.info("Exec deletecategory from itemService");
		Consumer Consumer =null;
		try {
			Consumer = consumerRepository.findById(consumerId).get();
			LOGGER.info("Exec deleteItem from itemService  , consumerId: "+consumerId);
		}catch(Exception e ) {
			LOGGER.error("Exec deleteItem from itemService : ERROR  --> "+e);
			throw new ResourceNotFoundException("category", "id", consumerId);
		}

		consumerRepository.delete(Consumer);
		return ResponseEntity.ok().build();
	}

	public Page<Consumer> fetchAllConsumersByRegistrationStatus(Pageable pageable, String status) {
		LOGGER.info("Exec fetchAllConsumersByRegistrationStatus from ConsumerService");
		return consumerRepository.findByRegistrationStatus(pageable, status);
	}
}
