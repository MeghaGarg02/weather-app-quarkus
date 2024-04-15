package org.practice.log.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

import io.quarkus.logging.LoggingFilter;

@LoggingFilter(name = "my-filter")
public final class LogFilter implements Filter{

    private static List<String> capturedMessages = new ArrayList<>(); 

    @Override
    public boolean isLoggable(LogRecord record) {
        if(record.getMessage().contains("Bad Request")) {
            capturedMessages.add(record.getMessage());
        }
        return true;
    }

    public List<String> getCapturedMessages() {
        return capturedMessages;
    }
}
