package com.weather.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sys implements Serializable {
	private static final long serialVersionUID = -1537948741870865258L;
	private String pod;
}
