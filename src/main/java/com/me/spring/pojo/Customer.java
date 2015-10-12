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
@Table(name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="customerId", unique=true, nullable=false)
	private int customerId;
	
	
	
	@Column(name="username", unique=true, nullable=false)
    private String username;
     
    @Column(name="password", nullable=false)
    private String password;
	
	
	@Column(name="firstName", nullable=false)
    private String firstName;
     
    @Column(name="lastName", nullable=false)
    private String lastName;
	
    @Column(name="phoneNumber", nullable=false)
    private String phoneNumber;

    @Column(name="address", nullable=false)
    private String address;

    @Column(name="email", nullable=false)
    private String email;

    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="countryId", referencedColumnName="countryId", nullable=false)
	private Country country;


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirsName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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


	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}

	
	
}
