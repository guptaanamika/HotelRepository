package com.booking.repository.exception;

public class HotelAlreadyExistException extends ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4615699391391825171L;

	public HotelAlreadyExistException() {

	}

	public HotelAlreadyExistException(Exception e) {
		super(e);
	}

	public HotelAlreadyExistException(Throwable e) {
		super(e);
	}

	public HotelAlreadyExistException(String message) {
		super(message);
	}

	{
		this.setErrorCode(ExceptionErrorCode.HOTEL_ALREADY_EXIST);
	}

}
