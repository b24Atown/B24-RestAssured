package com.cybertek.tests.day08_hamcrest_post;

import com.cybertek.tests.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanHamcrestTest extends SpartanTestBase {

    @Test
    public void singleSpartanTest(){
        RestAssured.given().accept(ContentType.JSON).when().get("/api/spartans/24").then().assertThat().statusCode(200).and().contentType("application/json").and().body("id",equalTo(24),"name", equalTo("Julio"),"phone",equalTo(9393139934L),"gender", equalTo("Male")).log().all();
    }

    @Test
    public void getMapTest(){
        /**
         * we send get request, verify status code and header then convert json body to map object and return
         * DE-SERIALIZATION
         */
       Map<String,Object> spartanMap = RestAssured.given().accept(ContentType.JSON).when().get("/api/spartans/24").then().assertThat().statusCode(200).and().contentType("application/json").log().all().and().extract().body().as(Map.class);  //convert jsonbody to map object and return
        System.out.println(spartanMap);

        //read id value from map then assert its 24
        assertThat(spartanMap.get("id"), equalTo(24));

        //check the keys of json response
        assertThat(spartanMap.keySet(),containsInAnyOrder("id","name","gender","phone"));

        assertThat(spartanMap.values(), hasItems("Julio",24,"Male",9393139934L));

    }

    /**
     * given accept type json
     * get request to /api/spartans
     * then status code is 200
     * and content type is json
     * then extract names of spartans into a list<String>
     */
    @Test
    public void getSpartanNamesTest(){

       List<String> names = given().accept(ContentType.JSON).when().get("/api/spartans").then().assertThat().statusCode(200).and().contentType("application/json").and().extract().body().jsonPath().getList("name");
        System.out.println("names = " + names);
        assertThat(names,hasSize(178));
        assertThat(names,hasItems("Sol","Lorenza","Florrie"));

    }

    /**
     * given accept type json
     * name contains "v"
     * gender is  "Male"
     * get request to /api/spartans/search
     * then status code is 200
     * and content type is json
     * and return totalElem  value as an int
     */

    @Test
    public void getTotalElementTest(){
       int totalElement = given().accept(ContentType.JSON).and().queryParam("nameContains","v").and().queryParam("gender","Male").when().get("/api/spartans/search").then().assertThat().statusCode(200).and().contentType("application/json").log().all().extract().path("totalElement");
        System.out.println("totalElement = "+totalElement);

        assertThat(totalElement,is(equalTo(4)));
    }

    /**
     * get /api/spartans/2400
     * in signe statement, verify status code 404 and status is "not found"
     */

    @Test
    public void invalidSpartanTest(){
        given().accept(ContentType.JSON).when().get("/api/spartans/2400").then().assertThat().statusCode(404).and().body("error",equalTo("Not Found"));

        //other way
        Response response = given().accept(ContentType.JSON).when().get("/api/spartans/2400");
        Assertions.assertEquals(404,response.statusCode());
        Assertions.assertEquals("Not Found", response.path("error"));
    }

}
