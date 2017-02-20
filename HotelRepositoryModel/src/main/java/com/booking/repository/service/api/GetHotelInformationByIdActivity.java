package com.booking.repository.service.api;

import com.booking.repository.service.api.model.GetHotelInformationByIdRequest;
import com.booking.repository.service.api.model.GetHotelInformationByIdResponse;

public interface GetHotelInformationByIdActivity {

	public GetHotelInformationByIdResponse getHotelInformationByIdActivity(
			GetHotelInformationByIdRequest request);
}
