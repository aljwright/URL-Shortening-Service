package aljwright.myproject.url.shortening.service.api.controller;

import aljwright.myproject.url.shortening.service.api.request.URLGetOriginalUrlRequest;
import aljwright.myproject.url.shortening.service.api.request.URLShorteningRequest;
import aljwright.myproject.url.shortening.service.api.response.URLShorteningResponse;
import aljwright.myproject.url.shortening.service.api.service.URLShorteningAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


/**
 *
 * Controller Class used to handle the incoming requests to the URL Shortening Service API
 *
 */
@RestController
public class URLShorteningAPIController {

    Logger logger = LoggerFactory.getLogger(URLShorteningAPIController.class);


    @Autowired
    URLShorteningAPIService service;

    /**
     *
     * Method to handle incoming request with the Original Url to be shortened
     *
     * @param request - contains the URL that needs to be shortened
     * @return
     * Shortened URL that can be used to redirect to the Original URL
     */
    @PostMapping("/shorten")
    @ResponseBody
    public ResponseEntity<URLShorteningResponse> getShortenedURL(@RequestBody URLShorteningRequest request){
        logger.info("Received Url for shortening : "+request.getOriginalUrl());
        URLShorteningResponse response = service.getShortenedUrl(request.getOriginalUrl());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *
     * Method used to handle the redirect when the short Url is used
     *
     * @param shortUrl - short Url used to redirect to the Original URL
     * @return
     * Redirection to the Original Url
     */
    @GetMapping("/{shortUrl}")
    @ResponseBody
    public RedirectView redirectFromUrl(@PathVariable String shortUrl){

        logger.info("Received redirect request for the Url : "+shortUrl);

        return service.getRedirectUrl(shortUrl);
    }

    /**
     *
     * Method to get the Original URl from the Short Url.
     *
     * @param request - shortened Url
     * @return
     * The original Url used to create the shortened Url
     */
    @GetMapping("/getOriginalUrl")
    @ResponseBody
    public ResponseEntity<URLShorteningResponse> getOriginalUrl(@RequestBody URLGetOriginalUrlRequest request){
        logger.info("Received request to get Original Url for the Url : "+request.getShortUrl());
        URLShorteningResponse response = service.getOriginalUrl(request.getShortUrl());

        return new ResponseEntity<>(response,HttpStatus.OK);

    }


}
