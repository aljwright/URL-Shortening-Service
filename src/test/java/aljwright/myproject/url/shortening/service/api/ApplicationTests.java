package aljwright.myproject.url.shortening.service.api;

import aljwright.myproject.url.shortening.service.api.response.URLShorteningResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {



	@LocalServerPort
	private int port;

	@Test
	public void testShorteningURI(){

		String originalUrl = "https://www.geeksforgeeks.org/system-design-interview-bootcamp-guide/";
		String requestJson = "{\"originalUrl\" : \""+originalUrl+"\"}";

		URLShorteningResponse shorteningResponse = RestAssured.given()
				.port(port)
				.contentType("application/json")
				.body(requestJson)
				.post("/shorten")
				.getBody().
				as(URLShorteningResponse.class);

		String requestForOriginalUrlJson = "{\"shortUrl\" : \""+shorteningResponse.getShortUrl()+"\"}";

		URLShorteningResponse urlShorteningResponse = RestAssured.given()
				.port(port)
				.contentType("application/json")
				.body(requestForOriginalUrlJson)
				.get("/getOriginalUrl")
				.getBody()
				.as(URLShorteningResponse.class);

		Assertions.assertEquals(originalUrl,urlShorteningResponse.getOriginalUrl());

	}

	@Test
	public void testInvalidUrlRequestProvided(){

		String originalUrl = "httpews://www.geeksforgeeks.org/system-design-interview-bootcamp-guide/";
		String requestJson = "{\"originalUrl\" : \""+originalUrl+"\"}";
		Response responseBody = RestAssured.given()
				.port(port)
				.contentType("application/json")
				.body(requestJson)
				.post("/shorten");

		Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseBody.getStatusCode());

	}
}
