package com.weather.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coord implements Serializable {
	private static final long serialVersionUID = -404334626137102220L;
	private double lat;
    private double lon;
}