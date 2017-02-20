package com.booking.repository.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.repository.exception.RepositoryException;
import com.booking.repository.service.IngestionService;
import com.booking.repository.service.SearchService;
import com.booking.repository.service.ServiceUri;
import com.booking.repository.service.api.model.AddHotelInformationRequest;
import com.booking.repository.service.api.model.AddHotelInformationResponse;
import com.booking.repository.service.api.model.GetHotelInformationByIdRequest;
import com.booking.repository.service.api.model.GetHotelInformationByIdResponse;
import com.booking.repository.service.api.model.RemoveHotelInformationRequest;
import com.booking.repository.service.api.model.SearchHotelInRepositoryRequest;
import com.booking.repository.service.api.model.SearchHotelInRepositoryResponse;
import com.booking.repository.service.api.model.ServiceResponse;
import com.booking.repository.service.api.model.UpdateHotelInformationRequest;

@RestController
public class HotelRepositoryController {

	@Autowired
	private SearchService searchService;

	@Autowired
	private IngestionService ingestionService;

	private static final String APPLICATION_JSON = "application/json";

	@RequestMapping(value = ServiceUri.SEARCH_HOTEL, produces = APPLICATION_JSON, method = RequestMethod.GET)
	public ServiceResponse<SearchHotelInRepositoryResponse> searchHotelInRepository(
			@ModelAttribute SearchHotelInRepositoryRequest searchHotelInRepositoryRequest,
			HttpServletRequest servletRequest) {
		ServiceResponse<SearchHotelInRepositoryResponse> serviceResponse = new ServiceResponse<SearchHotelInRepositoryResponse>();
		serviceResponse.setResponse(searchService.searchHotelInRepository(searchHotelInRepositoryRequest));
		return serviceResponse;
	}

	@RequestMapping(value = ServiceUri.GET_HOTEL_BY_ID, produces = APPLICATION_JSON, method = RequestMethod.GET)
	public ServiceResponse<GetHotelInformationByIdResponse> getHotelInformationByIdActivity(
			@ModelAttribute GetHotelInformationByIdRequest request, HttpServletRequest servletRequest) {
		ServiceResponse<GetHotelInformationByIdResponse> serviceResponse = new ServiceResponse<GetHotelInformationByIdResponse>();
		serviceResponse.setResponse(ingestionService.getHotelInformationByIdActivity(request));
		return serviceResponse;
	}

	@RequestMapping(value = ServiceUri.ADD_HOTEL, produces = APPLICATION_JSON, method = RequestMethod.POST)
	public ServiceResponse<AddHotelInformationResponse> addHotelInformation(
			@RequestBody AddHotelInformationRequest addHotelInformationRequest, HttpServletRequest servletRequest) {
		ServiceResponse<AddHotelInformationResponse> serviceResponse = new ServiceResponse<AddHotelInformationResponse>();
		serviceResponse.setResponse(ingestionService.addHotelInformation(addHotelInformationRequest));
		return serviceResponse;
	}

	@RequestMapping(value = ServiceUri.UPDATE_HOTEL, produces = APPLICATION_JSON, method = RequestMethod.POST)
	public ServiceResponse<Void> updateHotelInformation(
			@RequestBody UpdateHotelInformationRequest updateHotelInformationRequest, HttpServletRequest servletRequest) {
		ServiceResponse<Void> serviceResponse = new ServiceResponse<Void>();
		ingestionService.updateHotelInformation(updateHotelInformationRequest);
		return serviceResponse;
	}

	@RequestMapping(value = ServiceUri.REMOVE_HOTEL, produces = APPLICATION_JSON, method = RequestMethod.POST)
	public ServiceResponse<Void> removeHotelInformation(
			@RequestBody RemoveHotelInformationRequest removeHotelInformationRequest, HttpServletRequest servletRequest) {
		ServiceResponse<Void> serviceResponse = new ServiceResponse<Void>();
		ingestionService.removeHotelInformation(removeHotelInformationRequest);
		return serviceResponse;
	}

	@ExceptionHandler(RepositoryException.class)
	public <T> ServiceResponse<T> handleException(RepositoryException exception) {
		ServiceResponse<T> response = new ServiceResponse<T>();
		response.setException(exception);
		return response;
	}
}
