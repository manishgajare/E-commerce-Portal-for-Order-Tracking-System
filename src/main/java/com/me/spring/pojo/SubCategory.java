package com.me.spring.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="SubCategory")
public class SubCategory {
	
	
	public SubCategory() {
		
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="subCategoryId", unique=true, nullable=false)
	private int subCategoryId;
	
	@Column(name="name", nullable=false)
	private String name;

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="categoryId", referencedColumnName="categoryId", nullable=false)
	private Category category;


	


	public int getSubCategoryId() {
		return subCategoryId;
	}


	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	
	
	

}
