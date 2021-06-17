package com.weather.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weather.api.model.WeatherEntity;
 
@Repository
public interface WeatherRepository
        extends JpaRepository<WeatherEntity, Long> {
      List<WeatherEntity> findByApiKey(String apiKey);
}
