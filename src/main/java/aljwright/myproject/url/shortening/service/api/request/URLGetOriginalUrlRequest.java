package aljwright.myproject.url.shortening.service.api.request;

import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
public class URLGetOriginalUrlRequest {

    String shortUrl;

}
