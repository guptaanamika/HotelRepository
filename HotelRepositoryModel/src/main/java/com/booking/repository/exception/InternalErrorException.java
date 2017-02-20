package com.booking.repository.exception;

public class InternalErrorException extends RepositoryException {

	private static final long serialVersionUID = 8008343838743105044L;

	public InternalErrorException() {

	}

	public InternalErrorException(String message) {
		super(message);
	}

	public InternalErrorException(Throwable e) {
		super(e);
	}

	public InternalErrorException(Throwable e, String message) {
		super(message, e);
	}

	{
		this.setErrorCode(ExceptionErrorCode.INTERNAL_ERROR);
	}
}
