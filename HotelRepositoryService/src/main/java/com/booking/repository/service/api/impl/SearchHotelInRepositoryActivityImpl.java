package com.booking.repository.service.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.booking.repository.dao.HotelRepositoryDao;
import com.booking.repository.entity.Address;
import com.booking.repository.entity.HotelInfo;
import com.booking.repository.exception.InternalServerException;
import com.booking.repository.service.api.SearchHotelInRepositoryActivity;
import com.booking.repository.service.api.model.SearchHotelInRepositoryRequest;
import com.booking.repository.service.api.model.SearchHotelInRepositoryResponse;

@Service
public class SearchHotelInRepositoryActivityImpl implements SearchHotelInRepositoryActivity {

	@Autowired
	@Qualifier("HotelRepositoryElasticSearchDao")
	private HotelRepositoryDao secondaryDao;

	@Override
	public SearchHotelInRepositoryResponse searchHotelInRepository(
			SearchHotelInRepositoryRequest searchHotelInRepositoryRequest) {

		try {
			HotelInfo hotelInfo = new HotelInfo();
			hotelInfo.setAddress(new Address(null, null, searchHotelInRepositoryRequest.getCity(), null));
			hotelInfo.setName(searchHotelInRepositoryRequest.getName());
			hotelInfo.setStarRanking(searchHotelInRepositoryRequest.getStarRanking());
			List<HotelInfo> hotelInfoList = secondaryDao.getPaginatedHotelSearchList(hotelInfo,
					searchHotelInRepositoryRequest.getPageSize(), searchHotelInRepositoryRequest.getOffset());
			return new SearchHotelInRepositoryResponse(hotelInfoList);
		} catch (Exception e) {
			throw new InternalServerException("Exception occured , msg" + e.getMessage());
		}
	}

}
