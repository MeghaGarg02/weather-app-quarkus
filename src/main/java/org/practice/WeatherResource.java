package org.practice;

import java.util.Optional;

import org.practice.exception.ServiceException;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/weather")
public class WeatherResource {

@Inject
private AppConfig appConfig;

    @GET()
    public Optional<WeatherResponse> hello(@QueryParam(value = "cityName") String cityName) {
        Log.info("Passing the request for getting the Weather Data for City: " + cityName);
        Optional<WeatherResponse> response = appConfig.getWeatherService().findByCityName(cityName);
        if(response.isPresent()) {
            return response;
        } else {
            throw new ServiceException("Resource Not Found");
        }
    }
}
