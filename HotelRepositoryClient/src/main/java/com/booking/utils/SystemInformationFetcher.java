package com.booking.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.UUID;

import com.booking.repository.service.api.model.RequestHeaders;

public class SystemInformationFetcher {

	public static void setApplicationInformationInHeader(Map<String, String> requestHeader) {
		InetAddress ip;
		String hostName = null, hostIpAddress = null, hostLoggedUserName = null, requestId = null;
		long timeStamp = System.currentTimeMillis();
		try {
			ip = InetAddress.getLocalHost();
			hostName = ip.getHostName();
			hostIpAddress = ip.getHostAddress();
			hostLoggedUserName = System.getenv().get("LOGNAME");
			requestId = UUID.randomUUID().toString();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		requestHeader.put(RequestHeaders.APP_CLIENT_NAME.getName(), hostName);
		requestHeader.put(RequestHeaders.APP_CLIENT_IP_ADDRESS.getName(), hostIpAddress);
		requestHeader.put(RequestHeaders.APP_LOGGED_USER.getName(), hostLoggedUserName);
		requestHeader.put(RequestHeaders.APP_CLIENT_TIMESTAMP.getName(), String.valueOf(timeStamp));
		requestHeader.put(RequestHeaders.APP_REQUEST_ID.getName(), requestId);
	}
}
