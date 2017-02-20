package com.booking.repository.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.booking.repository.dao.HotelRepositoryDao;
import com.booking.repository.entity.HotelInfo;
import com.booking.repository.exception.InternalServerException;
import com.booking.repository.service.api.GetHotelInformationByIdActivity;
import com.booking.repository.service.api.model.GetHotelInformationByIdRequest;
import com.booking.repository.service.api.model.GetHotelInformationByIdResponse;

@Service
public class GetHotelInformationByIdActivityImpl implements GetHotelInformationByIdActivity {

	@Autowired
	@Qualifier("HotelRepositoryMysqlDao")
	private HotelRepositoryDao primaryDao;

	@Override
	public GetHotelInformationByIdResponse getHotelInformationByIdActivity(GetHotelInformationByIdRequest request) {
		try {
			HotelInfo hotelInfo = primaryDao.getHotelById(request.getId());
			return new GetHotelInformationByIdResponse(hotelInfo);
		} catch (Exception e) {
			throw new InternalServerException("Error Occurred , msg" + e.getMessage());
		}
	}

}
