package com.booking.repository.exception;

public class InternalClientException extends InternalErrorException {

	private static final long serialVersionUID = 4502562121582908793L;

	public InternalClientException() {
	}

	public InternalClientException(String message) {
		super(message);
	}

	public InternalClientException(Throwable e, String message) {
		super(e, message);
	}

	public InternalClientException(String message, Throwable e) {
		super(e, message);
	}

	public InternalClientException(Throwable e) {
		super(e);
	}

	{
		this.setErrorCode(ExceptionErrorCode.CLIENT_INTERNAL);
	}
}
