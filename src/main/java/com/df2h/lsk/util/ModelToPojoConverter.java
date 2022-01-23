package com.df2h.lsk.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.df2h.lsk.model.Administrator;
import com.df2h.lsk.model.Category;
import com.df2h.lsk.model.Farmer;
import com.df2h.lsk.model.Supplier;
import com.df2h.lsk.pojo.AdministratorPojo;
import com.df2h.lsk.pojo.FarmerPojo;
import com.df2h.lsk.pojo.SupplierPojo;

public class ModelToPojoConverter {

	public static List<com.df2h.lsk.pojo.Category> convertModelToPojo(List<Category> categoryList){
		List<com.df2h.lsk.pojo.Category> categoryPojoList =new ArrayList<>();
		com.df2h.lsk.pojo.Category categoryPojo = null;
		for (Iterator iterator = categoryList.iterator(); iterator.hasNext();) {
			Category category = (Category) iterator.next();
			categoryPojo = new com.df2h.lsk.pojo.Category(category.getId() , category.getName(), category.getImage(), category.getItems().size());
			categoryPojoList.add(categoryPojo);
		}
		return categoryPojoList;
	}

	public static AdministratorPojo convertAdminModelToPojo(Administrator admin){
		AdministratorPojo pojo = null;
		if(admin!=null){
			pojo= new AdministratorPojo(admin);		
		}
		return pojo;
	}
	public static SupplierPojo convertSupplierModelToPojo(Supplier supplier){
		SupplierPojo pojo = null;
		if(supplier!=null){
			pojo= new SupplierPojo(supplier);		
		}
		return pojo;
	}
	public static FarmerPojo convertFarmerModelToPojo(Farmer farmer){
		FarmerPojo pojo = null;
		if(farmer!=null){
			pojo= new FarmerPojo(farmer);		
		}
		return pojo;
	}
}
