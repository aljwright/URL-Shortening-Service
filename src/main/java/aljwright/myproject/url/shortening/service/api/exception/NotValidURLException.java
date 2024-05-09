package aljwright.myproject.url.shortening.service.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static aljwright.myproject.url.shortening.service.api.utilities.URLShorteningAPIConstants.NOT_VALID_URL_EXCEPTION_MESSAGE;

/**
 * Custom Exception to handle URL Validation Failure
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = NOT_VALID_URL_EXCEPTION_MESSAGE)
public class NotValidURLException extends RuntimeException{
    private static final long serialVersionUID = 1L;
}
