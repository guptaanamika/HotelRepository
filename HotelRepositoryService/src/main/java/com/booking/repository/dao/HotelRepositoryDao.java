package com.booking.repository.dao;

import java.util.List;

import com.booking.repository.entity.HotelInfo;

/**
 * @author anamika.gupta created_on : 16-Feb-2017
 **/
public interface HotelRepositoryDao {

	public String addHotel(HotelInfo hotelInfo);

	public HotelInfo getHotelById(String id);

	public void removeHotelById(String id);

	public void updateHotelById(HotelInfo hotelInfo);

	public List<HotelInfo> getPaginatedHotelSearchList(HotelInfo hotelInfo, Integer pageCount, Integer pageStartPoint);
}
