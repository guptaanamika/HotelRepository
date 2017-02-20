package com.booking.repository.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.booking.repository.converter.HotelInfoConverter;
import com.booking.repository.dao.HotelRepositoryDao;
import com.booking.repository.dao.entity.HotelEntity;
import com.booking.repository.entity.HotelInfo;

/**
 * @author anamika.gupta created_on : 16-Feb-2017
 **/
@Component("HotelRepositoryMysqlDao")
public class HotelRepositoryMysqlDao implements HotelRepositoryDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public String addHotel(HotelInfo hotelInfo) {
		HotelEntity hotelEntity = HotelInfoConverter.toHotelEntity(hotelInfo);
		sqlSession.insert("hotelRegistration.addHotel", hotelEntity);
		return hotelInfo.getId();
	}

	public HotelInfo getHotelById(String id) {
		HotelEntity hotelEntity = sqlSession.selectOne("hotelRegistration.getHotelById", id);
		return HotelInfoConverter.toHotelInfo(hotelEntity);
	}

	public void removeHotelById(String id) {
		sqlSession.delete("hotelRegistration.removeHotelById", id);
	}

	public void updateHotelById(HotelInfo hotelInfo) {
		HotelEntity hotelEntity = HotelInfoConverter.toHotelEntity(hotelInfo);
		sqlSession.update("hotelRegistration.updateHotelById", hotelEntity);
	}

	@Override
	public List<HotelInfo> getPaginatedHotelSearchList(HotelInfo hotelInfo, Integer pageCount, Integer pageStartPoint) {
		throw new RuntimeException("Not expected to fetch result from this part of code/method");
	}

}
