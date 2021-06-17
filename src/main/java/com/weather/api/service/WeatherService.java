package com.weather.api.service;

import com.weather.api.model.response.WeatherResponse;

public interface WeatherService {

	WeatherResponse getWeatherForecastCityCountry(String city, String country, String apiKey);
}
