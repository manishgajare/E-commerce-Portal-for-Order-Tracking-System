package com.me.spring.pojo;

import java.sql.Date;

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
@Table(name="OrderItem")
public class OrderItem {

	public OrderItem(){
		
	}
	//
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="orderItemId", unique=true, nullable=false)
	private int orderItemId;
//
	@Column(name="cost", nullable=false)
	private String cost;
	//
	@Column(name="quantity", nullable=false)
	private String quantity;
	//
	@Column(name="confirmationCode", nullable=true)
	private String confirmationCode;
	//null
	@Column(name="actualCostPaid", nullable=true)
	private String actualCostPaid;
	//
	@Column(name="commision", nullable=true)
	private String commision;
	
	//-
	@Column(name="penaltyFlag", nullable=true)
	private String penaltyFlag;
	
	@Column(name="actualDeliveryTime", nullable=true)
	private Date actualDeliveryTime;

		
	//
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="orderId", referencedColumnName="orderId", nullable=false)
	private OrderCatalog orderCatalog;
	//
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="inventoryItemId", referencedColumnName="inventoryItemId", nullable=false)
	private Inventory inventory;
	//
	@Column(name="statusOfOrderItem", nullable=false)
	private String statusOfOrderItem;
//
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="financeEmployeeId", referencedColumnName="employeeId", nullable=true)
	private Employee financeEmployee;
//
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="salesEmployeeId", referencedColumnName="employeeId", nullable=true)
	private Employee salesEmployee;
//
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="shippingEmployeeId", referencedColumnName="employeeId", nullable=true)
	private Employee shippingEmployee;
//
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="salesReturnManagementEmployeeId", referencedColumnName="employeeId", nullable=true)
	private Employee salesReturnManagementEmployee;
//
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="financeReturnManagementEmployeeId", referencedColumnName="employeeId", nullable=true)
	private Employee financeReturnManagementEmployee;
	
	
	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public OrderCatalog getOrderCatalog() {
		return orderCatalog;
	}

	public void setOrderCatalog(OrderCatalog orderCatalog) {
		this.orderCatalog = orderCatalog;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}



	public String getStatusOfOrderItem() {
		return statusOfOrderItem;
	}

	public void setStatusOfOrderItem(String statusOfOrderItem) {
		this.statusOfOrderItem = statusOfOrderItem;
	}

	public Employee getFinanceEmployee() {
		return financeEmployee;
	}

	public void setFinanceEmployee(Employee financeEmployee) {
		this.financeEmployee = financeEmployee;
	}

	public Employee getSalesEmployee() {
		return salesEmployee;
	}

	public void setSalesEmployee(Employee salesEmployee) {
		this.salesEmployee = salesEmployee;
	}

	public Employee getShippingEmployee() {
		return shippingEmployee;
	}

	public void setShippingEmployee(Employee shippingEmployee) {
		this.shippingEmployee = shippingEmployee;
	}

	public Employee getSalesReturnManagementEmployee() {
		return salesReturnManagementEmployee;
	}

	public void setSalesReturnManagementEmployee(
			Employee salesReturnManagementEmployee) {
		this.salesReturnManagementEmployee = salesReturnManagementEmployee;
	}

	public Employee getFinanceReturnManagementEmployee() {
		return financeReturnManagementEmployee;
	}

	public void setFinanceReturnManagementEmployee(
			Employee financeReturnManagementEmployee) {
		this.financeReturnManagementEmployee = financeReturnManagementEmployee;
	}

	public String getActualCostPaid() {
		return actualCostPaid;
	}

	public void setActualCostPaid(String actualCostPaid) {
		this.actualCostPaid = actualCostPaid;
	}

	public String getPenaltyFlag() {
		return penaltyFlag;
	}

	public void setPenaltyFlag(String penaltyFlag) {
		this.penaltyFlag = penaltyFlag;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public String getCommision() {
		return commision;
	}

	public void setCommision(String commision) {
		this.commision = commision;
	}

	public Date getActualDeliveryTime() {
		return actualDeliveryTime;
	}

	public void setActualDeliveryTime(Date actualDeliveryTime) {
		this.actualDeliveryTime = actualDeliveryTime;
	}

	
	
	
	
}
