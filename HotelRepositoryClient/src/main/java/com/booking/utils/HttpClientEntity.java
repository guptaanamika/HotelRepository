package com.booking.utils;

import lombok.Getter;
import lombok.Setter;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

@Getter
@Setter
public class HttpClientEntity {

	private HttpClient httpClient;
	private PoolingHttpClientConnectionManager connManager;
}
