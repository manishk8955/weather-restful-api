package com.weather.api.exception.handler;

public class WeatherForecastNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3241920620370394306L;

	public WeatherForecastNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}