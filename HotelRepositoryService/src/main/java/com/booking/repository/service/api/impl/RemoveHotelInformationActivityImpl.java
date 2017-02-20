package com.booking.repository.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.repository.exception.InternalServerException;
import com.booking.repository.service.api.RemoveHotelInformationActivity;
import com.booking.repository.service.api.model.RemoveHotelInformationRequest;

@Service
public class RemoveHotelInformationActivityImpl implements RemoveHotelInformationActivity {

	@Autowired
	private TransactionalSemantics transactionSemantics;

	@Override
	public void removeHotelInformation(RemoveHotelInformationRequest removeHotelInformationRequest) {
		try {
			transactionSemantics.delete(removeHotelInformationRequest.getId());
		} catch (Exception e) {
			throw new InternalServerException("Error Occurred , msg : " + e.getMessage());
		}
	}

}
