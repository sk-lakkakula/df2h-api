package com.df2h.lsk.pojo;
import java.io.Serializable;

/**
 * 
 * @author slakkakula
 *
 */
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String description;
	private Integer unitCost;
	private Integer quantityOrdered;
	private Float totalCost;
	
	private OrderDetails orderDetails;

	public OrderItem() {
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
				+ ", quantityOrdered=" + quantityOrdered + ", totalCost=" + totalCost 
				+ "]";
	}

	
}