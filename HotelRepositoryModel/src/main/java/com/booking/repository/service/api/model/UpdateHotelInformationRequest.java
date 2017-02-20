package com.booking.repository.service.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.booking.repository.entity.HotelInfo;

/**
 * @author anamika.gupta created_on : 16-Feb-2017
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateHotelInformationRequest {

	private HotelInfo hotelInfo;
}
