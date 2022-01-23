package com.df2h.lsk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;

@Entity
@Table(name="user_details")
public class UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name="user_name")
    private String userName;
	@Column(name="user_role")
    private String role;
	@Column(name="user_password")
    private String password;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="consumer_id")
	private Consumer consumer;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="administrator_id")
	private Administrator administrator;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="farmer_id")
	private Farmer farmer;
	
	public UserDetails() {
	}
	
    public UserDetails(String userName, String role, String password) {
		this.userName = userName;
		this.role = role;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	@Override
	public String toString() {
		return "UserDetails [userName=" + userName + ", role=" + role + ", password=" + password + "]";
	}
}