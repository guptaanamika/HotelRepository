package com.booking.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author anamika.gupta created_on : 16-Feb-2017
 **/
@Data
@NoArgsConstructor
public class Address {

	private String blockNo;

	private String street;

	private String city;

	private String country;

	public String getZipcode;

	public Address(String blockNo, String street, String city, String country) {
		this.blockNo = blockNo;
		this.street = street;
		this.city = city;
		this.country = country;
	}
}
