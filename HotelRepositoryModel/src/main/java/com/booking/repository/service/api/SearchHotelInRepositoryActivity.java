package com.booking.repository.service.api;

import com.booking.repository.service.api.model.SearchHotelInRepositoryRequest;
import com.booking.repository.service.api.model.SearchHotelInRepositoryResponse;

/**
 * @author anamika.gupta created_on : 16-Feb-2017
 **/
public interface SearchHotelInRepositoryActivity {

	public SearchHotelInRepositoryResponse searchHotelInRepository(
			SearchHotelInRepositoryRequest searchHotelInRepositoryRequest);
}
