package com.booking.repository.service.api.impl;

import java.util.Date;

import org.elasticsearch.common.base.Throwables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.booking.repository.dao.HotelRepositoryDao;
import com.booking.repository.entity.HotelFacilityInfo;
import com.booking.repository.entity.HotelInfo;
import com.booking.repository.exception.HotelAlreadyExistException;
import com.booking.repository.exception.InternalServerException;
import com.booking.repository.id.generator.IdGenerator;
import com.booking.repository.service.api.AddHotelInformationActivity;
import com.booking.repository.service.api.model.AddHotelInformationRequest;
import com.booking.repository.service.api.model.AddHotelInformationResponse;

@Service
public class AddHotelInformationActivityImpl implements AddHotelInformationActivity {

	@Autowired
	@Qualifier("HotelRepositoryMysqlDao")
	private HotelRepositoryDao primaryDao;

	@Autowired
	private IdGenerator idGenerator;

	@Autowired
	private TransactionalSemantics transactionSemantics;

	@Override
	public AddHotelInformationResponse addHotelInformation(AddHotelInformationRequest addHotelInformationRequest) {

		try {
			HotelInfo hotelInfo = buildHotelInfoFromRequest(addHotelInformationRequest);
			HotelFacilityInfo hotelFacilityInfo = buildHotelFacilityInfoFromRequest(addHotelInformationRequest);
			String id = idGenerator.generatedId(hotelInfo);

			HotelInfo prevHotelInfo = primaryDao.getHotelById(id);

			if (null != prevHotelInfo)
				throw new HotelAlreadyExistException("Hotel is already present , details : " + prevHotelInfo);

			hotelInfo.setId(id);
			hotelFacilityInfo.setId(id);
			transactionSemantics.insert(hotelInfo, hotelFacilityInfo);

			return new AddHotelInformationResponse(hotelInfo);

		} catch (Exception e) {

			if (e instanceof HotelAlreadyExistException)
				Throwables.propagate(e);
			throw new InternalServerException("Addition of Hotel Failed due to err , " + e.getMessage());
		}
	}

	private HotelFacilityInfo buildHotelFacilityInfoFromRequest(AddHotelInformationRequest addHotelInformationRequest) {
		HotelFacilityInfo hotelFacilityInfo = new HotelFacilityInfo();
		hotelFacilityInfo.setFacilityInfoList(addHotelInformationRequest.getFacilityList());
		hotelFacilityInfo.setCreatedBy(addHotelInformationRequest.getCreatedBy());
		hotelFacilityInfo.setCreatedOn(new Date());
		hotelFacilityInfo.setUpdatedBy(addHotelInformationRequest.getCreatedBy());
		hotelFacilityInfo.setUpdatedOn(new Date());
		return hotelFacilityInfo;
	}

	private HotelInfo buildHotelInfoFromRequest(AddHotelInformationRequest addHotelInformationRequest) {
		HotelInfo hotelInfo = new HotelInfo();
		hotelInfo.setName(addHotelInformationRequest.getName());
		hotelInfo.setVersion(1L);
		hotelInfo.setAddress(addHotelInformationRequest.getAddress());
		hotelInfo.setContact(addHotelInformationRequest.getContact());
		hotelInfo.setStarRanking(addHotelInformationRequest.getStarRanking());
		hotelInfo.setNumberOfRooms(addHotelInformationRequest.getNumberOfRooms());
		hotelInfo.setCreatedBy(addHotelInformationRequest.getCreatedBy());
		hotelInfo.setCreatedOn(new Date());
		hotelInfo.setUpdatedBy(addHotelInformationRequest.getCreatedBy());
		hotelInfo.setUpdatedOn(new Date());
		return hotelInfo;
	}
}
