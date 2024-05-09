package aljwright.myproject.url.shortening.service.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static aljwright.myproject.url.shortening.service.api.utilities.URLShorteningAPIConstants.URL_RECORD_NOT_FOUND_EXCEPTION_MESSAGE;

/**
 *
 * Custom Exception to handle url data not available in Storage
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = URL_RECORD_NOT_FOUND_EXCEPTION_MESSAGE)
public class URLRecordNotFoundException extends  RuntimeException{
    private static final long serialVersionUID = 1L;
}
