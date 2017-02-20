package com.booking.repository.dao.entity;

import lombok.Data;

import com.booking.repository.entity.EditInfo;

@Data
public class HotelEntity extends EditInfo {

	private String id;

	private String name;

	private Long version;

	private String address;

	private String contact;

	private Integer starRanking;

	private Integer numberOfRooms;

}
