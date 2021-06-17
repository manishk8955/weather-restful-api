package com.weather.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.weather.api.exception.handler.InvalidInputException;
import com.weather.api.model.response.WeatherResponse;
import com.weather.api.service.WeatherService;

@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {

    @InjectMocks
    private WeatherController controller;

    @Mock
    private WeatherService service;

    @Test
    public void getWeatherForecastCityCountrySuccessTest() {
    	WeatherResponse weatherAPIResponse = WeatherResponse.builder().build();
        when(service.getWeatherForecastCityCountry(anyString(),anyString(),any())).thenReturn(weatherAPIResponse);
        HttpHeaders headers=new HttpHeaders();
        headers.add("x-api-key", "7257d8782a5eb0a6fa450c5c3a8884a1");
        ResponseEntity<Object> responseEntity = controller.getWeatherForecastCityCountry("London","uk",headers);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test(expected = InvalidInputException.class)
    public void getWeatherForecastCityCountryFailureTest() {
    	HttpHeaders headers=new HttpHeaders();
        headers.add("x-api-key", "");
        ResponseEntity<Object> responseEntity = controller.getWeatherForecastCityCountry("London","uk",headers);
    }
}
