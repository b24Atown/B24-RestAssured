package com.cybertek.tests.day02_headers_params;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartanGetRequestsApiTest {
    /**
     * WHen I send a Get  request to  spartan_base_url/api/spartans
     * Then Response status code must be 200
     * And I  should see all Spartans data in Json format
     */

    String baseUrl = ConfigurationReader.getProperty("spartan.url");

    @Test
    public void getAllSpartansTest(){
        Response response  = RestAssured.get(baseUrl+"/api/spartans");
        System.out.println("status  code = "+ response.statusCode());
        Assertions.assertEquals(200,response.statusCode());

        response.prettyPrint();
        Assertions.assertTrue(response.asString().contains("Florrie"));
    }

    /**
     * Given Accept type is "application/json
     * when i send a get request to spartan_base/api/spartans
     * Then response status code must be 200
     * And content type should be "application/json"
     */

    @Test
    public void allSpartansHeaderTest(){
        Response response = RestAssured.given().accept(ContentType.JSON)//add header to req
                .when().get(baseUrl + "/api/spartans");

        System.out.println("status code =" + response.statusCode());
        Assertions.assertEquals(200,response.statusCode());
        System.out.println(response.contentType()); //read content type response header
        Assertions.assertEquals("application/json",response.contentType());

        //print more response headers
        System.out.println("date header value = " + response.getHeader("Date"));
        System.out.println("transer encoding = " + response.getHeader("Transfer-Encoding"));
        Assertions.assertTrue(response.getHeaders().hasHeaderWithName("Date"));

    }
}
