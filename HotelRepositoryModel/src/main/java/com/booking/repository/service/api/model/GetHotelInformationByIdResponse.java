package com.booking.repository.service.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.booking.repository.entity.HotelInfo;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetHotelInformationByIdResponse {

	private HotelInfo hotelInfo;
}
