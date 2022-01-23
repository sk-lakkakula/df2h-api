package com.df2h.lsk.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "order_details")
@EntityListeners(AuditingEntityListener.class)
/**
 * 
 * @author slakkakula
 *
 */
public class OrderDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="order_cost")
	private float orderCost;
	@Column(name="payment_type")
	private String paymentType;
	@Column(name="payment_status")
	private String paymentStatus;
	
	@Column(name="order_status")
	private String orderStatus;
	
	@Column(name="order_date")
	private Date orderDate;
	
	@Column(name="delivery_date")
	private Date deliveryDate;
	
	@Column(name="consumer_id")
	private Long consumerId;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE},mappedBy="orderDetails")
	private List<OrderItem> orderItems;
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
	 * @return the orderItems
	 */
	public List<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	/**
	 * @param orderItems the orderItems to set
	 */
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
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

	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", orderCost=" + orderCost + ", paymentType=" + paymentType
				+ ", paymentStatus=" + paymentStatus + ", orderStatus=" + orderStatus + ", orderDate=" + orderDate
				+ ", deliveryDate=" + deliveryDate + ", consumerId=" + consumerId + ", orderItems=" + orderItems + "]";
	}

	public void addOrderItem(OrderItem newOrderItem) {
		if(this.orderItems==null) {
			this.orderItems = new ArrayList<OrderItem>();	
		}
		this.orderItems.add(newOrderItem);
		newOrderItem.setOrderDetails(this);
	}

}