package com.me.spring.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ZipcodePosition")
public class ZipcodePosition {
	
		
		public ZipcodePosition(){
			
		}
		
		@Column(name="zipcode", nullable=false)
		private String zipcode;

		@Column(name="city", nullable=false)
		private String city;
		
		@Column(name="state", nullable=false)
		private String state;
		
		@Column(name="longitude", nullable=false)
		private String longitude;
		
		@Column(name="latitude", nullable=false)
		private String latitude;
		
		@Column(name="country", nullable=false)
		private String country;
				
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="zipcodePositionId", unique=true, nullable=false)
		private int zipcodePositionId;

		public String getZipcode() {
			return zipcode;
		}

		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		
		

}
