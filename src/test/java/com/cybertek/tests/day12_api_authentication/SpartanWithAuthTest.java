package com.cybertek.tests.day12_api_authentication;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class SpartanWithAuthTest {

    @BeforeAll
    public static void setUp(){

        baseURI = "http://3.84.251.99:7000";

    }
    /**
     * Given accept type is json
     * And basic auth credentials are “admin”,”admin”
     * When I send get request to  “/api/spartans"
     * Then status code is 200
     * And content type is json
     * And json response should have Spartan list
      */

    @Test
    public void adminGetAllSpartans(){
      List<Integer> allIds =  given().accept(ContentType.JSON).and().auth().basic("admin","admin").when().get("/api/spartans").then().assertThat().statusCode(200).and().contentType("application/json").and().extract().body().path("id");
        System.out.println("all ids = " + allIds);
    }

    /**
     * Given accept type is json
     * And no credentials
     * When I send get request to  “/api/spartans
     * Then status code is 401
     * And content type is json
     * And json response should have message: Unauthorized
     */

    @Test
    public void noAuthGetSpartansNegativeTest(){

        given().accept(ContentType.JSON).when().get("/api/spartans").then().assertThat().statusCode(401).and().contentType(ContentType.JSON).and().body("message",is("Unauthorized"));

    }

}
