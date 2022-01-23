package com.df2h.lsk.pojo;

import java.io.Serializable;

import com.df2h.lsk.model.Supplier;
/**
 * 
 * @author slakkakula
 *
 */
public class ConsumerBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConsumerBean(){
	}
	public ConsumerBean(String consumerName, String consumerAddress, String contactNo, String consumerEmail,
			String userRegistrationStatus) {
		super();
		this.consumerName = consumerName;
		this.consumerAddress = consumerAddress;
		this.contactNo = contactNo;
		this.consumerEmail = consumerEmail;
		this.userRegistrationStatus = userRegistrationStatus;
	}

	private String consumerName;
	private String consumerAddress;
	private String contactNo;
	private String consumerEmail;
	private String userRegistrationStatus;
	private Long supplierId;

	
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public String getConsumerName() {
		return consumerName;
	}
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	public String getConsumerAddress() {
		return consumerAddress;
	}
	public void setConsumerAddress(String consumerAddress) {
		this.consumerAddress = consumerAddress;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getConsumerEmail() {
		return consumerEmail;
	}
	public void setConsumerEmail(String consumerEmail) {
		this.consumerEmail = consumerEmail;
	}
	public String getUserRegistrationStatus() {
		return userRegistrationStatus;
	}
	public void setUserRegistrationStatus(String userRegistrationStatus) {
		this.userRegistrationStatus = userRegistrationStatus;
	}
	@Override
	public String toString() {
		return "ConsumerBean [consumerName=" + consumerName + ", consumerAddress=" + consumerAddress + ", contactNo="
				+ contactNo + ", consumerEmail=" + consumerEmail + ", userRegistrationStatus=" + userRegistrationStatus
				+ ", supplierId=" + supplierId + "]";
	}

}