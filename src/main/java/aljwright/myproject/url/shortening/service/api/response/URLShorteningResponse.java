package aljwright.myproject.url.shortening.service.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * Response Object used in the URL Shortening API, it is used for both shortening url and retrieving the original URL APIs
 *
 */
@Data
@NoArgsConstructor(force = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class URLShorteningResponse {

    String message;

    int responseCode;

    String shortUrl;

    String originalUrl;

}
