package com.booking.repository.exception;


/**
 * @author anamika.gupta created_on : 18-Nov-2016
 **/
public class ValidationException extends RepositoryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1466433267470373326L;

	public ValidationException() {
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(String message, Throwable e) {
		super(message, e);
	}

	public ValidationException(Throwable e) {
		super(e);
	}

	{
		this.setErrorCode(ExceptionErrorCode.DEFAULT_VALIDATION);
	}
}
