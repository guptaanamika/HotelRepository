package com.booking.repository.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.booking.repository.dao.HotelRepositoryDao;
import com.booking.repository.exception.InternalServerException;
import com.booking.repository.service.api.UpdateHotelInformationActivity;
import com.booking.repository.service.api.model.UpdateHotelInformationRequest;

@Service
public class UpdateHotelInformationActivityImpl implements UpdateHotelInformationActivity {

	@Autowired
	@Qualifier("HotelRepositoryMysqlDao")
	private HotelRepositoryDao primaryDao;

	@Autowired
	private TransactionalSemantics transactionalSemantics;

	@Override
	public void updateHotelInformation(UpdateHotelInformationRequest request) {

		try {
			transactionalSemantics.update(request.getHotelInfo());
		} catch (Exception e) {
			throw new InternalServerException("Error Occurred , msg" + e.getMessage());
		}
	}

}
