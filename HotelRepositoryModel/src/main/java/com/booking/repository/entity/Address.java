package com.booking.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author anamika.gupta created_on : 16-Feb-2017
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	private String blockNo;

	private String street;

	private String city;

	private String country;
}
