package com.booking.repository.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;

import com.booking.repository.entity.Address;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "hotel_repo", type = "hotel")
public class HotelSearchEntity {

	@Id
	private String id;

	@Version
	private Long version;

	private String name;

	private Address address;

	private Integer starRanking;

}
