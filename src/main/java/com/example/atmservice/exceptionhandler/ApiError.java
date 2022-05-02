package com.example.atmservice.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {

    private String message;
    private List<String> errors;
    private LocalDateTime timestamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(final List<String> errors) {
        this.errors = errors;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
