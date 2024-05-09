package aljwright.myproject.url.shortening.service.api.request;


import lombok.NoArgsConstructor;
import lombok.Value;

/**
 *
 * Request Object that contains the Original/long URl to be shortened
 *
 */
@Value
@NoArgsConstructor(force = true)
public class URLShorteningRequest {

    String originalUrl;


}
