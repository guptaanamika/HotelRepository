package com.booking.repository.id.generator;

import com.booking.repository.entity.HotelInfo;

public interface IdGenerator {

	public String generatedId(HotelInfo hotelInfo);
}
