package com.booking.repository.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.booking.repository.dao.impl.HotelFacilitiesRepositoryMysqlDao;
import com.booking.repository.entity.FacilityInfo;
import com.booking.repository.entity.HotelFacilityInfo;

@ContextConfiguration("classpath:spring/application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class HotelFacilitiesRepositoryMysqlDaoTest {

	@Autowired
	private HotelFacilitiesRepositoryMysqlDao dao;

	@Test
	public void addFacilities() {
		String id = UUID.randomUUID().toString();

		HotelFacilityInfo hotelFaciltyInfo = new HotelFacilityInfo();
		hotelFaciltyInfo.setId(id);
		List<FacilityInfo> facilityInfoList = new ArrayList<FacilityInfo>();
		facilityInfoList.add(new FacilityInfo(null, "Swimming Pool", Arrays.asList("openpool", "pets allowed")));
		hotelFaciltyInfo.setFacilityInfoList(facilityInfoList);
		hotelFaciltyInfo.setCreatedBy("createdBy");
		hotelFaciltyInfo.setCreatedOn(new Date());
		hotelFaciltyInfo.setUpdatedBy("updatedBy");
		hotelFaciltyInfo.setUpdatedOn(new Date());

		dao.addFacilities(hotelFaciltyInfo);

		HotelFacilityInfo facilityInfo = dao.getHotelFacilities(id);
		Assert.assertEquals(hotelFaciltyInfo, facilityInfo);
	}

	@Test
	public void removeFacilities() {
		String id = UUID.randomUUID().toString();

		HotelFacilityInfo hotelFaciltyInfo = new HotelFacilityInfo();
		hotelFaciltyInfo.setId(id);
		List<FacilityInfo> facilityInfoList = new ArrayList<FacilityInfo>();
		facilityInfoList.add(new FacilityInfo(null, "Swimming Pool", Arrays.asList("openpool", "pets allowed")));
		hotelFaciltyInfo.setFacilityInfoList(facilityInfoList);
		hotelFaciltyInfo.setCreatedBy("createdBy");
		hotelFaciltyInfo.setCreatedOn(new Date());
		hotelFaciltyInfo.setUpdatedBy("updatedBy");
		hotelFaciltyInfo.setUpdatedOn(new Date());

		dao.addFacilities(hotelFaciltyInfo);
		dao.removeFacilities(id);

		HotelFacilityInfo facilityInfo = dao.getHotelFacilities(id);
		Assert.assertNull(facilityInfo);
	}
}
