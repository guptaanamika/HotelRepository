package com.booking.repository.service.api.model;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetHotelInformationByIdRequest {

	@NotBlank
	private String id;
}
