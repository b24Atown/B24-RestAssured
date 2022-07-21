package com.cybertek.tests.day05_jsonPath;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class TypiCodeInterviewTest {

    @BeforeAll
    public static void setUp(){
        baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void getUserTest (){
        Response response = given().accept(ContentType.JSON).when().get("/users/1");

        System.out.println("status code = " + response.statusCode());
        Assertions.assertEquals(200,response.statusCode());

        response.prettyPrint();

        JsonPath json = response.jsonPath();
        System.out.println("name = " + json.getString("name"));
        System.out.println("city = " + json.getString("address.city"));
        Assertions.assertEquals("Leanne Graham", json.getString("name"));
        Assertions.assertEquals("Gwenborough", json.getString("address.city"));

        //company name
        System.out.println("company name = " + json.getString("company.name"));
        Assertions.assertEquals("Romaguera-Crona",json.getString("company.name"));

        //lng
        System.out.println("lng = " + json.getString("address.geo.lng"));

    }
}
