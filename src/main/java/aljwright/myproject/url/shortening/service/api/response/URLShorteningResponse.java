package aljwright.myproject.url.shortening.service.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class URLShorteningResponse {

    String message;

    int responseCode;

    String shortUrl;

    String originalUrl;

}
