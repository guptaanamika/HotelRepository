package com.booking.repository.service.impl;

import lombok.experimental.Delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.booking.repository.service.IngestionService;
import com.booking.repository.service.SearchService;
import com.booking.repository.service.api.AddHotelInformationActivity;
import com.booking.repository.service.api.GetHotelInformationByIdActivity;
import com.booking.repository.service.api.RemoveHotelInformationActivity;
import com.booking.repository.service.api.SearchHotelInRepositoryActivity;
import com.booking.repository.service.api.UpdateHotelInformationActivity;

@Component
public class ServiceImpl implements SearchService, IngestionService {

	@Autowired
	@Delegate
	private AddHotelInformationActivity addHotelInformationActivity;

	@Autowired
	@Delegate
	private RemoveHotelInformationActivity removeHotelInformationActivity;

	@Autowired
	@Delegate
	UpdateHotelInformationActivity UpdateHotelInformationActivity;

	@Autowired
	@Delegate
	GetHotelInformationByIdActivity GetHotelInformationByIdActivity;

	@Autowired
	@Delegate
	SearchHotelInRepositoryActivity SearchHotelInRepositoryActivity;
}
