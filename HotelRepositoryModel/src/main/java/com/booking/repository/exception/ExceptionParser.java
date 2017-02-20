package com.booking.repository.exception;

import java.lang.reflect.InvocationTargetException;

public class ExceptionParser {

	public static void parseException(RepositoryException exception) {
		try {
			throw ((RepositoryException) exception.getExceptionCause().getConstructor(String.class)
					.newInstance(exception.getMessage())).withErrorCode(exception.getErrorCode());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

}
