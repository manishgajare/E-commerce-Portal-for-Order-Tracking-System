package com.me.spring.pojo;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="Country")
public class Country {

public Country(){
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="countryId", unique=true, nullable=false)
	private int countryId;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="branchName", nullable=false)
	private String branchName;
	
	@Column(name="address", nullable=false)
	private String address;
	
	@Column(name="email", nullable=false)
	private String email;
	
	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	


}
