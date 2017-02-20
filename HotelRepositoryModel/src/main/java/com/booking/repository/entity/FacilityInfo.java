package com.booking.repository.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author anamika.gupta created_on : 16-Feb-2017
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacilityInfo {

	private Long version = 1L;

	/**
	 * Facility Type , that will be mentioned under facility List Combination of
	 * id and facility type will be unique.
	 */
	private String faciltyType;

	/**
	 * Detailed points under facility type. Eg : Restaurant 1. Asian & Italian
	 * cuisine. 2. Open terrace
	 * 
	 */
	private List<String> facilityDetailList;
}
