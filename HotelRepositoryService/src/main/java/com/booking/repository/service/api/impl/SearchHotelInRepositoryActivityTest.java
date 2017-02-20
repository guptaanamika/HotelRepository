package com.booking.repository.service.api.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.booking.repository.entity.Address;
import com.booking.repository.entity.FacilityInfo;
import com.booking.repository.service.api.model.AddHotelInformationRequest;
import com.booking.repository.service.api.model.AddHotelInformationResponse;
import com.booking.repository.service.api.model.SearchHotelInRepositoryRequest;
import com.booking.repository.service.api.model.SearchHotelInRepositoryResponse;

@ContextConfiguration("classpath:spring/application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SearchHotelInRepositoryActivityTest {

	@Autowired
	private AddHotelInformationActivityImpl addHotelInformationActivity;

	@Autowired
	private SearchHotelInRepositoryActivityImpl searchHotelInRepositoryActivity;

	@Before
	public void insert() {
		try {
			addHotel("The Hilton Grand", "xyz", 4);
			addHotel("The Hilton Grand Great", "abc", 3);
			addHotel("The Hilton Great Grand", "pqr", 2);
		} catch (Exception e) {

		}
	}

	@Test
	public void searchByName() {
		SearchHotelInRepositoryResponse searchResult = searchHotelInRepositoryActivity
				.searchHotelInRepository(new SearchHotelInRepositoryRequest("Hilton", null, null, 10, 0));
		Assert.assertNotNull(searchResult);
		Assert.assertTrue(searchResult.getHotelinfoList().size() == 3);
	}

	@Test
	public void searchByNameNullResult() {
		SearchHotelInRepositoryResponse searchResult = searchHotelInRepositoryActivity
				.searchHotelInRepository(new SearchHotelInRepositoryRequest("Random", null, null, 10, 0));
		Assert.assertNotNull(searchResult);
		Assert.assertTrue(searchResult.getHotelinfoList().size() == 0);
	}

	@Test
	public void searchByCity() {
		SearchHotelInRepositoryResponse searchResult = searchHotelInRepositoryActivity
				.searchHotelInRepository(new SearchHotelInRepositoryRequest(null, "xyz", null, 10, 0));
		Assert.assertNotNull(searchResult);
		Assert.assertTrue(searchResult.getHotelinfoList().size() == 1);
	}

	@Test
	public void searchByCityNullResult() {
		SearchHotelInRepositoryResponse searchResult = searchHotelInRepositoryActivity
				.searchHotelInRepository(new SearchHotelInRepositoryRequest(null, "Random", null, 10, 0));
		Assert.assertNotNull(searchResult);
		Assert.assertTrue(searchResult.getHotelinfoList().size() == 0);
	}

	@Test
	public void searchByStarRanking() {
		SearchHotelInRepositoryResponse searchResult = searchHotelInRepositoryActivity
				.searchHotelInRepository(new SearchHotelInRepositoryRequest(null, null, 2, 10, 0));
		Assert.assertNotNull(searchResult);
		Assert.assertTrue(searchResult.getHotelinfoList().size() == 1);
	}

	@Test
	public void searchByStarRankingNullResult() {
		SearchHotelInRepositoryResponse searchResult = searchHotelInRepositoryActivity
				.searchHotelInRepository(new SearchHotelInRepositoryRequest(null, null, 1, 10, 0));
		Assert.assertNotNull(searchResult);
		Assert.assertTrue(searchResult.getHotelinfoList().size() == 0);
	}

	@Test
	public void searchByNameAndCity() {
		SearchHotelInRepositoryResponse searchResult = searchHotelInRepositoryActivity
				.searchHotelInRepository(new SearchHotelInRepositoryRequest("Hilton", "xyz", null, 10, 0));
		Assert.assertNotNull(searchResult);
		Assert.assertTrue(searchResult.getHotelinfoList().size() == 1);
	}

	@Test
	public void searchByCityAndStarRanking() {
		SearchHotelInRepositoryResponse searchResult = searchHotelInRepositoryActivity
				.searchHotelInRepository(new SearchHotelInRepositoryRequest(null, "xyz", 4, 10, 0));
		Assert.assertNotNull(searchResult);
		Assert.assertTrue(searchResult.getHotelinfoList().size() == 1);
	}

	@Test
	public void searchByNameAndStarRanking() {
		SearchHotelInRepositoryResponse searchResult = searchHotelInRepositoryActivity
				.searchHotelInRepository(new SearchHotelInRepositoryRequest("Hilton", null, 4, 10, 0));
		Assert.assertNotNull(searchResult);
		Assert.assertTrue(searchResult.getHotelinfoList().size() == 1);
	}

	@Test
	public void searchByNameAndCityAndStarRanking() {
		SearchHotelInRepositoryResponse searchResult = searchHotelInRepositoryActivity
				.searchHotelInRepository(new SearchHotelInRepositoryRequest("Hilton", "xyz", 4, 10, 0));
		Assert.assertNotNull(searchResult);
		Assert.assertTrue(searchResult.getHotelinfoList().size() == 1);
	}

	public void addHotel(String name, String city, Integer starRanking) {
		AddHotelInformationRequest addHotelInformationRequest = new AddHotelInformationRequest();
		addHotelInformationRequest.setName(name);
		addHotelInformationRequest.setStarRanking(starRanking);
		addHotelInformationRequest.setNumberOfRooms(4);
		List<FacilityInfo> facilityList = new ArrayList<FacilityInfo>();
		facilityList.add(new FacilityInfo(null, "Swimming Pool", Arrays.asList("openpool", "pets allowed")));
		addHotelInformationRequest.setFacilityList(facilityList);
		addHotelInformationRequest.setCreatedBy("Anamika");
		addHotelInformationRequest.setContact("2131212341241");
		addHotelInformationRequest.setAddress(new Address("4th Block", "2nd Street", city, "England"));

		AddHotelInformationResponse response = addHotelInformationActivity
				.addHotelInformation(addHotelInformationRequest);
	}
}
