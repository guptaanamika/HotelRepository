package com.booking.repository.dao;

import com.booking.repository.entity.HotelFacilityInfo;

/**
 * @author anamika.gupta created_on : 16-Feb-2017
 **/
public interface HotelFacilitiesRepositoryDao {

	public void addFacilities(HotelFacilityInfo hotelFaciltyInfo);

	public void removeFacilities(String id);

	public HotelFacilityInfo getHotelFacilities(String id);
}
