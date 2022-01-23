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
@Table(name = "item")
@EntityListeners(AuditingEntityListener.class)
/**
 * 
 * @author slakkakula
 *
 */
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	@Column(name="photo_url")
	private String photoUrl;
	@Column(name="unit_of_sale")
	private String unitOfSale;
	@Column(name="unit_cost")
	private Float unitCost;
	@Column(name="quantity_ordered")
	private Integer quantityOrdered;
	@Column(name="stock_remaining")
	private Integer stockRemaining;

	@JsonIgnore	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH},fetch= FetchType.LAZY)
	private Category category;

	@JsonIgnore	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH},fetch= FetchType.LAZY)
	@JoinColumn(name="order_id")
	private OrderDetails orderDetails;

	public Item() {
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


	/**
	 * @param surveys the surveys to set
	 */


	public String getName() {
		return name;
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
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Float getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Float unitCost) {
		this.unitCost = unitCost;
	}

	public Integer getStockRemaining() {
		return stockRemaining;
	}

	public void setStockRemaining(Integer stockRemaining) {
		this.stockRemaining = stockRemaining;
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
	 * @return the unitOfSale
	 */
	public String getUnitOfSale() {
		return unitOfSale;
	}


	/**
	 * @param unitOfSale the unitOfSale to set
	 */
	public void setUnitOfSale(String unitOfSale) {
		this.unitOfSale = unitOfSale;
	}


	/**
	 * @return the categoryId
	 *//*
	public Integer getCategoryId() {
		return categoryId;
	}

	  *//**
	  * @param categoryId the categoryId to set
	  *//*
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}*/

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Item [name=" + name + ", description=" + description + ", photoUrl=" + photoUrl + ", unitCost="
				+ unitCost + ", quantityOrdered=" + quantityOrdered + ", stockRemaining=" + stockRemaining + "]";
	}
}