package com.booking.repository.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.booking.repository.converter.HotelFacilityInfoConverter;
import com.booking.repository.dao.HotelFacilitiesRepositoryDao;
import com.booking.repository.dao.entity.HotelFacilityEntity;
import com.booking.repository.entity.HotelFacilityInfo;

/**
 * @author anamika.gupta created_on : 16-Feb-2017
 **/
@Component("HotelFacilitiesRepositoryMysqlDao")
public class HotelFacilitiesRepositoryMysqlDao implements HotelFacilitiesRepositoryDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public void addFacilities(HotelFacilityInfo hotelFaciltyInfo) {
		List<HotelFacilityEntity> hotelfacilityList = HotelFacilityInfoConverter
				.toHotelFacilityEntity(hotelFaciltyInfo);
		for (HotelFacilityEntity entity : hotelfacilityList)
			sqlSession.insert("hotelFacilityRegistration.addFacility", entity);
	}

	public void removeFacilities(String id) {
		sqlSession.delete("hotelFacilityRegistration.removeFacility", id);
	}

	public HotelFacilityInfo getHotelFacilities(String id) {
		List<HotelFacilityEntity> result = sqlSession.selectList("hotelFacilityRegistration.getHotelFacilities", id);
		return HotelFacilityInfoConverter.toHotelFacilityInfo(result);
	}

}
