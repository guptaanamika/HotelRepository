package com.booking.repository.service.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author anamika.gupta created_on : 16-Feb-2017
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchHotelInRepositoryRequest {

	private String name;

	private String city;

	private Integer starRanking;

	private Integer pageSize = 10;

	private Integer offset = 0;
}
