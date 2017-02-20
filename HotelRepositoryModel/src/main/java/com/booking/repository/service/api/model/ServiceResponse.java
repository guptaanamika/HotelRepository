package com.booking.repository.service.api.model;

import java.util.Date;

import lombok.Data;

import com.booking.repository.exception.RepositoryException;

public @Data class ServiceResponse<T> {
	private T response;
	private RepositoryException exception;
	private Date serverTimeStamp;

	public void setResponse(T response) {
		this.response = response;
		this.serverTimeStamp = new Date();
	}

}
