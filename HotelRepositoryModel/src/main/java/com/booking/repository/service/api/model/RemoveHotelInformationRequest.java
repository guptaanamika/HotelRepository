package com.booking.repository.service.api.model;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author anamika.gupta created_on : 16-Feb-2017
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoveHotelInformationRequest {

	@NotBlank
	private String id;
}
