package com.test;

import org.testng.annotations.*;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

public class TestWebService {

	@BeforeMethod
	public void setUp() {
		// set default port for REST-assured
		RestAssured.port = 80;

		// set default URI for REST-assured.
		// In integration tests, this would most likely point to localhost.
		RestAssured.baseURI = "http://api.openweathermap.org";
	}

	@Test
	public void shouldReturnWeatherDataForWarsaw() {
		given().header("Accept-Encoding", "application/json").when()
				.get("data/2.5/weather?q=Warsaw").then().statusCode(200)
				.contentType(ContentType.JSON)
				.body(getLongitudeByCityName("Warsaw"), equalTo(21.01f));
	}

	private String getLongitudeByCityName(String name) {
		return String.format("coord.lon", name);
	}

}
