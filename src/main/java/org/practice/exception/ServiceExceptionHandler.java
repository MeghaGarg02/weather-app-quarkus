package org.practice.exception;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider
public class ServiceExceptionHandler implements ExceptionMapper<ServiceException> {

    @ConfigProperty(name = "quarkus.exception.resource-not-found")
    String resourceNotFound;

    @Override
    public Response toResponse(ServiceException e) {

        if (e.getMessage().equalsIgnoreCase(resourceNotFound)) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity(new ErrorMessage(e.getMessage(), false)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).
                    entity(new ErrorMessage(e.getMessage(), false)).build();
        }
    }
}
