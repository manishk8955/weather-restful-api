package com.weather.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherRoot implements Serializable {
	private static final long serialVersionUID = 5848077137039509365L;
	private String cod;
    private int message;
    private int cnt;
    private List<ListData> list;
    private City city;
	public String toString() {
		return "WeatherRoot [cod=" + cod + ", message=" + message + ", cnt=" + cnt + ", list=" + list + ", city=" + city
				+ "]";
	}
}