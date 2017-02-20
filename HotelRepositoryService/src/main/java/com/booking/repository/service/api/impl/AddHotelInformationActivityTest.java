package com.booking.repository.service.api.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.booking.repository.entity.Address;
import com.booking.repository.entity.FacilityInfo;
import com.booking.repository.exception.HotelAlreadyExistException;
import com.booking.repository.service.api.model.AddHotelInformationRequest;
import com.booking.repository.service.api.model.AddHotelInformationResponse;
import com.booking.repository.service.api.model.GetHotelInformationByIdRequest;
import com.booking.repository.service.api.model.GetHotelInformationByIdResponse;
import com.booking.repository.service.api.model.SearchHotelInRepositoryRequest;
import com.booking.repository.service.api.model.SearchHotelInRepositoryResponse;

@ContextConfiguration("classpath:spring/application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AddHotelInformationActivityTest {

	@Autowired
	private AddHotelInformationActivityImpl addHotelInformationActivity;

	@Autowired
	private GetHotelInformationByIdActivityImpl getHotelInformationByIdActivity;

	@Autowired
	private SearchHotelInRepositoryActivityImpl searchHotelInRepositoryActivity;

	@Test
	public void positiveTest() {

		AddHotelInformationRequest addHotelInformationRequest = new AddHotelInformationRequest();
		addHotelInformationRequest.setName("The Hilton great great");
		addHotelInformationRequest.setStarRanking(4);
		addHotelInformationRequest.setNumberOfRooms(4);
		List<FacilityInfo> facilityList = new ArrayList<FacilityInfo>();
		facilityList.add(new FacilityInfo(null, "Swimming Pool", Arrays.asList("openpool", "pets allowed")));
		addHotelInformationRequest.setFacilityList(facilityList);
		addHotelInformationRequest.setCreatedBy("Anamika");
		addHotelInformationRequest.setContact("2131212341241");
		addHotelInformationRequest.setAddress(new Address("4th Block", "2nd Street", "London", "England"));

		AddHotelInformationResponse response = addHotelInformationActivity
				.addHotelInformation(addHotelInformationRequest);

		GetHotelInformationByIdResponse hotelInfo = getHotelInformationByIdActivity
				.getHotelInformationByIdActivity(new GetHotelInformationByIdRequest(response.getHotelInfo().getId()));

		Assert.assertNotNull(hotelInfo.getHotelInfo());

		SearchHotelInRepositoryResponse searchInfo = searchHotelInRepositoryActivity
				.searchHotelInRepository(new SearchHotelInRepositoryRequest("Hilton", null, null, 10, 0));

		Assert.assertTrue(searchInfo.getHotelinfoList().size() == 1);
	}

	@Test(expected = HotelAlreadyExistException.class)
	public void concurrentAddition() {

		AddHotelInformationRequest addHotelInformationRequest = new AddHotelInformationRequest();
		addHotelInformationRequest.setName("The Hilton grand great");
		addHotelInformationRequest.setStarRanking(4);
		addHotelInformationRequest.setNumberOfRooms(4);
		List<FacilityInfo> facilityList = new ArrayList<FacilityInfo>();
		facilityList.add(new FacilityInfo(null, "Swimming Pool", Arrays.asList("openpool", "pets allowed")));
		addHotelInformationRequest.setFacilityList(facilityList);
		addHotelInformationRequest.setCreatedBy("Anamika");
		addHotelInformationRequest.setContact("2131212341241");
		addHotelInformationRequest.setAddress(new Address("4th Block", "2nd Street", "London", "England"));

		AddHotelInformationResponse response = null;
		try {
			response = addHotelInformationActivity.addHotelInformation(addHotelInformationRequest);
			addHotelInformationActivity.addHotelInformation(addHotelInformationRequest);
		} finally {
			GetHotelInformationByIdResponse hotelInfo = getHotelInformationByIdActivity
					.getHotelInformationByIdActivity(new GetHotelInformationByIdRequest(response.getHotelInfo().getId()));

			Assert.assertNotNull(hotelInfo.getHotelInfo());

			SearchHotelInRepositoryResponse searchInfo = searchHotelInRepositoryActivity
					.searchHotelInRepository(new SearchHotelInRepositoryRequest("grand", null, null, 10, 0));

			// This assertion proves there was only 1 insertion in elastic
			// search as well
			Assert.assertTrue(searchInfo.getHotelinfoList().size() == 1);
		}
	}
}
