package org.practice;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import org.practice.exception.ServiceException;
import org.practice.weather.map.response.OpenWeatherMapResponseData;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
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

    public Optional<WeatherResponse> findByCityName(String cityName){
      Log.info("Reading Weather Json data from https://openweathermap.org/ by city and appId---");
      OpenWeatherMapResponseData data;
        try {
            if(this.getOpenWeatherMapURLByCityName(cityName) != null) {
                data = new ObjectMapper().readValue(this.getOpenWeatherMapURLByCityName(cityName), OpenWeatherMapResponseData.class);
                if(data != null) {
                    Log.info("Weather Map Data String JSON is " +  data.toString());
                    return getWeatherResponse(data, cityName);
                }
            }
        } catch (StreamReadException e) {
            Log.error("Error in reading Open weather api response stream---", e);
        } catch (DatabindException e) {
            Log.error("Error in converting Open weather api response to JSON object---", e);
        } catch (IOException e) {
            Log.error("IO error occurred while calling the Open weather api response---", e);
        } catch (Exception e) {
            Log.errorf("Service Exception Occured while fetching the open weather api response---", e);
            throw new ServiceException("Service Exception Occured while building the open weather api URL for city and appId ---");
        }
      return Optional.ofNullable(null);
    }

    private Optional<WeatherResponse> getWeatherResponse(OpenWeatherMapResponseData data, String cityName) {
        String message = String.format("Weather for city %s is %s and Temperature is %f!!!", cityName, data.getWeather().get(0).getDescription(), data.getMain().getTemp());
        Log.info("Weather Information Response: " + message);
        return Optional.of(new WeatherResponse(message));
    }

    private URL getOpenWeatherMapURLByCityName(String cityName) {
        URL url = null;
        try {
            url = UriBuilder.newInstance()
                    .scheme(appConfig.getProperties().getScheme())
                    .host(appConfig.getProperties().getHostName())
                    .path(appConfig.getProperties().getPathValue())
                    .queryParam("q", cityName)
                    .queryParam("appid", appConfig.getProperties().getAppKey())
                    .build()
                    .toURL();
        } catch (MalformedURLException | IllegalArgumentException | UriBuilderException e) {
            Log.error("Error in generating the Open weather api URL ---", e);
        } catch (Exception e) {
            Log.errorf("Service Exception Occured while building the open weather api URL for city and appId ---", e);
            throw new ServiceException("Service Exception Occured while building the open weather api URL for city and appId ---");
        }
        return url;
    }
}