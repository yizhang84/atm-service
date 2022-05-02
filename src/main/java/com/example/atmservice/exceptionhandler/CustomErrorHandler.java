package com.example.atmservice.exceptionhandler;

import com.example.atmservice.exception.InputUrlFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomErrorHandler.class);


    /**
     * Handle MissingServletRequestParameterException
     *
     * @param ex The exception
     * @return ApiError
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiError handleMissingServletRequestParameter(final MissingServletRequestParameterException ex) {
        LOGGER.error("Handling MissingServletRequestParameter error:  {}", ex.getMessage());

        String error = ex.getParameterName() + " parameter is missing";
        final List<String> errors = new ArrayList<>();
        errors.add(error);
        return buildApiError(errors, ex.getLocalizedMessage());
    }

    /**
     * Handle InputUrlFormatException
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(InputUrlFormatException.class)
    @ResponseBody
    public ApiError handleInputUrlFormatException(final InputUrlFormatException ex) {
        LOGGER.error("Handling InputUrlFormatException:  {}", ex.getMessage());

        String error = "The input API URL is not correct";
        final List<String> errors = new ArrayList<>();
        errors.add(error);
        return buildApiError(errors, ex.getLocalizedMessage());
    }

    private ApiError buildApiError(final List<String> errors, final String msg) {
        final ApiError apiError = new ApiError();
        apiError.setMessage(msg);
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setErrors(errors);
        return apiError;
    }
}
