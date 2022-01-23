package com.df2h.lsk.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "consumer")
/**
 * 
 * @author slakkakula
 *
 */
public class Consumer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	@Column(name="contact_no")
	private String contactNo;
	
	@Column(name="email")
	private String email;
	
	@Column(name="registration_status")
	private String registrationStatus;
	
	@JsonIgnore
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	/*
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="consumer_item",
			joinColumns=@JoinColumn(name="consumer_id"),
			inverseJoinColumns=@JoinColumn(name="item_id")
			)
	*/
	/*@JsonIgnore
	private List<Item> items;
	*/
	/**
	 * @return the items
	 */
	/*public List<Item> getItems() {
		return items;
	}
	*//**
	 * @param items the items to set
	 *//*
	public void setItems(List<Item> items) {
		this.items = items;
	}*/
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
	public String getRegistrationStatus() {
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
	 * @return the supplier
	 */
	@ManyToOne(cascade=CascadeType.ALL)
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Consumer [ firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", contactNo=" + contactNo + ", email=" + email + ", userRegistrationStatus=" + registrationStatus
				+ ", supplier=" + supplier +"]";
	}

	



}