package com.weather.api.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.weather.api.exception.handler.InvalidInputException;
import com.weather.api.model.response.WeatherResponse;
import com.weather.api.service.WeatherService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class WeatherController {

	private final WeatherService service;

	@GetMapping("/{city}/{country}")
	public ResponseEntity<Object> getWeatherForecastCityCountry(@PathVariable String city, @PathVariable String country,
			@RequestHeader HttpHeaders headers) {
		String apiKey = StringUtils.EMPTY;
		log.info("*** Getting Weather forecast for city :: {}", city + " country :: {}" + country);

		if (headers.containsKey("x-api-key") && (headers.getValuesAsList("x-api-key").size()) > 0) {
			apiKey = (headers.getValuesAsList("x-api-key")).get(0);
		}

		if (StringUtils.isBlank(city) || StringUtils.isBlank(country) || StringUtils.isBlank(apiKey)) {
			throw new InvalidInputException("City, country & apiKey should not be null or empty");
		}

		WeatherResponse weatherResponse = service.getWeatherForecastCityCountry(city, country, apiKey);
		
		log.info("*** Weather forecast :: {}", weatherResponse);
		return ResponseEntity.ok().body(weatherResponse);
	}

}
