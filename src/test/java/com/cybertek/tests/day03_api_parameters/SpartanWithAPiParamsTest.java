package com.cybertek.tests.day03_api_parameters;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;

public class SpartanWithAPiParamsTest {

    @BeforeAll
    public static void setUp(){

        System.out.println("setup method: assigning value to baseURI variable");
        baseURI = ConfigurationReader.getProperty("spartan.url");

        /**
         * Given accept typee is json
         * And path parameter id is 24
         * When i send request to /api/spartans/24
         * Then status code should be 200
         * And content typee should be "application/json"
         * and json body should contain "Julio"
         */

    }

    @Test
    public void getSpartanPathParamTest(){

        Response response = given().accept(ContentType.JSON).when().get("/api/spartans/24");

        System.out.println("status code = " + response.statusCode());
        Assertions.assertEquals(200,response.statusCode());

        System.out.println("content type = " + response.contentType());
        Assertions.assertEquals("application/json", response.contentType());

        response.prettyPrint();
        Assertions.assertTrue(response.prettyPrint().contains("Julio"));
    }

    /**
     * Given Accept type is json
     * And path parameter id is 1000
     * WHen I send request to /api/spartans/1000
     * Then status code should be 404
     * And content type should be "application/json"
     * And json body should contain "Not Found"
     */

    @Test
    public void getSpartanPathParamNegativeTest(){
        Response response = given().accept(ContentType.JSON).and().pathParam("id",1000).when().get("/api/spartans/{id}");
        System.out.println("status line = " + response.statusLine());
        Assertions.assertEquals(404,response.statusCode());

        System.out.println("content type = " + response.contentType());
        Assertions.assertEquals("application/json",response.contentType());

        response.prettyPrint();
        Assertions.assertTrue(response.prettyPrint().contains("Not Found"));
    }

}
