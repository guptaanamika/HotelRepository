package com.booking.repository.service;

import com.booking.repository.service.api.AddHotelInformationActivity;
import com.booking.repository.service.api.GetHotelInformationByIdActivity;
import com.booking.repository.service.api.RemoveHotelInformationActivity;
import com.booking.repository.service.api.UpdateHotelInformationActivity;

/**
 * @author anamika.gupta created_on : 16-Feb-2017
 **/
public interface IngestionService extends AddHotelInformationActivity, RemoveHotelInformationActivity,
		UpdateHotelInformationActivity,GetHotelInformationByIdActivity {

}
