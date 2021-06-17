package com.weather.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wind implements Serializable {
	private static final long serialVersionUID = -3181132403671847746L;
	private double speed;
    private int deg;
}