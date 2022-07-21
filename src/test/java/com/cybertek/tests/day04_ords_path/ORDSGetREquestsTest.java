package com.cybertek.tests.day04_ords_path;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;


public class ORDSGetREquestsTest {

    @BeforeAll
    public static void setUp (){
        RestAssured.baseURI= ConfigurationReader.getProperty("ords.url");
    }

    /**
     * Given accept type is json
     * When user send get request to /ords/hr/regions
     * Status code should be 200
     * Content type should be "application/json"
     * And body should contain "Europe"
     */
    @DisplayName("Get Request to /ords/hr/regions")
    @Test
    public void getAllRegionsTest(){

        Response response = RestAssured.given().accept(ContentType.JSON).when().get("/regions");
        System.out.println("Status code = " + response.statusCode());
        Assertions.assertEquals(200,response.statusCode());

        System.out.println("ORDS content type = " + response.contentType());
        Assertions.assertEquals("application/json",response.contentType());

        Assertions.assertTrue(response.prettyPrint().contains("Europe"));
    }

    /**
     * Given accept type is json
     * And Path param value is 1
     * When user sends get request to /ords/hr/regions/{region_id}
     * Status code should be 200
     * Content type should be "application/json"
     * And body should contain "Europe"
     */

    @DisplayName("Get Request to /ords/hr/regions/1")
    @Test
    public void getSingleRegionsWithParam(){
        Response response = RestAssured.given().accept(ContentType.JSON).when().get("/regions/1");
        System.out.println("status code = " + response.statusCode());
        Assertions.assertEquals(200,response.statusCode());

        System.out.println("content type = " + response.contentType());
        Assertions.assertEquals("application/json",response.contentType());

        Assertions.assertTrue(response.asString().contains("Europe"));

    }

    /**
     * Given accept type is json
     * And query param q = ""{region_name": "Americas"}"
     * When user send get request to /ords/hr/regions
     * Status code should be 200
     * Content type should be "application/json"
     * And region name should be "Americas"
     * And region id should be "2"
     */

    @DisplayName("Get Request to /ords/hr/regions with query param")
    @Test
    public void getRegionWithQueryParam(){
        Response response = RestAssured.given().accept(ContentType.JSON).when().queryParam("q","{\"region_name\": \"Americas\"}").when().get("regions");

        System.out.println("status code = " + response.statusCode());
        Assertions.assertEquals(200,response.statusCode());

        System.out.println("content type = " + response.contentType());
        Assertions.assertEquals("application/json",response.contentType());

        Assertions.assertTrue(response.asString().contains("Americas"));
        Assertions.assertTrue(response.asString().contains("2"));
    }
}
