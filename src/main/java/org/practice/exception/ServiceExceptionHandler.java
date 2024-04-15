package org.practice.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ServiceExceptionHandler implements ExceptionMapper<ServiceException> {

    @Override
    public Response toResponse(ServiceException e) {

        if (e.getMessage().equalsIgnoreCase("Resource Not Found")) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity(new ErrorMessage(e.getMessage(), false)).build();
        } else if(e.getMessage().contains("Bad Request")){
            return Response.status(Response.Status.BAD_REQUEST).
                    entity(new ErrorMessage(e.getMessage(), false)).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity(new ErrorMessage(e.getMessage(), false)).build();
        }
    }
}
