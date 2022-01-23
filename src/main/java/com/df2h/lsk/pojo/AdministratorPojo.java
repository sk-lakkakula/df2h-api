package com.df2h.lsk.pojo;

import java.io.Serializable;

import com.df2h.lsk.model.Administrator;

/**
 * 
 * @author slakkakula
 *
 */
public class AdministratorPojo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String contactNo;
	private String survey;

	public AdministratorPojo(){

	}
	public AdministratorPojo(Administrator administrator){
		this.id= administrator.getId();
		this.firstName= administrator.getFirstName();
		this.lastName= administrator.getLastName();
		this.address= administrator.getAddress();
		this.email= administrator.getEmail();
		this.contactNo= administrator.getContactNo();
		this.survey= null;
	}
	public AdministratorPojo(String firstName, String lastName, String address, String email, String contactNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.contactNo = contactNo;
	}


	/**
	 * @return the survey
	 */
	public String getSurvey() {
		return survey;
	}

	/**
	 * @param survey the survey to set
	 */
	public void setSurvey(String survey) {
		this.survey = survey;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
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

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Administrator [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", contactNo=" + contactNo + ", email=" + email + "]";
	}


}