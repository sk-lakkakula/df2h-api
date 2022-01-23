package com.df2h.lsk.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "supplier")
/**
 * 
 * @author slakkakula
 *
 */
public class Supplier implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Supplier(){

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;


	@Column(name="address")
	private String address;

	@Column(name="email")
	private String email;

	@Column(name="contact_no")
	private String contactNo;


	@Column(name="registration_status")
	private String userRegistrationStatus;
	@JsonIgnore
	@OneToMany(mappedBy="supplier",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<Consumer> consumers;


	/**
	 * @return the userRegistrationStatus
	 */
	public String getUserRegistrationStatus() {
		return userRegistrationStatus;
	}

	/**
	 * @param userRegistrationStatus the userRegistrationStatus to set
	 */
	public void setUserRegistrationStatus(String userRegistrationStatus) {
		this.userRegistrationStatus = userRegistrationStatus;
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

	public void add(Consumer tempConsumer) {
		if (consumers== null) {
			consumers= new ArrayList<>();
		}
		consumers.add(tempConsumer);
		tempConsumer.setSupplier(this);
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
				+ userRegistrationStatus + "]";
	}


}