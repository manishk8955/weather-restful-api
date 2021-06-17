package com.weather.api.service;

import java.util.List;

import com.weather.api.exception.handler.RecordNotFoundException;
import com.weather.api.model.WeatherEntity;

public interface WeatherEntityService {
	
	List<WeatherEntity> getAllWeatherInfo();
	
	WeatherEntity getWeatherById(Long id) throws RecordNotFoundException;
	
	WeatherEntity getWeatherByApiKey(String apiKey) throws RecordNotFoundException;
	
	WeatherEntity createOrUpdateWeather(WeatherEntity entity) throws RecordNotFoundException;
	
	void deleteWeatherById(Long id) throws RecordNotFoundException;

	WeatherEntity createWeather(WeatherEntity entity) throws RecordNotFoundException;	
}
