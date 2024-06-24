package com.starter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String City;
	private int pincode;
	
	public Address() {}

	public String getCity() {
		return City;
	}

	public void setCity(String City) {
		this.City = City;
	}

	public int getPincode() {
		return pincode;
	}

	@Override
	public String toString() {
		return "Address [Id=" + Id + ", City=" + City + ", pincode=" + pincode + "]";
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public Address(String City, int pincode) {
		super();
		this.City = City;
		this.pincode = pincode;
	}
}
