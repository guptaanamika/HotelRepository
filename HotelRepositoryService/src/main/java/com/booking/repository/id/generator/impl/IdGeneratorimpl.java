package com.booking.repository.id.generator.impl;

import org.springframework.stereotype.Component;

import com.booking.repository.entity.HotelInfo;
import com.booking.repository.id.generator.IdGenerator;

@Component
public class IdGeneratorimpl implements IdGenerator {

	@Override
	public String generatedId(HotelInfo hotelInfo) {
		return String.valueOf(hotelInfo.getName().hashCode());
	}

}
