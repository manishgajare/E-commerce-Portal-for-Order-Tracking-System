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
@Table(name="Inventory")
public class Inventory {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="inventoryItemId", unique=true, nullable=false)
	private int inventoryItemId;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="productCode", nullable=false)
	private String productCode;
	
	@Column(name="quantityAvailable", nullable=false)
	private int quantityAvailable;
	
	@Column(name="category", nullable=false)
	private String category;
	
	@Column(name="subCategory", nullable=false)
	private String subCategory;
	
	@Column(name="cost", nullable=false)
	private int cost;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="supplierId", referencedColumnName="supplierId", nullable=false)
	private Supplier supplier;

	public int getInventoryItemId() {
		return inventoryItemId;
	}

	public void setInventoryItemId(int inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	
	
		
}
