package org.practice;

import org.practice.open.weather.map.helper.OpenWeatherMapUtil;

import io.quarkus.logging.Log;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public final class AppConfig {

    @Inject 
    private WeatherAppProperties weatherAppProperties;

    @ApplicationScoped
    public WeatherResource WeatherResource(WeatherService weatherService) {
        return new WeatherResource(weatherService);
    }

    @ApplicationScoped
    public WeatherService WeatherService(WeatherRepository weatherRepository, OpenWeatherMapUtil openWeatherMapUtil) {
        return new WeatherService(weatherRepository, openWeatherMapUtil);
    }

    @ApplicationScoped
    public OpenWeatherMapUtil OpenWeatherMapUtil(WeatherAppProperties weatherAppProperties) {
        return new OpenWeatherMapUtil(weatherAppProperties);
    }

    @ApplicationScoped
    public WeatherRepository WeatherRepository() {
        return new WeatherRepository();
    }

    void onStart(@Observes StartupEvent ev) {
        Log.info("The application is starting...{}");
    }

    void onStop(@Observes ShutdownEvent ev) {
        Log.info("The application is stopping... {}");
    }
}
