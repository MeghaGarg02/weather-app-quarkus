package org.practice;

import java.util.Optional;

import org.practice.exception.ServiceException;

import io.netty.util.internal.StringUtil;
import io.quarkus.logging.Log;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/weather")
public class WeatherResource {

    private WeatherService weatherService;

    public WeatherResource(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

	@GET()
    public Optional<WeatherResponse> getWeatherResponse(@QueryParam(value = "cityName") String cityName) {
        if(StringUtil.isNullOrEmpty(cityName)) {
            Log.error("Bad Request Exception Occurred - Invalid input param cityName: " + cityName);
            throw new ServiceException("Bad Request - Invalid input param cityName");
        }
        Optional<WeatherResponse> response = weatherService.getWeatherResponse(cityName);
        if(response.isPresent()) {
            Log.info("Successfully returning the Response---");
        } else {
            Log.error("Service Exception Occurred - Unable to get the Resource");
            throw new ServiceException("Resource Not Found");
        }
        return response;
    }
}
