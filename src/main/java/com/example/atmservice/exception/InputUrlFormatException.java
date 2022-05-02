package com.example.atmservice.exception;


public class InputUrlFormatException extends RuntimeException {

    private static final long serialVersionUID = 7454810756981429799L;

    public InputUrlFormatException(final String message) {
        super(message);
    }
    public InputUrlFormatException(final String message, final Throwable source) {
        super(message, source);
    }
}
