package com.example.atmservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The input URL is not correct.")
public class InputUrlFormatException extends RuntimeException {

    private static final long serialVersionUID = 7454810756981429799L;

    public InputUrlFormatException(final String message) {
        super(message);
    }
    public InputUrlFormatException(final String message, final Throwable source) {
        super(message, source);
    }
}
