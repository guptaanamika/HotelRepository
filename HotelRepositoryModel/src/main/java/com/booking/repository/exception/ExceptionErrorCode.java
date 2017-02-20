package com.booking.repository.exception;

public enum ExceptionErrorCode {

	INTERNAL_ERROR(400), DEFAULT_VALIDATION(401), CLIENT_INTERNAL(402), SERVER_INTERNAL(403), HOTEL_ALREADY_EXIST(405);

	private final Integer errorCode;

	private ExceptionErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

}
