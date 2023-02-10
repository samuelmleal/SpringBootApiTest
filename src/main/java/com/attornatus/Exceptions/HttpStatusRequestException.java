package com.attornatus.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class HttpStatusRequestException extends ResponseStatusException {

    public HttpStatusRequestException(HttpStatus status) {
        super(status);
    }

    public HttpStatusRequestException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public HttpStatusRequestException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
