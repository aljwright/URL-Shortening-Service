package aljwright.myproject.url.shortening.service.api.service;

import aljwright.myproject.url.shortening.service.api.entity.URLRecord;
import aljwright.myproject.url.shortening.service.api.exception.NotValidURLException;
import aljwright.myproject.url.shortening.service.api.exception.URLRecordNotFoundException;
import aljwright.myproject.url.shortening.service.api.repository.URLShorteningAPIRepository;
import aljwright.myproject.url.shortening.service.api.response.URLShorteningResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static aljwright.myproject.url.shortening.service.api.utilities.URLShorteningAPIUtils.*;

/**
 *
 * Service Layer used to perform the Data Operations for the URI Shortening API Service
 *
 */
@Service
public class URLShorteningAPIService {

    Logger logger = LoggerFactory.getLogger(URLShorteningAPIService.class);

    @Autowired
    URLShorteningAPIRepository repository;

    /**
     *
     * Method used to generate shortened URL based on the original / long url
     *
     * @param longUrl long/original url to be shortened
     * @return
     * response object with the shortened Url
     * @throws
     * NotValidURLException in case of url validation errors
     */
    public URLShorteningResponse getShortenedUrl(String longUrl)  {

        if(Objects.isNull(longUrl) || isNotValidURL(longUrl)){
            logger.error("Validation failed for the request provided");
            throw new NotValidURLException();
        }

        String hashToBeStored = getMd5HashOfUrl(longUrl);
        String shortenedUrl = constructUri(hashToBeStored);

        repository.save(constructURLEntity(longUrl,hashToBeStored));
        logger.info("Record saved in DB Successfully");
        URLShorteningResponse response = new URLShorteningResponse();

        response.setShortUrl(shortenedUrl);
        response.setMessage("Success");
        response.setResponseCode(HttpStatus.OK.value());
        logger.info("Url shortened and saved successfully");
        return response;
    }

    /**
     *
     * Method used to redirect to the Original Url when the short url is loaded
     *
     * @param shortUrl shortened url
     * @return
     * Redirect view object to redirect to the original url
     * @throws
     * URLRecordNotFoundException
     * in case the URL is not found in the storage
     */
    public RedirectView getRedirectUrl(String shortUrl){

        if(Objects.isNull(shortUrl)){
            throw new URLRecordNotFoundException();
        }

        return new RedirectView(getLongUrlFromDb(shortUrl));
    }

    /**
     *
     * Method used to get the Original / Long Url based on the short Url
     *
     * @param shortUrl shortened Url to be used to retrieve the long /original url
     * @return
     * Response Object with the long/original Url
     * @throws
     * NotValidURLException in case of URL validation errors
     * @throws
     * URLRecordNotFoundException in case if the details are not found in the storage
     */
    public URLShorteningResponse getOriginalUrl(String shortUrl){

        if(Objects.isNull(shortUrl) || isNotValidURL(shortUrl)){
            logger.error("Validation failed for the request provided");
            throw new NotValidURLException();
        }

        String originalUrl = getLongUrlFromDb(extractMd5FromUrl(shortUrl));

        URLShorteningResponse response = new URLShorteningResponse();

        response.setOriginalUrl(originalUrl);
        response.setMessage("Success");
        response.setResponseCode(HttpStatus.OK.value());

        return response;
    }


    /**
     *
     * Used to retrieve the long/original Url from storage based on the hash value
     *
     * @param storedHash stored hash value
     * @return
     * long/original Url from storage
     * @throws
     * URLRecordNotFoundException
     * when the record is not found in the storage
     */
    private String getLongUrlFromDb(String storedHash){
        logger.info("Search for record in DB");
        logger.debug("hash value searched -- "+storedHash);
        Optional<URLRecord> recordOptional = StreamSupport.stream(repository.findAll().spliterator(),false)
                .filter(urlRecord -> urlRecord.getShortUrl().equals(storedHash))
                .findFirst();

        return recordOptional.orElseThrow(URLRecordNotFoundException::new).getLongUrl();

    }


}
