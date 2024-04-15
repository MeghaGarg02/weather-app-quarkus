package org.practice.open.weather.map.helper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.practice.AppConfig;
import org.practice.WeatherAppProperties;
import org.practice.exception.ServiceException;
import org.practice.open.weather.map.response.OpenWeatherMapData;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.logging.Log;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriBuilderException;

public class OpenWeatherMapUtil {

    private WeatherAppProperties weatherAppProperties;

    public OpenWeatherMapUtil() {
        weatherAppProperties = AppConfig.getInstance().getDependency(WeatherAppProperties.class);
    }

    public URL getURL(String cityName) {
            try {
                return UriBuilder.newInstance().scheme(weatherAppProperties.getScheme())
                        .host(weatherAppProperties.getHostName()).path(weatherAppProperties.getPathValue())
                        .queryParam("q", cityName).queryParam("appid", weatherAppProperties.getAppKey()).build().toURL();
            } catch (MalformedURLException | IllegalArgumentException | UriBuilderException e) {
                Log.errorf("Error while creating Open Weather Map API URL");
                throw new ServiceException("Could Not create OpenWeatherMap API URL");
            }
    }

    public OpenWeatherMapData getOpenWeatherMapData(URL url) {
        try {
            if(url != null) {
                return new ObjectMapper().readValue(url, OpenWeatherMapData.class);
            } else {
                return null;
            }
        } catch (IOException e) {
            Log.errorf("IO Exception Occurred while reading data from OpenWeatherMap API");
            throw new ServiceException("Unable to get response from OpenWeatherMap API");
        }
    }
}
