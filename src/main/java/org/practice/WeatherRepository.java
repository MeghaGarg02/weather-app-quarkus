package org.practice;

import java.util.Optional;

import io.quarkus.logging.Log;

public class WeatherRepository {

    public Optional<WeatherResponse> getWeatherResponse(String name, String desc, float temp){ 
        Log.info("Creating the response from open Weather Map API data"); 
        return Optional.of(new WeatherResponse(String.format("Weather for city %s is %s and Temperature is %f!!!",name, desc, temp)));
      }
}
