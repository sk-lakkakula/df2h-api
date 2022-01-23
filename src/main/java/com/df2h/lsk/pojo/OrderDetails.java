package com.df2h.lsk.pojo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * 
 * @author slakkakula
 *
 */
public class OrderDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private float orderCost;
	private String paymentType;
	private String paymentStatus;
	private String orderStatus;
	private Date orderDate;
	private Date deliveryDate;
	private Long consumerId;
	private List<OrderItem> orderItemList;
	
	// NEED TO ADD ENTRY FOR ORDER ITEM LIST
	
	/**
	 * @return the orderCost
	 */
	public float getOrderCost() {
		return orderCost;
	}
	/**
	 * @param orderCost the orderCost to set
	 */
	public void setOrderCost(float orderCost) {
		this.orderCost = orderCost;
	}
	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}
	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * @return the paymentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}
	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the deliveryDate
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	/**
	 * @return the orderItemList
	 */
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	/**
	 * @param orderItemList the orderItemList to set
	 */
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
	
	/**
	 * @return the consumerId
	 */
	public Long getConsumerId() {
		return consumerId;
	}
	/**
	 * @param consumerId the consumerId to set
	 */
	public void setConsumerId(Long consumerId) {
		this.consumerId = consumerId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", orderCost=" + orderCost + ", paymentType=" + paymentType
				+ ", paymentStatus=" + paymentStatus + ", orderStatus=" + orderStatus + ", orderDate=" + orderDate
				+ ", deliveryDate=" + deliveryDate + "]";
	}
	
}