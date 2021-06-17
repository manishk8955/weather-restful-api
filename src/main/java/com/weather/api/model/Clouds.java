package com.weather.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clouds implements Serializable {
	private static final long serialVersionUID = 7397109014765647408L;
	private int all;
}