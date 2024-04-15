package org.practice.log.filter;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.practice.WeatherResource;
import org.practice.exception.ServiceException;
import org.practice.log.filter.LogFilter;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogFilterTest {

    @Inject
    private WeatherResource resource;

    private LogFilter filter = new LogFilter();

      @Test 
      void filterBadRequestExceptionTest() {
        try {
            resource.getWeatherResponse("");
        } catch (ServiceException e){
          assertTrue(filter.getCapturedMessages().size()>0);
        }
     }
}