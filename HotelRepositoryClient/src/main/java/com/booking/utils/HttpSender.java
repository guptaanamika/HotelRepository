package com.booking.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.booking.repository.exception.InternalClientException;


public class HttpSender {

	private final String CONTENT_ENCODING_UTF_8 = "UTF-8";
	String HTTPS = "https";
	private static HttpSender instance = new HttpSender();

	private HttpSender() {
	}

	public static HttpSender getInstance() {
		return instance;
	}

	public HttpResponse executeGet(String url, Map<String, String> params, Map<String, String> headers,
			HttpClient httpClient) throws InternalClientException {

		HttpGet httpGet = new HttpGet(createURL(url, params));
		setHeaders(httpGet, headers);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			return response;
		} catch (Exception e) {
			throw new InternalClientException("Unable to execute http get", e);
		}
	}

	public HttpResponse executePost(String url, Map<String, String> params, Map<String, String> headers,
			HttpClient httpClient) throws InternalClientException {

		HttpPost httpPost = new HttpPost(url);
		setHeaders(httpPost, headers);
		try {
			if (params != null) {
				httpPost.setEntity(createStringEntity(params));
			}
			HttpResponse response = httpClient.execute(httpPost);
			return response;
		} catch (Exception e) {
			throw new InternalClientException("Unable to execute http post", e);
		}
	}

	public HttpResponse executePut(String url, Map<String, String> params, Map<String, String> headers,
			HttpClient httpClient) throws InternalClientException {

		HttpPut httpPut = new HttpPut(url);
		setHeaders(httpPut, headers);
		try {
			if (params != null) {
				httpPut.setEntity(createStringEntity(params));
			}
			HttpResponse response = httpClient.execute(httpPut);
			return response;
		} catch (Exception e) {
			throw new InternalClientException("Unable to execute http put", e);
		}
	}

	public HttpResponse executeDelete(String url, Map<String, String> params, Map<String, String> headers,
			HttpClient httpClient) throws InternalClientException {

		HttpDelete httpDelete = new HttpDelete(createURL(url, params));
		setHeaders(httpDelete, headers);
		try {
			HttpResponse response = httpClient.execute(httpDelete);
			return response;
		} catch (Exception e) {
			throw new InternalClientException("Unable to execute http delete", e);
		}
	}

	private HttpEntity createStringEntity(Map<String, String> params)
			throws JSONException, UnsupportedEncodingException {

		JSONObject keyArg = new JSONObject();
		for (Map.Entry<String, String> pEntry : params.entrySet()) {
			keyArg.put(pEntry.getKey(), pEntry.getValue());
		}
		StringEntity input = null;
		input = new StringEntity(keyArg.toString());
		return input;
	}

	private String createURL(String url, Map<String, String> params) {

		if (params == null) {
			return url;
		}
		StringBuilder builder = new StringBuilder();
		builder.append(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> pEntry : params.entrySet()) {
			nvps.add(new BasicNameValuePair(pEntry.getKey(), String.valueOf(pEntry.getValue())));
		}
		builder.append('?').append(URLEncodedUtils.format(nvps, CONTENT_ENCODING_UTF_8));
		return builder.toString();
	}

	private void setHeaders(HttpRequestBase httpObject, Map<String, String> headers) {
		if (headers != null) {
			for (Map.Entry<String, String> hEntry : headers.entrySet()) {
				httpObject.addHeader(hEntry.getKey(), hEntry.getValue());
			}
		}
	}
}