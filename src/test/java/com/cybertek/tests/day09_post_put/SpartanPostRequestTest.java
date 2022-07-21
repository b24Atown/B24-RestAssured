package com.cybertek.tests.day09_post_put;

import com.cybertek.tests.SpartanTestBase;
import com.cybertek.tests.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class SpartanPostRequestTest extends SpartanTestBase {

    /**
     * given accept is json
     * and content type is json
     * when i send post  request to /api/spartans
     * with {
     *     "gender" : "Female",
     *     "name": "SayMyName2",
     *     "phone": 9684928493
     * }
     * Then status code is 201
     * and "spartan is born" message should be displayed
     */

    @Test
    public void postSpartanTest(){

        String requestJson = "{\n" +
                "    \"gender\" : \"Female\",\n" +
                "    \"name\": \"SayMyName2\",\n" +
                "    \"phone\": 9684928493\n" +
                "}";
        Response response = given().accept(ContentType.JSON).and().contentType(ContentType.JSON).and().body(requestJson).when().post("/api/spartans");

        System.out.println("status code = " + response.statusCode());
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));
        //verify json body response  and "A Spartan is Born!" msg should be displayed
        System.out.println("message = " + response.path("success"));
        assertThat(response.path("success"), equalTo("A Spartan is Born!"));

    }

    @Test
    public void postSpartanWithMapTest(){ //serialization happening here
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("name","SayMyName3");
        requestMap.put("gender","Male");
        requestMap.put("phone",1234567899);

        Response response = given().accept(ContentType.JSON).and().contentType(ContentType.JSON).and().body(requestMap).when().post("/api/spartans");
        System.out.println("status code = " + response.statusCode());
        /**
         * staus code is 201, content type is json, name is "SayMyName3", gender is "Male"
         * phone is 1234567899
         */
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));
        JsonPath json = response.jsonPath();
        assertThat(json.getString("data.name"),is("SayMyName3"));
        assertThat(json.getString("data.gender"),is("Male"));
        assertThat(json.getInt("data.phone"),is(1234567899));
        assertThat(json.getInt("data.id"),greaterThan(0));
    }

    @Test
    public void postSpartanAndVerifyWithMapTest(){
        //serialization
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("name","SayMyName4");
        requestMap.put("gender","Male");
        requestMap.put("phone",1234567899);

        //deserialization
        Response response = given().accept(ContentType.JSON).and().contentType(ContentType.JSON).body(requestMap).when().post("/api/spartans");
        assertThat(response.statusCode(), is(201));
        Map<String,Object> responseMap = response.jsonPath().getMap("data");
        System.out.println("responseMap = " + requestMap);
        //compare response data is matching with request data
        assertThat(responseMap.get("name"), equalTo(requestMap.get("name")));
        assertThat(responseMap.get("gender"), equalTo(requestMap.get("gender")));
        assertThat(responseMap.get("phone"), equalTo(requestMap.get("phone")));
    }

    @Test
    public void postSpartanWithPojoTest(){
        //create object of spartan and set values
        Spartan reqSpartan = new Spartan();
        reqSpartan.setName("pojoPost");
        reqSpartan.setGender("Female");
        reqSpartan.setPhone(1234567899);

        //serialization of java object to json
        Response response = given().accept(ContentType.JSON).and().contentType(ContentType.JSON).and().body(reqSpartan).log().all().when().post("/api/spartans");
        //body(reqSpartans) is automatically converting spartan object to json

        System.out.println("reponse status code = " + response.statusCode());
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));
        //verify json body
        //assign response spartan info into another Spartan object then compare 2 spartan values
        //deserialization - json to java object
        Spartan resSpartan = response.jsonPath().getObject("data",Spartan.class);
        assertThat(resSpartan.getName(),equalTo(reqSpartan.getName()));
        assertThat(resSpartan.getPhone(), equalTo(reqSpartan.getPhone()));
        assertThat(resSpartan.getGender(), equalTo(reqSpartan.getGender()));



    }
}
