package org.practice;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import io.quarkus.logging.Log;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class AppConfig {

    @Inject
    private WeatherService weatherService;
    
    @Inject
    private WeatherAppProperties properties;

    public WeatherService getWeatherService() {
        return weatherService;
    }

    public WeatherAppProperties getProperties() {
        return properties;
    }

    void onStart(@Observes StartupEvent ev) {
        Log.info("The application is starting...{}");
    }

    void onStop(@Observes ShutdownEvent ev) {
        Log.info("The application is stopping... {}");
    }
}
