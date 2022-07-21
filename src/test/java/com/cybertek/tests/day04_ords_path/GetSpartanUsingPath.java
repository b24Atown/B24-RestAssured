package com.cybertek.tests.day04_ords_path;

import com.cybertek.tests.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GetSpartanUsingPath extends SpartanTestBase {

    /**
     * Given accept type is json
     *And path param id is 13
     * WHen I send get request to /api.spartans/{id}
     * Then status code is 200
     * And content type is Json
     * And id value is 13
     * And name is "Jaimie"
     * And gender is "Female"
     * And phone is "7842554879
     */

    @Test
    public void readJsonWithPathTest(){
        Response response = RestAssured.given().accept(ContentType.JSON).and().pathParam("id",13).when().get("/api/spartans/{id}");

        System.out.println("status code = " + response.statusCode());
        Assertions.assertEquals(200,response.statusCode());
        System.out.println("content type = " + response.contentType());
        Assertions.assertEquals("application/json",response.contentType());
        System.out.println("id = " + response.path("id"));
        System.out.println("name = " + response.path("name"));
        System.out.println("gender = " + response.path("gender"));
        System.out.println("phone = " + response.path("phone"));

        //storing into variables
        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        System.out.println("phone = " + phone);
        System.out.println("gender = " + gender);
        System.out.println("name = " + name);
        System.out.println("id = " + id);

        //assertions
        Assertions.assertEquals("Jaimie",name);
        Assertions.assertEquals("Female",gender);
        Assertions.assertEquals("13",Integer.toString(id));
       // Assertions.assertEquals(13,id); or like this
        Assertions.assertEquals("7842554879",Long.toString(phone));
    }

    /**
     * Given accept type is json
     * WHen I send request to /api/spartans
     * Then status code is 200
     * ANd content type is json
     * And i can navigate json array object
     */

    @Test
    public void readJsonArrayTest(){

        Response response = RestAssured.given().accept(ContentType.JSON).when().get("/api/spartans");

        System.out.println("status code = " + response.statusCode());
        Assertions.assertEquals(200,response.statusCode());

        System.out.println("content type = " + response.contentType());
        Assertions.assertEquals("application/json",response.contentType());

        System.out.println("id = " + response.path("id"));//all ids
        System.out.println("id of first spartan = " + response.path("[0].id"));
        System.out.println("name of second person = " + response.path("[1].name"));

        System.out.println("third person info");
        System.out.println(response.path("[2].id").toString());
        System.out.println(response.path("[2].name")+"");
        System.out.println(response.path("[2].gender").toString());
        System.out.println(response.path("[2].phone")+"");
        System.out.println("last person phone" );
        System.out.println(response.path("phone[-1]").toString());
        System.out.println("all info for first person");
        System.out.println(response.path("[0]").toString());
        System.out.println("print all ids");
        System.out.println(response.path("id").toString());

        List<Integer>id = response.path("id");
        for (Integer eachId:id){
            System.out.println(eachId);
        }

    }

}
