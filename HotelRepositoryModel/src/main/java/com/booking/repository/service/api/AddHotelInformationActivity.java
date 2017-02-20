package com.booking.repository.service.api;

import com.booking.repository.service.api.model.AddHotelInformationRequest;
import com.booking.repository.service.api.model.AddHotelInformationResponse;

/**
 * @author anamika.gupta created_on : 16-Feb-2017
 **/
public interface AddHotelInformationActivity {

	public AddHotelInformationResponse addHotelInformation(AddHotelInformationRequest addHotelInformationRequest);
}
