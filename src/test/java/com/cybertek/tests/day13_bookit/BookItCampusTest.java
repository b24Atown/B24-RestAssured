package com.cybertek.tests.day13_bookit;

import com.cybertek.tests.BookItTestBase;
import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseLogSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BookItCampusTest extends BookItTestBase {


    String accessToken = getAccessToken(ConfigurationReader.getProperty("teacher_email"), ConfigurationReader.getProperty("teacher_password"));

    @Test
    public void getALlCampusesTest(){

        System.out.println("accessToken = " + accessToken);

      JsonPath path = given().accept(ContentType.JSON).and().header("Authorization",accessToken).when().get("/api/campuses").then().assertThat().statusCode(200).and().extract().jsonPath();

        System.out.println(path.getList("clusters.rooms.name"));
        System.out.println(path.getList("location"));
    }

    @Test
    public void ilCampusTest(){
        /**
         * store request details in a variable
         */
        RequestSpecification reqSpec = given().accept(ContentType.JSON).and().header("Authorization",accessToken ).and().pathParam("campus_location","IL");

        given(reqSpec).when().get("/api/campuses/{campus_location}").then().assertThat().statusCode(200).and().body("location", is("IL"),"clusters[0].rooms.name", hasItems("google","facebook"));
    }

    @Test
    public void getRoomInfoTest(){
        /**
         * store details of response verification into a variable.
         * for reusibility
         */
        ResponseSpecification resSpec = expect().statusCode(200).and().contentType(ContentType.JSON).and().body("name",is("mit"),"description", is("mens et manus"), "capacity", is(6));

        given().accept(ContentType.JSON).and().header("Authorization",accessToken).when().get("/api/rooms/mit").then().spec(resSpec);
    }
}
