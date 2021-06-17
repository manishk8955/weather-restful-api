package com.weather.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="WEATHER_INFO")
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="api_url")
    private String apiUrl;
    
    @Column(name="api_key")
    private String apiKey;
    
    @Column(name="request")
    private String request;
    
    @Column(name="response", nullable=false, length=1000)
    private String response;

    public String toString() {
		return "WeatherEntity [id=" + id + ", apiUrl=" + apiUrl + ", request=" + request + ", response=" + response + "]";
	}
    
	public WeatherEntity(Long id, String apiUrl, String apiKey, String request, String response) {
		super();
		this.id = id;
		this.apiUrl = apiUrl;
		this.apiKey = apiKey;
		this.request = request;
		this.response = response;
	}
	
	public WeatherEntity(String apiUrl, String apiKey, String request, String response) {
		super();
		this.apiUrl = apiUrl;
		this.apiKey = apiKey;
		this.request = request;
		this.response = response;
	}
}