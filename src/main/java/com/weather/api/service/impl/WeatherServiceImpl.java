package com.weather.api.service.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weather.api.exception.handler.WeatherForecastNotFoundException;
import com.weather.api.model.ListData;
import com.weather.api.model.WeatherEntity;
import com.weather.api.model.WeatherRoot;
import com.weather.api.model.response.WeatherResponse;
import com.weather.api.service.WeatherEntityService;
import com.weather.api.service.WeatherService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class WeatherServiceImpl implements WeatherService {

	private final RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	WeatherEntityService weatherEntityService;
	
	@Autowired
	Environment environment;

    @Override
	public WeatherResponse getWeatherForecastCityCountry(String city, String country, String appid) {
        WeatherRoot response;        
        log.info("*** Getting Weather forecast for  city :: {}", city + " country :: {}" +country);        
        String apiUrl = environment.getProperty("weather.api.url");
        List<String> keyList = new ArrayList<>(Arrays.asList(getWeatherApiKeys()));
        if(!keyList.contains(appid)) {
        	throw new WeatherForecastNotFoundException("Invalid API Key");
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", city+","+country);
        params.put("appid", appid);
        
        try {
	        response = restTemplate.getForObject(apiUrl, WeatherRoot.class, params);
	        log.info("*** Weather forecast for provided city and country :: {}", response);
	        /* Insert Response into H2 DB */
	        WeatherEntity entity = new WeatherEntity(apiUrl, appid, "q="+city+","+country+"&appid="+appid, response.toString());
	        weatherEntityService.createWeather(entity);
	        
        } catch(Exception e){
         	throw new WeatherForecastNotFoundException("Weather forecast not found for provided City and Country");         	
        }
        WeatherResponse weatherResponse = WeatherResponse.builder()
                .cityName(response.getCity().getName())
                .country(response.getCity().getCountry())
                .tomorrowsForecast(getTomorrowsPredictedData(response.getList()))     
                .build();
        log.info("*** Weather forecast for tomorrow :: {}", weatherResponse); 
        return weatherResponse;
	}
    
    private Map<String, Double> getTomorrowsPredictedData(List<ListData> list) {
        log.info("*** Populating Weather forecast for tomorrow");
        Map<String, Double> hourlyTemperature = new HashMap<>();
        LocalDate tomorrowsLocalDate = LocalDate.now().plusDays(1);
        list.forEach(listData -> {
                    LocalDate forecastedDate = Instant.ofEpochMilli(new Date(listData.getDt() * 1000)
                            .getTime())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();

                    if (tomorrowsLocalDate.getDayOfMonth() == forecastedDate.getDayOfMonth()) {
                        hourlyTemperature.put(listData.getDt_txt(), listData.getMain().getTemp_min());
                    }
                }
        );
        log.info("*** Populated Weather forecast for tomorrow");
        return hourlyTemperature;
    }
    
    public String[] getWeatherApiKeys() {
 	   return environment.getProperty("weather.api.key",String[].class);
    }
}
