package com.cybertek.tests.officeHours;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class PetStoreGetRequestTest {

    /**
     * accept type is json
     * get request to /store/inventory
     * then status code is 200
     * and content type is application/json
     * and available is >500
     */

    @BeforeAll
    public static void setUp(){
        baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void getInventoryTest(){
        Response response = given().accept(ContentType.JSON).when().get("/store/inventory");

        System.out.println("content type = " + response.contentType());
        Assertions.assertEquals("application/json",response.contentType());

        System.out.println("status code = " + response.statusCode());
        Assertions.assertEquals(200,response.statusCode());

        JsonPath json = response.jsonPath();

        System.out.println("available count " + json.getInt("available"));
        Assertions.assertTrue(json.getInt("available")>500);


    }
    /**
     * accept type is json
     * order id is 2
     * get request to /store/order/2
     * then status code is 200
     * and content type is application/json
     * and id is 2, pet id is 2, status is "placed", complete is true
     */

//    @Test
//    public void getOrderPathParamTest(){
//        Response response = given().accept(ContentType.JSON).and().pathParam("orderId",2).when().get("/store/order/{orderId}");
//        //can also do .when().get("/store/oder/2")
//        Assertions.assertEquals(200,response.statusCode());
//        Assertions.assertEquals("application/json",response.contentType());
//        JsonPath json = response.jsonPath();
//        Assertions.assertEquals(2,json.getInt("id"));
//        Assertions.assertEquals(2,json.getInt("petId"));
//        Assertions.assertEquals("placed",json.getString("status"));
//        Assertions.assertTrue(json.getBoolean("complete"));
//    }

    /**
     * accept type is json
     * query param status is "available"
     * get request to /pet/findByStatus
     * then status code is 200
     * and content type is application/json
     * And see all pet names
     * And all status values should be available
     */

    @Test
    public void searchPetsByStatusTest(){

        Response response = given().accept(ContentType.JSON).and().queryParam("status","available").when().get("/pet/findByStatus");

        System.out.println("status code = " + response.statusCode());
        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("application/json",response.contentType());

        JsonPath json = response.jsonPath();


        List<String> allNames = json.getList("name");
        System.out.println("allNames = " + allNames);

        List<String> allStatus = json.getList("status");
        System.out.println("allStatus = " + allStatus);
        for (String eachStatus:  allStatus){
            Assertions.assertEquals("available",eachStatus);
        }
    }
}
