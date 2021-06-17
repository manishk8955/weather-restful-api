package com.weather.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weather.api.exception.handler.RecordNotFoundException;
import com.weather.api.model.WeatherEntity;
import com.weather.api.model.repository.WeatherRepository;
import com.weather.api.service.WeatherEntityService;

@Service
public class WeatherEntityServiceImpl implements WeatherEntityService {

	@Autowired
	WeatherRepository repository;

	@Override
	public List<WeatherEntity> getAllWeatherInfo() {
		List<WeatherEntity> weatherList = repository.findAll();
		if (weatherList.size() > 0) {
			return weatherList;
		} else {
			return new ArrayList<WeatherEntity>();
		}
	}

	@Override
	public WeatherEntity getWeatherById(Long id) throws RecordNotFoundException {
		Optional<WeatherEntity> weather = repository.findById(id);
		if (weather.isPresent()) {
			return weather.get();
		} else {
			throw new RecordNotFoundException("No weather record exist for given id");
		}
	}

	@Override
	public WeatherEntity getWeatherByApiKey(String apiKey) throws RecordNotFoundException {
		WeatherEntity newEntity = new WeatherEntity();
		newEntity.setApiKey(apiKey);
		List<WeatherEntity> weather = repository.findByApiKey(apiKey);
		if (weather.size() > 0) {
			return weather.get(0);
		} else {
			throw new RecordNotFoundException("No weather record exist for given id");
		}
	}

	@Override
	public WeatherEntity createOrUpdateWeather(WeatherEntity entity) throws RecordNotFoundException {
		Optional<WeatherEntity> weather = repository.findById(entity.getId());
		if (weather.isPresent()) {
			WeatherEntity newEntity = weather.get();
			newEntity.setApiUrl(entity.getApiUrl());
			newEntity.setRequest(entity.getRequest());
			newEntity.setResponse(entity.getResponse());
			entity = repository.save(entity);
			return entity;
		} else {
			entity = repository.save(entity);
			return entity;
		}
	}

	@Override
	public WeatherEntity createWeather(WeatherEntity entity) throws RecordNotFoundException {
		entity = repository.save(entity);
		return entity;
	}

	@Override
	public void deleteWeatherById(Long id) throws RecordNotFoundException {
		Optional<WeatherEntity> weather = repository.findById(id);
		if (weather.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No weather record exist for given id");
		}
	}
}