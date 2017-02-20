package com.booking.utils;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.mina.http.api.HttpMethod;

import com.booking.repository.exception.InternalClientException;
import com.booking.repository.service.api.model.RequestHeaders;
import com.booking.repository.service.api.model.RequestParamValidator;
import com.booking.repository.service.api.model.ServiceResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
public class HttpUtil {
	public static final String APPLICATION_JSON = "application/json";
	public static final int DEFAULT_ERROR_CODE = 500;

	HttpSender httpSender = HttpSender.getInstance();

	private static HttpUtil instance = new HttpUtil();

	private static RequestParamValidator validator = new RequestParamValidator();

	public static HttpUtil getInstance() {
		return instance;
	}

	private HttpUtil() {
	}

	public String getCompleteUrl(String relativeUrl) {
		return relativeUrl;
	}

	public <T, R> R processHttpRequest(String completeUrl, TypeReference<ServiceResponse<R>> typeReference, T request,
			HttpMethod method, HttpClient httpClient, ClientDetails clientDetails) {

		validator.validate(request);
		final Map<String, String> parameters = getMap(request);
		R response = null;
		int statusCode = DEFAULT_ERROR_CODE;
		Map<String, String> header = new HashMap<String, String>();
		try {
			header = createHeader(request, clientDetails);
			log.info("For Client :" + clientDetails.getClientName()
					+ " Request Json for Checksum generation on Client:" + request + " [CheckSumGenerated]:"
					+ header.get("CheckSum"));
			HttpResponse result = executeHttpMethod(completeUrl, parameters, header, method, httpClient);
			if (result != null && result.getStatusLine() != null) {
				statusCode = result.getStatusLine().getStatusCode();
				String json = EntityUtils.toString(result.getEntity());
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

				if (statusCode == ValidResponseEnum.OK.getValue() || statusCode == ValidResponseEnum.CREATED.getValue()) {
					response = mapper.readValue(json, typeReference);
				} else {
					throw new InternalClientException("Status code: " + statusCode + " "
							+ result.getStatusLine().getReasonPhrase());
				}

			}
		} catch (Exception e) {
			log.error("For Request ID:[" + header.get(RequestHeaders.APP_REQUEST_ID.getName()) + "] " + e.getMessage()
					+ ":");
			throw new InternalClientException(e.getCause(), e.getMessage());
		}
		log.info("For Request ID:[" + header.get(RequestHeaders.APP_REQUEST_ID.getName()) + "]" + response);
		return response;
	}

	private HttpResponse executeHttpMethod(String completeUrl, Map<String, String> parameters,
			Map<String, String> header, HttpMethod method, HttpClient httpClient) throws InternalClientException {

		HttpResponse result = null;
		switch (method) {
		case GET:
			result = httpSender.executeGet(completeUrl, parameters, header, httpClient);
			break;
		case PUT:
			result = httpSender.executePut(completeUrl, parameters, header, httpClient);
			break;
		case POST:
			result = httpSender.executePost(completeUrl, parameters, header, httpClient);
			break;
		case DELETE:
			result = httpSender.executeDelete(completeUrl, parameters, header, httpClient);
			break;
		default:
			throw new InternalClientException("Server doesn't support http method: " + method);
		}
		return result;
	}

	private Map<String, String> createHeader(Object request, ClientDetails clientDetails) throws Exception {
		Map<String, String> header = new HashMap<String, String>();
		header.put(RequestHeaders.CONTENT_TYPE.getName(), APPLICATION_JSON);
		header.put(RequestHeaders.ACCEPT.getName(), APPLICATION_JSON);
		SystemInformationFetcher.setApplicationInformationInHeader(header);
		return header;
	}

	@SuppressWarnings("unchecked")
	private Map getMap(Object request) {
		Map<String, Object> map = new HashMap<String, Object>();
		org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();
		map = mapper.convertValue(request, map.getClass());
		Map<String, Object> mapNew = new HashMap<String, Object>();
		for (String key : map.keySet()) {
			if (map.get(key) != null)
				mapNew.put(key, map.get(key));
		}
		return mapNew;
	}
}