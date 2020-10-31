package com.example.demo_user.bean;

import javax.persistence.*;

@Entity
@Table(name="Address")
public class Address {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;

	@Column(name="street")
	private String street;
	
	@Column(name="city")
	private String city;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
}
