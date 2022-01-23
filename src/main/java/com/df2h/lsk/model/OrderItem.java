package com.df2h.lsk.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "order_item")
@EntityListeners(AuditingEntityListener.class)
/**
 * 
 * @author slakkakula
 *
 */
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	@Column(name="unit_cost")
	private Integer unitCost;
	@Column(name="quantity_ordered")
	private Integer quantityOrdered;
	@Column(name="total_cost")
	private Float totalCost;
	
	@JsonIgnore	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH},fetch= FetchType.LAZY)
	@JoinColumn(name="order_id")
	private OrderDetails orderDetails;

	public OrderItem() {
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the unitCost
	 */
	public Integer getUnitCost() {
		return unitCost;
	}

	/**
	 * @param unitCost the unitCost to set
	 */
	public void setUnitCost(Integer unitCost) {
		this.unitCost = unitCost;
	}

	/**
	 * @return the quantityOrdered
	 */
	public Integer getQuantityOrdered() {
		return quantityOrdered;
	}

	/**
	 * @param quantityOrdered the quantityOrdered to set
	 */
	public void setQuantityOrdered(Integer quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	
	/**
	 * @return the totalCost
	 */
	public Float getTotalCost() {
		return totalCost;
	}

	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(Float totalCost) {
		this.totalCost = totalCost;
	}

	/**
	 * @return the orderDetails
	 */
	public OrderDetails getOrderDetails() {
		return orderDetails;
	}

	/**
	 * @param orderDetails the orderDetails to set
	 */
	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", name=" + name + ", description=" + description + ", unitCost=" + unitCost
				+ ", quantityOrdered=" + quantityOrdered + ", total_cost=" + totalCost +"]";
	}

	
}