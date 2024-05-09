package aljwright.myproject.url.shortening.service.api.request;

import lombok.NoArgsConstructor;
import lombok.Value;

/**
 *
 * Request Object that contains the Shortened Url to retrieve the Original/long Url from Storage
 *
 */
@Value
@NoArgsConstructor(force = true)
public class URLGetOriginalUrlRequest {

    String shortUrl;

}
