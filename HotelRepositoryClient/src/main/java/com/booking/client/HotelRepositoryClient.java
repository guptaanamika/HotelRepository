package com.booking.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Timer;

import lombok.extern.slf4j.Slf4j;

import org.apache.mina.http.api.HttpMethod;

import com.booking.repository.exception.ExceptionParser;
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
import com.booking.utils.ClientDetails;
import com.booking.utils.HttpClientEntity;
import com.booking.utils.HttpClientFactory;
import com.booking.utils.HttpUtil;
import com.booking.utils.IdleConnectionMonitor;
import com.fasterxml.jackson.core.type.TypeReference;

@Slf4j
public class HotelRepositoryClient implements IngestionService, SearchService {

	private ClientDetails clientDetails;
	private HttpClientEntity httpClientEntity;
	private HttpClientFactory httpClientFactory = HttpClientFactory.getInstance();
	private Timer timer;

	public HotelRepositoryClient(String serviceHostUrl, String clientName, String clientKey) throws Exception {
		ClientDetails details = new ClientDetails();
		details.setUrl(serviceHostUrl);
		details.setClientName(clientName);
		details.setClientKey(clientKey);
		this.clientDetails = details;
		intializeHttpClient(details);
		initializeIdleConnectionMonitor();

	}

	public HotelRepositoryClient(ClientDetails clientDetails) throws Exception {
		this.clientDetails = clientDetails;
		intializeHttpClient(clientDetails);
		initializeIdleConnectionMonitor();
	}

	private void intializeHttpClient(ClientDetails clientDetails) throws UnrecoverableKeyException,
			KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException,
			FileNotFoundException, IOException {
		this.httpClientEntity = httpClientFactory.createHttpClient(clientDetails);
	}

	private void initializeIdleConnectionMonitor() {
		IdleConnectionMonitor monitor = new IdleConnectionMonitor();
		monitor.setMonitorName("SDMoneyClient");
		monitor.setConnMgr(httpClientEntity.getConnManager());
		timer = new Timer(true);
		timer.scheduleAtFixedRate(monitor, 0, IdleConnectionMonitor.MONITOR_RATE);
	}

	private String createCompleteUrl(String relativeUrl) {
		return clientDetails.getUrl() + relativeUrl;
	}

	@SuppressWarnings({ "unchecked" })
	private <T, R> R processPostRequest(String requestUri, T request, TypeReference<ServiceResponse<R>> typeReference)
			throws RepositoryException {

		ServiceResponse<R> responseBody = (ServiceResponse<R>) HttpUtil.getInstance().processHttpRequest(requestUri,
				typeReference, request, HttpMethod.POST, httpClientEntity.getHttpClient(), clientDetails);
		RepositoryException exception = responseBody.getException();
		if (exception != null) {
			log.error(exception.getMessage());
			ExceptionParser.parseException(exception);
		}
		R json = responseBody.getResponse();

		return json;
	}

	@SuppressWarnings({ "unchecked" })
	private <T, R> R processGetRequest(String requestUri, T request, TypeReference<ServiceResponse<R>> typeReference)
			throws RepositoryException {

		ServiceResponse<R> responseBody = (ServiceResponse<R>) HttpUtil.getInstance().processHttpRequest(requestUri,
				typeReference, request, HttpMethod.GET, httpClientEntity.getHttpClient(), clientDetails);
		RepositoryException exception = responseBody.getException();
		if (exception != null) {
			log.error(exception.getMessage());
			ExceptionParser.parseException(exception);
		}
		R json = responseBody.getResponse();

		return json;
	}

	@Override
	public AddHotelInformationResponse addHotelInformation(AddHotelInformationRequest addHotelInformationRequest) {
		String requestUri = createCompleteUrl(ServiceUri.ADD_HOTEL);
		return processPostRequest(requestUri, addHotelInformationRequest,
				new TypeReference<ServiceResponse<AddHotelInformationResponse>>() {
				});
	}

	@Override
	public void removeHotelInformation(RemoveHotelInformationRequest removeHotelInformationRequest) {
		String requestUri = createCompleteUrl(ServiceUri.REMOVE_HOTEL);
		processPostRequest(requestUri, removeHotelInformationRequest, new TypeReference<ServiceResponse<Void>>() {
		});
	}

	@Override
	public void updateHotelInformation(UpdateHotelInformationRequest updateHotelInformationRequest) {
		String requestUri = createCompleteUrl(ServiceUri.UPDATE_HOTEL);
		processPostRequest(requestUri, updateHotelInformationRequest, new TypeReference<ServiceResponse<Void>>() {
		});
	}

	@Override
	public GetHotelInformationByIdResponse getHotelInformationByIdActivity(GetHotelInformationByIdRequest request) {
		String requestUri = createCompleteUrl(ServiceUri.GET_HOTEL_BY_ID);
		return processGetRequest(requestUri, request,
				new TypeReference<ServiceResponse<GetHotelInformationByIdResponse>>() {
				});
	}

	@Override
	public SearchHotelInRepositoryResponse searchHotelInRepository(
			SearchHotelInRepositoryRequest searchHotelInRepositoryRequest) {
		String requestUri = createCompleteUrl(ServiceUri.SEARCH_HOTEL);
		return processGetRequest(requestUri, searchHotelInRepositoryRequest,
				new TypeReference<ServiceResponse<SearchHotelInRepositoryResponse>>() {
				});
	}

}
