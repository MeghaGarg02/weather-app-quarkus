package org.practice;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class WeatherResponse {

    private String message;
    private boolean success;

    public WeatherResponse()
    {
    	
    }

    WeatherResponse(String message) {
        this.success = true;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
