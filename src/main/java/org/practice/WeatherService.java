package org.practice;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import org.practice.weather.map.response.OpenWeatherMapResponseData;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriBuilderException;

@ApplicationScoped
public class WeatherService {

    @Inject
    private AppConfig appConfig;

    public Optional<WeatherResponse> findByCityName(String cityName) throws IllegalArgumentException, UriBuilderException, IOException {
      Log.info("Reading Weather Json data from https://openweathermap.org/ by city and appId---");
      OpenWeatherMapResponseData data =  new ObjectMapper().readValue(this.getOpenWeatherMapURLByCityName(cityName), OpenWeatherMapResponseData.class);
      Log.info("Weather Map Data String JSON is " +  data.toString());
      return getWeatherResponse(data, cityName);
    }

    private Optional<WeatherResponse> getWeatherResponse(OpenWeatherMapResponseData data, String cityName) {
        String message = String.format("Weather for city %s is %s and Temperature is %f!!!", cityName, data.getWeather().get(0).getDescription(), data.getMain().getTemp());
        Log.info("Weather Information Response: " + message);
        return Optional.of(new WeatherResponse(message));
    }

    private URL getOpenWeatherMapURLByCityName(String cityName) throws MalformedURLException, IllegalArgumentException, UriBuilderException {
        return UriBuilder.newInstance()
                .scheme(appConfig.getProperties().getScheme())
                .host(appConfig.getProperties().getHostName())
                .path(appConfig.getProperties().getPathValue())
                .queryParam("q", cityName)
                .queryParam("appid", appConfig.getProperties().getAppKey())
                .build()
                .toURL();
    }
}