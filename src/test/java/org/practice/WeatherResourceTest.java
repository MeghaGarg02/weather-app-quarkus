package org.practice;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;


@QuarkusTest
class WeatherResourceTest {

    @ParameterizedTest
    @ValueSource(strings = {"London", "Mumbai", "Paris"})
    public void testAdd2(final String param) {
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
}