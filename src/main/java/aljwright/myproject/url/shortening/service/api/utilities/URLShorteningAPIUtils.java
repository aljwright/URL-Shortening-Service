package aljwright.myproject.url.shortening.service.api.utilities;

import aljwright.myproject.url.shortening.service.api.entity.URLRecord;
import aljwright.myproject.url.shortening.service.api.exception.NotValidURLException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class URLShorteningAPIUtils {

    public static boolean isNotValidURL(String url){
        try {
            new URL(url).toURI();
            return false;
        } catch (MalformedURLException | URISyntaxException e) {
            return true;
        }
    }

    public static String constructUri(String shortenedMd5){

        URI currentUri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .build()
                .toUri()
                .resolve("/"+shortenedMd5);

        return currentUri.toString();
    }

    public static String getMd5HashOfUrl(String longUrl){
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(longUrl.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext.substring(0,8);
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static URLRecord constructURLEntity(String longUrl,String shortenedUrl){
        URLRecord record = new URLRecord();
        record.setShortUrl(shortenedUrl);
        record.setLongUrl(longUrl);
        return record;
    }

    public static String extractMd5FromUrl(String shortUrl)  {
        URI currentUri = null;
        try {
            currentUri = new URL(shortUrl).toURI();
        }catch (MalformedURLException | URISyntaxException ignored){}

        return Objects.nonNull(currentUri) ? currentUri.getPath().substring(1) : null;
    }


}
