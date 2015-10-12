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
@Table(name="Zipcode")
public class Zipcode {
	
	public Zipcode(){
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="zipcodeId", unique=true, nullable=false)
	private int zipcodeId;
	
	@Column(name="zipcode", nullable=false)
	private String zipcode;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cityId", referencedColumnName="cityId", nullable=false)
	private City city;

	public int getZipcodeId() {
		return zipcodeId;
	}

	public void setZipcodeId(int zipcodeId) {
		this.zipcodeId = zipcodeId;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	
}
