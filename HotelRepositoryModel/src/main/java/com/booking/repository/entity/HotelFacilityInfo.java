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
public class HotelFacilityInfo extends EditInfo {

	/**
	 * Unique Hotel identifier
	 */
	private String id;

	private List<FacilityInfo> facilityInfoList;

}
