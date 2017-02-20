package com.booking.repository.converter;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseConverter {

	public static String toString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Conversion to String failed , msg" + e.getMessage());
		}
	}

	public static <T> T fromString(String obj, Class<T> clazz) {
		try {
			return new ObjectMapper().readValue(obj, clazz);
		} catch (IOException e) {
			throw new RuntimeException("Conversion to Object failed , msg" + e.getMessage());
		}
	}
}
