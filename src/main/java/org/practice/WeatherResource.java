package org.practice;

import java.io.IOException;
import java.util.Optional;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.UriBuilderException;

@Path("/weather")
public class WeatherResource {

@Inject
private AppConfig appConfig;

    @GET()
    public Optional<WeatherResponse> hello(@QueryParam(value = "cityName") String cityName) throws IllegalArgumentException, UriBuilderException, IOException {
    Log.info("Passing the request for getting the Weather Data for City: " + cityName);
    //cityName = "Stavanger";
    return appConfig.getWeatherService().findByCityName(cityName);
    }
}
