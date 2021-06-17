package com.weather.api.exception.handler;

public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = 3706702991449963392L;

	public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }

}
