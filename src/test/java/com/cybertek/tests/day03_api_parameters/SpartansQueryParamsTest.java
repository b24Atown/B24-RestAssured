package com.cybertek.tests.day03_api_parameters;
import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SpartansQueryParamsTest {

    @BeforeAll
    public static void setUp (){
        baseURI = ConfigurationReader.getProperty("spartan.url");
    }

    /**
     * Given accept type is Json
     * And query parameter values are: gender|Female nameContains|e
     * When User sends Get request to /api/spartans/search
     * Then response status code should be 200
     * And response content-type: application/json
     * And "Female" should be in response body
     * And "Janette" should be in response body
     */

    @Test
    public void searchForSpartanUsingQueryTest(){
        Response response = given().accept(ContentType.JSON).and().queryParams("gender","Female","nameContains","e").when().get("/api/spartans/search");

        System.out.println("status code = " + response.statusCode());
        Assertions.assertEquals(200,response.statusCode());

        System.out.println("content type = " + response.contentType());
        Assertions.assertEquals("application/json",response.contentType());

        response.prettyPrint();
        Assertions.assertTrue(response.prettyPrint().contains("Female"));
        Assertions.assertTrue(response.prettyPrint().contains("Janette"));

    }

}
