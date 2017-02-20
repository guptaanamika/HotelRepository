package com.booking.repository.dao.entity;

import com.booking.repository.entity.EditInfo;

import lombok.Data;

@Data
public class HotelFacilityEntity extends EditInfo {

	private String id;

	private Long version;

	private String facilityType;

	private String facilityDesc;
}
