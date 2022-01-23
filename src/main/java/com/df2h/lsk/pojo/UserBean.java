package com.df2h.lsk.pojo;

public class UserBean {
	private String name;
    private String role;
    private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String contactNo;
	private String userRegistrationStatus;
	private Long supplierId;

	/**
	 * @return the supplierId
	 */
	public Long getSupplierId() {
		return supplierId;
	}
	/**
	 * @param supplierId the supplierId to set
	 */
	public void setSupplierId(Long  supplierId) {
		this.supplierId = supplierId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getUserRegistrationStatus() {
		return userRegistrationStatus;
	}
	public void setUserRegistrationStatus(String userRegistrationStatus) {
		this.userRegistrationStatus = userRegistrationStatus;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public UserBean() {
		super();
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserBean [name=" + name + ", role=" + role + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + ", email=" + email + ", address=" + address + ", contactNo=" + contactNo
				+ ", userRegistrationStatus=" + userRegistrationStatus + ", supplierId=" + supplierId + "]";
	}

}
