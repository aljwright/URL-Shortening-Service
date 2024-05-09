package aljwright.myproject.url.shortening.service.api.utilities;

import aljwright.myproject.url.shortening.service.api.entity.URLRecord;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 *
 * Utility class to provide helper methods for data processing
 *
 */
public class URLShorteningAPIUtils {

    /**
     *
     * Method used to validate the provided URL
     * @param url url to be validated
     * @return
     * if the provided url is valid or not
     */
    public static boolean isNotValidURL(String url){
        try {
            new URL(url).toURI();
            return false;
        } catch (MalformedURLException | URISyntaxException e) {
            return true;
        }
    }

    /**
     *
     * Utility method to construct the shortened URL
     *
     * @param shortenedMd5 generated hash used for the shortened Url
     * @return
     * shortened Url
     */
    public static String constructUri(String shortenedMd5){

        URI currentUri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .build()
                .toUri()
                .resolve("/"+shortenedMd5);

        return currentUri.toString();
    }

    /**
     *
     * Utility method to get the Hash based on the long/original Url
     *
     * @param longUrl long/original Url
     * @return
     * Generated hash value based on the long/original Url
     */
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

    /**
     * Utility Method used to construct the entity to be saved in the DB
     * @param longUrl long/original Url to be saved
     * @param shortenedUrl shortened Url based on the long/original Url
     * @return
     * Entity Object to be saved in the Database
     */
    public static URLRecord constructURLEntity(String longUrl,String shortenedUrl){
        URLRecord record = new URLRecord();
        record.setShortUrl(shortenedUrl);
        record.setLongUrl(longUrl);
        return record;
    }

    /**
     *
     * Extract the hash value from the shortened URl
     *
     * @param shortUrl shortened Url
     * @return
     * hash value separated from the short Url to help lookup in Database
     *
     */
    public static String extractMd5FromUrl(String shortUrl)  {
        URI currentUri = null;
        try {
            currentUri = new URL(shortUrl).toURI();
        }catch (MalformedURLException | URISyntaxException ignored){}

        return Objects.nonNull(currentUri) ? currentUri.getPath().substring(1) : null;
    }


}
