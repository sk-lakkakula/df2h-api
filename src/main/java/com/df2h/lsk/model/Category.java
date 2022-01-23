package com.df2h.lsk.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="category")
public class Category implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@OneToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE})
	@JsonIgnore
	@JoinColumn(name="category_id")
	private List<Item> items;

	@Column(name="name")
	private String name;
	
	@Column(name="image")
	private String image;

	private static final long serialVersionUID = 1L;

	Category(){
	}

	public Category(String name, String image,List<Item> items) {
		this.name = name;
		this.image = image;
		this.items = items;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
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

	public void add(Item item) {
		if (items == null) {
			items = new ArrayList<>();
		}
		items.add(item);
//		item.setCategory(this);
	}

	@Override
	public String toString() {
		return "Category [" + "name=" + name + ", image=" + image ;
	}

}