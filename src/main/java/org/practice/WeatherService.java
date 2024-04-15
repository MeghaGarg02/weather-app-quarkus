package org.practice;

import java.util.Optional;

import org.practice.open.weather.map.helper.OpenWeatherMapUtil;
import org.practice.open.weather.map.response.OpenWeatherMapData;

import io.quarkus.logging.Log;

public class WeatherService {

    private WeatherRepository weatherRepository;

    private OpenWeatherMapUtil openWeatherMapUtil;

    public WeatherService(WeatherRepository weatherRepository, OpenWeatherMapUtil openWeatherMapUtil) {
        this.weatherRepository = weatherRepository;
        this.openWeatherMapUtil = openWeatherMapUtil;
    }

    public Optional<WeatherResponse> getWeatherResponse(String cityName) {
        Log.info("Requesting the data from Open Weather Map API---");
        OpenWeatherMapData openWeatherMapData = openWeatherMapUtil.getOpenWeatherMapData(openWeatherMapUtil.getURL(cityName));
        Log.info("Received Data from Open Weather Map API---");
      return weatherRepository.getWeatherResponse(openWeatherMapData.getName(), openWeatherMapData.getWeather().get(0).getDescription(),
              openWeatherMapData.getMain().getTemp());
    }
}