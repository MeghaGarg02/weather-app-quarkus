package org.practice;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.practice.open.weather.map.helper.OpenWeatherMapUtil;

import io.quarkus.logging.Log;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;

public final class AppConfig {

    private static final ConcurrentMap<Class<?>, Object> dependencies = new ConcurrentHashMap<>();

    private String appKey = "weather.app-key";

    private String hostName = "weather.host-name";

    private String pathValue = "weather.path-value";

    private String scheme = "weather.scheme";

    private Properties properties;

    private AppConfig() {
    }

    private <T> T registerDependency(Class<T> type, T instance) {
        Object oldInstance = dependencies.putIfAbsent(type, instance);
        return oldInstance != null ? type.cast(oldInstance) : instance;
    }

    public <T> T getDependency(Class<T> type) {
        return type.cast(dependencies.get(type));
    }

    public static AppConfig getInstance() {
        return PROVIDER.instance;
    }

    private static class PROVIDER {
        private static final AppConfig instance = new AppConfig();
    }

    void onStart(@Observes StartupEvent ev) {
        Log.info("The application is starting...{}");
        properties = loadProperties();
        appKey = properties.getProperty(appKey);
        hostName = properties.getProperty(hostName);
        pathValue = properties.getProperty(pathValue);
        scheme = properties.getProperty(scheme);
        registerDependency(WeatherAppProperties.class, new WeatherAppProperties(appKey,hostName, pathValue, scheme));
        registerDependency(WeatherRepository.class, new WeatherRepository());
        registerDependency(OpenWeatherMapUtil.class, new OpenWeatherMapUtil());
        registerDependency(WeatherService.class, new WeatherService());
    }

    private static Properties loadProperties() {
        Log.info("Loading the application properties file");
        Properties configuration = new Properties();
        try {
            InputStream inputStream = AppConfig.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties");
            configuration.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            Log.error("Unable to read the Properties file");
            e.printStackTrace();
        }
        return configuration;
    }

    void onStop(@Observes ShutdownEvent ev) {
        Log.info("The application is stopping... {}");
    }
}
