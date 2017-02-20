package com.booking.repository.service.api.model;

public enum RequestHeaders {
	APP_CLIENT_NAME("AppClientName"), APP_CLIENT_IP_ADDRESS(
			"AppClientIpAddress"), APP_LOGGED_USER("AppLoggedUser"), APP_CLIENT_TIMESTAMP(
			"AppClientTimestamp"), APP_REQUEST_ID("AppRequestId"), CONTENT_TYPE(
					"content-type"), ACCEPT("accept");
	private String name;

	RequestHeaders(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
