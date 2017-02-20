package com.booking.repository.entity;

import lombok.Data;

/**
 * @author anamika.gupta created_on : 16-Feb-2017
 **/
@Data
public class HotelInfo extends EditInfo {

	private String id;

	private String name;

	private Long version;

	private Address address;

	private String contact;

	private Integer starRanking;

	private Integer numberOfRooms;

}
