package com.cybertek.tests.day11_put_request;

import com.cybertek.tests.SpartanTestBase;
import com.cybertek.tests.pojo.Spartan;
import com.cybertek.utilities.RestUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


public class UpdateSpartanTest extends SpartanTestBase {

    /**
     * given accept type is json
     * and content type is json
     * When i send put request to /api/spartans/640
     * With jsonbody: {
     *     "gender" : "Male",
     *     "name" : "Changed",
     *     "phone" : 1234567899
     * }
     * Then status code is 200
     * And content type is json
     * And json response should contain updated values
     */

    @Test
    public void updateSpartanTest(){
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("gender","Male");
        requestMap.put("name", "Changed");
        requestMap.put("phone", 1234569989);

        given().accept(ContentType.JSON).and().contentType(ContentType.JSON).and().body(requestMap).when().put("/api/spartans/640").then().assertThat().statusCode(204);
        System.out.println(requestMap);
//this api doesnt return response body so just verifying status code
    }

    /**
     * given accept type is json
     * and content type is json
     * When i send patch request to /api/spartans/640
     * With jsonbody: {
     *     "gender" : "Male",
     *     "name" : "Changed",
     *     "phone" : 1234569899
     * }
     * Then status code is 204
     */

    @Test
    public void patchSpartanTest(){

        Map<String,Object> reqMap = new LinkedHashMap<>();
        reqMap.put("phone",1119111112L);

        given().contentType(ContentType.JSON).and().body(reqMap).when().patch("/api/spartans/640").then().assertThat().statusCode(204);

        //sending get request to spartan id and verifying phone number update
        Spartan spartan = RestUtils.getSpartan(640);
        assertThat(spartan.getPhone(), is(reqMap.get("phone")));
    }
}
