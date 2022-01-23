package com.df2h.lsk.pojo;

import java.io.Serializable;

import com.df2h.lsk.model.Farmer;

/**
 * 
 * @author slakkakula
 *
 */
public class FarmerPojo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FarmerPojo(){
	}
	public FarmerPojo(Farmer farmer){
		this.id = farmer.getId();
		this.firstName= farmer.getFirstName();
		this.lastName = farmer.getLastName();
		this.address = farmer.getAddress();
		this.email = farmer.getEmail();
		this.contactNo = farmer.getContactNo();
		this.registrationStatus = farmer.getRegistrationStatus();
	}

	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String contactNo;
	private String registrationStatus;


	public FarmerPojo(String firstName, String lastName, String address, String email, String contactNo,
			String registrationStatus) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.contactNo = contactNo;
		this.registrationStatus = registrationStatus;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserRegistrationStatus() {
		return registrationStatus;
	}
	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Farmer [address=" + address + ", contactNo=" + contactNo + ", consumerEmail=" + email
				+ ", userRegistrationStatus=" + registrationStatus + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}

}