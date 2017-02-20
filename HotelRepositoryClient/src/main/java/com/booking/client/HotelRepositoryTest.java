package com.booking.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.booking.repository.entity.Address;
import com.booking.repository.entity.FacilityInfo;
import com.booking.repository.entity.HotelInfo;
import com.booking.repository.service.api.model.AddHotelInformationRequest;
import com.booking.repository.service.api.model.AddHotelInformationResponse;
import com.booking.repository.service.api.model.GetHotelInformationByIdRequest;
import com.booking.repository.service.api.model.GetHotelInformationByIdResponse;
import com.booking.repository.service.api.model.RemoveHotelInformationRequest;
import com.booking.repository.service.api.model.SearchHotelInRepositoryRequest;
import com.booking.repository.service.api.model.SearchHotelInRepositoryResponse;
import com.booking.repository.service.api.model.UpdateHotelInformationRequest;
import com.booking.utils.ClientDetails;

/**
 * @author anamika.gupta created_on : 24-Nov-2016
 **/
public class HotelRepositoryTest {

	static HotelRepositoryClient client;

	public static void main(String[] args) throws Exception {
		ClientDetails clientDetails = new ClientDetails();
		clientDetails.setUrl("http://localhost:8080");
		client = new HotelRepositoryClient(clientDetails);

		// addHotel();
		// getHotelById();
		// searchHotel();
		// updateHotel();
		removeHotel();
		System.out.println(client.getHotelInformationByIdActivity(new GetHotelInformationByIdRequest("-1135025839"))
				.getHotelInfo());
	}

	private static void removeHotel() {
		client.removeHotelInformation(new RemoveHotelInformationRequest("-1135025839"));

	}

	private static void updateHotel() {
		HotelInfo hotelInfo = client.getHotelInformationByIdActivity(new GetHotelInformationByIdRequest("-1135025839"))
				.getHotelInfo();
		hotelInfo.setName("The great Hilton");
		client.updateHotelInformation(new UpdateHotelInformationRequest(hotelInfo));
		System.out.println(client.getHotelInformationByIdActivity(new GetHotelInformationByIdRequest("-1135025839"))
				.getHotelInfo());
	}

	private static void searchHotel() {
		SearchHotelInRepositoryResponse response = client.searchHotelInRepository(new SearchHotelInRepositoryRequest(
				"Hilton", null, null, 10, 0));
		System.out.println(response.toString());
	}

	private static void getHotelById() {
		GetHotelInformationByIdResponse response = client
				.getHotelInformationByIdActivity(new GetHotelInformationByIdRequest("-1135025839"));
		System.out.println(response);
	}

	private static void addHotel() {
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
		AddHotelInformationResponse resposne = client.addHotelInformation(addHotelInformationRequest);
		System.out.println(resposne);
	}

}
