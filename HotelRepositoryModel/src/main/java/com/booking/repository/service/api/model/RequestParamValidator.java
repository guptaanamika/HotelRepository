package com.booking.repository.service.api.model;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.booking.repository.exception.ValidationException;

public class RequestParamValidator {

	private Validator paramValidator;

	public RequestParamValidator() {
		initializer();
	}

	private void initializer() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		this.paramValidator = validatorFactory.getValidator();
	}

	public void validate(Object t) throws ValidationException {

		Set<ConstraintViolation<Object>> constraintViolations = paramValidator.validate(t);
		if (constraintViolations.size() != 0) {
			StringBuilder errorMessage = new StringBuilder();
			for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
				errorMessage.append(
						"[" + constraintViolation.getPropertyPath() + "] : " + constraintViolation.getMessage() + " ");
			}

			throw new ValidationException("ParamValidation failed: " + errorMessage);
		}
	}
}
