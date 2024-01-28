package com.projeto.demo.exceptions;

import org.springframework.http.HttpStatus;

public class ValueNotAllowed  extends AbstractException {
    public ValueNotAllowed(String message) {
        super(message);
    }
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
