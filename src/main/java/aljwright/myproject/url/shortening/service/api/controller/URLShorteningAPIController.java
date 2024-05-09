package aljwright.myproject.url.shortening.service.api.controller;

import aljwright.myproject.url.shortening.service.api.exception.NotValidURLException;
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

import java.util.Objects;

@RestController
public class URLShorteningAPIController {

    Logger logger = LoggerFactory.getLogger(URLShorteningAPIController.class);


    @Autowired
    URLShorteningAPIService service;

    @PostMapping("/shorten")
    @ResponseBody
    public ResponseEntity<URLShorteningResponse> getShortenedURL(@RequestBody URLShorteningRequest request){
        logger.info("Received Url for shortening : "+request.getOriginalUrl());
        URLShorteningResponse response = service.getShortenedUrl(request.getOriginalUrl());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{shortUrl}")
    @ResponseBody
    public RedirectView redirectFromUrl(@PathVariable String shortUrl){

        logger.info("Received redirect request for the Url : "+shortUrl);

        return service.getRedirectUrl(shortUrl);
    }

    @GetMapping("/getOriginalUrl")
    @ResponseBody
    public ResponseEntity<URLShorteningResponse> getOriginalUrl(@RequestBody URLGetOriginalUrlRequest request){
        logger.info("Received request to get Original Url for the Url : "+request.getShortUrl());
        URLShorteningResponse response = service.getOriginalUrl(request.getShortUrl());

        return new ResponseEntity<>(response,HttpStatus.OK);

    }


}
