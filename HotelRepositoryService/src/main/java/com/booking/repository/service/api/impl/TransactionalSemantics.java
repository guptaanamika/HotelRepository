package com.booking.repository.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.booking.repository.dao.HotelFacilitiesRepositoryDao;
import com.booking.repository.dao.HotelRepositoryDao;
import com.booking.repository.entity.HotelFacilityInfo;
import com.booking.repository.entity.HotelInfo;

/**
 * This class has been made purposely to achieve atomicity among transactions
 * through mysql transactional semantics.
 * 
 * @author anamika.gupta
 *
 */
@Component
public class TransactionalSemantics {

	@Autowired
	@Qualifier("HotelRepositoryMysqlDao")
	private HotelRepositoryDao primaryDao;

	@Autowired
	@Qualifier("HotelFacilitiesRepositoryMysqlDao")
	private HotelFacilitiesRepositoryDao primaryFacilityDao;

	@Autowired
	@Qualifier("HotelRepositoryElasticSearchDao")
	private HotelRepositoryDao secondaryDao;

	@Transactional
	public void insert(HotelInfo hotelInfo, HotelFacilityInfo hotelFacilityInfo) {
		primaryDao.addHotel(hotelInfo);
		primaryFacilityDao.addFacilities(hotelFacilityInfo);
		secondaryDao.addHotel(hotelInfo);
	}

	@Transactional
	public void delete(String id) {
		primaryFacilityDao.removeFacilities(id);
		primaryDao.removeHotelById(id);
		secondaryDao.removeHotelById(id);
	}

	@Transactional
	public void update(HotelInfo hotelInfo) {
		primaryDao.updateHotelById(hotelInfo);
		hotelInfo.setVersion(hotelInfo.getVersion() + 1);
		secondaryDao.updateHotelById(hotelInfo);
	}
}
