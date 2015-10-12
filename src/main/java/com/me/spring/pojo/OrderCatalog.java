package com.me.spring.pojo;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;

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
@Table(name="OrderCatalog")
public class OrderCatalog {
	
	
public OrderCatalog(){
	
	}
	

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="orderId", unique=true, nullable=false)
private int orderId;

@Column(name="address", nullable=false)
private String address;

@Column(name="orderTime", nullable=false)
private Date orderTime;


@Column(name="orderToBeDeliveredTime", nullable=true)
private Date orderToBeDeliveredTime;

@Column(name="action", nullable=false)
private String action;


public String getAction() {
	return action;
}

public void setAction(String action) {
	this.action = action;
}


@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="customerId", referencedColumnName="customerId", nullable=false)
private Customer customer;

public int getOrderId() {
	return orderId;
}

public void setOrderId(int orderId) {
	this.orderId = orderId;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public Customer getCustomer() {
	return customer;
}

public void setCustomer(Customer customer) {
	this.customer = customer;
}

public Date getOrderTime() {
	return orderTime;
}

public void setOrderTime(Date orderTime) {
	this.orderTime = orderTime;
}

public Date getOrderToBeDeliveredTime() {
	return orderToBeDeliveredTime;
}

public void setOrderToBeDeliveredTime(Date orderToBeDeliveredTime) {
	this.orderToBeDeliveredTime = orderToBeDeliveredTime;
}







}
