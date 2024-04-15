package org.practice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

@QuarkusIntegrationTest
class WeatherResourceIT extends WeatherResourceTest {

    @ParameterizedTest
    @ValueSource(strings = {"London", "Mumbai", "Paris"})
    public void getWeatherResponseTest(final String param) {
        given()
                .param("cityName", param)
                .header("Content-Type", ContentType.JSON)
                .when()
                .get("/weather")
                .then()
                .statusCode(200)
                .body(
                        "success", is(true),
                        "message", containsString("Weather for city " + param + " is "),
                        "message", containsString("and Temperature is "));
    }

    @Test
    public void getWeatherResponseBadRequestTest() {
        given()
                .param("cityName", "")
                .header("Content-Type", ContentType.JSON)
                .when()
                .get("/weather")
                .then()
                .statusCode(400);
    }
}
