package com.booking.repository.exception;

import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 **/
@Setter
@JsonIgnoreProperties("stackTrace")
public class RepositoryException extends RuntimeException {

	private static final long serialVersionUID = 2772585922930376373L;
	private ExceptionErrorCode errorCode;
	private Class<? extends RepositoryException> exceptionCause;

	public RepositoryException(Throwable cause) {
		super(cause);
	}

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException() {
	}

	public RepositoryException withErrorCode(ExceptionErrorCode errorCode) {
		this.errorCode = errorCode;
		return this;

	}

	public RepositoryException(String message, Throwable e) {
		super(message, e);
	}

	public ExceptionErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ExceptionErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public Class<?> getExceptionCause() {
		return exceptionCause;
	}

	public void setExceptionCause(Class<? extends RepositoryException> exceptionCause) {
		this.exceptionCause = exceptionCause;
	}

	{
		this.setExceptionCause(this.getClass());
	}

}
