package com.weather.api.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse implements Serializable {
	private static final long serialVersionUID = -5647700914433743910L;
	private String cityName;
    private String country;
    private Map<String, Double> tomorrowsForecast;
    
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Map<String, Double> getTomorrowsForecast() {
		return tomorrowsForecast;
	}
	public void setTomorrowsForecast(Map<String, Double> tomorrowsForecast) {
		this.tomorrowsForecast = tomorrowsForecast;
	}
}
