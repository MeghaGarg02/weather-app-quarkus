package org.practice.open.weather.map.helper;

import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.practice.open.weather.map.response.OpenWeatherMapData;

@QuarkusTest
class OpenWeatherMapUtilTest {

    private OpenWeatherMapUtil openWeatherMapUtil = new OpenWeatherMapUtil();

    private final String CITY_NAME_LONDON = "London";

    @Test
    public void getURLTest() {
        assertNotNull(openWeatherMapUtil.getURL(CITY_NAME_LONDON));
    }

    @Test
    public void getOpenWeatherMapDataNotNullTest() {
        OpenWeatherMapData openWeatherMapData = (openWeatherMapUtil.getOpenWeatherMapData(openWeatherMapUtil.getURL(CITY_NAME_LONDON)));
        assertEquals(CITY_NAME_LONDON, openWeatherMapData.getName());
        assertNotNull(openWeatherMapData.getWeather().get(0).getDescription());
        assertNotNull(openWeatherMapData.getMain().getTemp());
    }

    @Test
    public void getOpenWeatherMapDataNullTest() {
        assertNull(openWeatherMapUtil.getOpenWeatherMapData(null));
    }
}
