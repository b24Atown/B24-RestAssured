package com.cybertek.tests.officeHours;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.xml.ws.RequestWrapper;

import java.util.Map;

import static io.restassured.RestAssured.*;


public class ZipCodeHomeworkTest {

    /**
     * Given Accept application/json
     * And path zipcode is 22031
     * When I send a GET request to /us endpoint
     * Then status code must be 200
     * And content type must be application/json
     * And Server header is cloudflare
     * And Report-To header exists
     * And body should contains following information
     *     post code is 22031
     *     country  is United States
     *     country abbreviation is US
     *     place name is Fairfax
     *     state is Virginia
     *     latitude is 38.8604
     */

    @BeforeAll
    public static void setUp(){
        baseURI = "http://api.zippopotam.us";
    }

    @Test
    public void zipCodeTest(){
        Response response = given().accept(ContentType.JSON).and().pathParam("postal-code",22031).when().get("/us/{postal-code}");

        System.out.println("status code = " + response.statusCode());
        Assertions.assertEquals(200,response.statusCode());

        Assertions.assertEquals("application/json",response.contentType());

        System.out.println("server = " + response.getHeader("Server"));
        Assertions.assertEquals("cloudflare",response.getHeader("Server"));

        System.out.println("report to exists? = " + response.getHeaders().hasHeaderWithName("Report-To"));
        Assertions.assertTrue(response.getHeaders().hasHeaderWithName("Report-To"));

        //jsonbody verification

        JsonPath json = response.jsonPath();
        System.out.println("post code = " + json.getString("\"post code\""));
        Assertions.assertEquals("22031",json.getString("\"post code\""));

        System.out.println("country = " + json.getString("country"));
        Assertions.assertEquals("United States",json.getString("country"));

        System.out.println("country abbreviation = " + json.getString("\"country abbreviation\""));
        Assertions.assertEquals("US",json.getString("\"country abbreviation\""));

        System.out.println("place name = " + json.getString("places[0].'place name'"));
        Assertions.assertEquals("Fairfax", json.getString("places[0].'place name'"));

        System.out.println("state = " + json.getString("places[0].state"));
        Assertions.assertEquals("Virginia",json.getString("places[0].state"));

        System.out.println("latitude = " + json.getString("places[0].latitude"));
        Assertions.assertEquals("38.8604",json.getString("places[0].latitude"));



    }

    @Test
    public void zipcodeJsonToMapTest(){
        Response response = given().accept(ContentType.JSON).and().pathParam("postal-code",22031).when().get("/us/{postal-code}");

        //Deserialization Json - Map
        Map<String,Object > jsonMap = response.as(Map.class);
        // jsonMap.put("post code", "22031");

        System.out.println("jsonMap = " + jsonMap);

        System.out.println("post code = " + jsonMap.get("post code"));
        Assertions.assertEquals("22031", jsonMap.get("post code"));

        System.out.println("country = " + jsonMap.get("country"));
        Assertions.assertEquals("United States",jsonMap.get("country"));

        System.out.println("country abbreviation = " + jsonMap.get("country abbreviation"));
        Assertions.assertEquals("US",jsonMap.get("country abbreviation"));

        System.out.println("place info = " + jsonMap.get("places"));


    }

}
