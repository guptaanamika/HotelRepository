package com.booking.repository.service.api.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.hibernate.validator.constraints.NotBlank;

import com.booking.repository.entity.Address;
import com.booking.repository.entity.FacilityInfo;

/**
 * @author anamika.gupta created_on : 16-Feb-2017
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddHotelInformationRequest {

	@NotBlank
	private String name;

	@NonNull
	private Address address;

	@NotBlank
	private String contact;

	@NotNull
	private Integer starRanking;

	@NotNull
	private Integer numberOfRooms;

	private List<FacilityInfo> facilityList;

	@NotNull
	private String createdBy;
}
