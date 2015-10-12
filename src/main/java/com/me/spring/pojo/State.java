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
@Table(name="State")
public class State {
	
	
public State(){
		
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="stateId", unique=true, nullable=false)
	private int stateId;
	
	@Column(name="name", nullable=false)
	private String name;

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="countryId", referencedColumnName="countryId", nullable=false)
	private Country country;

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}



}
