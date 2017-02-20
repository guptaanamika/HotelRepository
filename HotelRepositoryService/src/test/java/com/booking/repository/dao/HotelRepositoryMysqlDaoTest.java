package com.booking.repository.dao;

import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.booking.repository.dao.impl.HotelRepositoryMysqlDao;
import com.booking.repository.entity.Address;
import com.booking.repository.entity.HotelInfo;

@ContextConfiguration("classpath:spring/application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class HotelRepositoryMysqlDaoTest {

	@Autowired
	private HotelRepositoryMysqlDao dao;

	@Test
	public void insertTest() {
		String id = UUID.randomUUID().toString();
		HotelInfo hotelInfo = new HotelInfo();
		hotelInfo.setId(id);
		hotelInfo.setVersion(1L);
		hotelInfo.setNumberOfRooms(2);
		hotelInfo.setName("Sparx Hilton The Great");
		hotelInfo.setAddress(new Address(null, null, "England", null));
		hotelInfo.setStarRanking(4);
		hotelInfo.setCreatedBy("createdBy");
		hotelInfo.setCreatedOn(new Date());
		hotelInfo.setUpdatedBy("updatedBy");
		hotelInfo.setUpdatedOn(new Date());
		dao.addHotel(hotelInfo);

		HotelInfo hotelInfo1 = dao.getHotelById(id);
		Assert.assertEquals(hotelInfo, hotelInfo1);
	}

	@Test
	public void updateTest() {
		String id = UUID.randomUUID().toString();
		HotelInfo hotelInfo = new HotelInfo();
		hotelInfo.setId(id);
		hotelInfo.setVersion(1L);
		hotelInfo.setNumberOfRooms(2);
		hotelInfo.setName("Sparx Hilton The Great");
		hotelInfo.setAddress(new Address(null, null, "England", null));
		hotelInfo.setStarRanking(4);
		hotelInfo.setCreatedBy("createdBy");
		hotelInfo.setCreatedOn(new Date());
		hotelInfo.setUpdatedBy("updatedBy");
		hotelInfo.setUpdatedOn(new Date());

		dao.addHotel(hotelInfo);

		hotelInfo.setName("The Hilton great");
		dao.updateHotelById(hotelInfo);

		HotelInfo hotelInfo1 = dao.getHotelById(id);
		Assert.assertTrue(hotelInfo1.getVersion() == 2);
	}

	@Test
	public void removeByIdTest() {
		String id = UUID.randomUUID().toString();
		HotelInfo hotelInfo = new HotelInfo();
		hotelInfo.setId(id);
		hotelInfo.setVersion(1L);
		hotelInfo.setNumberOfRooms(2);
		hotelInfo.setName("Sparx Hilton The Great");
		hotelInfo.setAddress(new Address(null, null, "England", null));
		hotelInfo.setStarRanking(4);
		hotelInfo.setCreatedBy("createdBy");
		hotelInfo.setCreatedOn(new Date());
		hotelInfo.setUpdatedBy("updatedBy");
		hotelInfo.setUpdatedOn(new Date());

		dao.addHotel(hotelInfo);

		dao.removeHotelById(id);

		HotelInfo hotelInfo1 = dao.getHotelById(id);
		Assert.assertNull(hotelInfo1);
	}
}
