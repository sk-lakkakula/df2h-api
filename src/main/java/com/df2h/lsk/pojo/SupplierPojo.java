package com.df2h.lsk.pojo;

import java.io.Serializable;
import java.util.List;

import com.df2h.lsk.model.Consumer;
import com.df2h.lsk.model.Supplier;

public class SupplierPojo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SupplierPojo(Supplier supplier){
		this.id = supplier.getId();
		this.firstName = supplier.getFirstName();
		this.lastName = supplier.getLastName();
		this.address  = supplier.getAddress();
		this.email = supplier.getEmail();
		this.contactNo = supplier.getContactNo();
		this.consumers = supplier.getConsumers();
	}

	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String contactNo;
	private String registrationStatus;
	private List<Consumer> consumers;



	/**
	 * @return the registrationStatus
	 */
	public String getRegistrationStatus() {
		return registrationStatus;
	}

	/**
	 * @param registrationStatus the registrationStatus to set
	 */
	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public void setConsumers(List<Consumer> consumers) {
		this.consumers = consumers;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the consumers
	 */
	public List<Consumer> getConsumers() {
		return consumers;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Supplier [password=" +  ", lastName=" + lastName + ", firstName=" + firstName + ", address="
				+ address + ", email=" + email + ", contactNo=" + contactNo + ", registrationStatus="
				+ registrationStatus + "]";
	}


}