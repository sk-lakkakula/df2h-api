package com.df2h.lsk.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.df2h.lsk.model.Item;

public class Category implements Serializable {
	private Long id;

	private int  itemsCount;

	private String name;
	
	private String image;

	private static final long serialVersionUID = 1L;

	Category(){
	}

	public Category(Long id , String name, String image,int itemsCount) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.itemsCount= itemsCount;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	/**
	 * @return the itemsCount
	 */
	public int getItemsCount() {
		return itemsCount;
	}

	/**
	 * @param itemsCount the itemsCount to set
	 */
	public void setItemsCount(int itemsCount) {
		this.itemsCount = itemsCount;
	}

	@Override
	public String toString() {
		return "Category [" + "name=" + name + ", image=" + image + ",Items Count :]"+itemsCount;
	}

}