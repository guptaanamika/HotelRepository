package com.booking.repository.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("stackTrace")
public class InternalServerException extends InternalErrorException {

	private static final long serialVersionUID = 8655878429831876879L;

	public InternalServerException(Throwable e) {
		super(e);
	}

	public InternalServerException() {
	}

	public InternalServerException(String message) {
		super(message);
	}

	{
		this.setErrorCode(ExceptionErrorCode.SERVER_INTERNAL);
	}

}
