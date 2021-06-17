package com.weather.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather implements Serializable {
	private static final long serialVersionUID = -7743655143086415010L;
	private int id;
    private String main;
    private String description;
    private String icon;
}