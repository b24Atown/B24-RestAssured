package com.cybertek.tests.day09_post_put;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import  static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import  static org.hamcrest.MatcherAssert.assertThat;

public class ORDSCountriesHamcrestTest extends ORDSTestBase {

    /**
     * given accept type is json
     * when i send get request to /countries
     * then status code is 200
     * and content type is json
     * and count should be 25
     * and country ids should contain "AR,AU,BE,BR,CA"
     * and country names should have "Argentina,Australia,Belgium,Brazil,Canada"
     */

    @Test
    public void allCountriesHamcrestTest(){
        given().accept(ContentType.JSON).when().get("/countries").then().assertThat().statusCode(200).and().contentType("application/json").and().body("count",equalTo(25),"items.country_id",hasItems("AR","AU","BE","BR","CA"),"items.country_name", hasItems("Argentina","Australia","Belgium","Brazil","Canada")).log().all();

        //Second way of using hamcrest for api response verifications.

        Response response = given().accept(ContentType.JSON).when().get("/countries");

        assertThat(response.statusCode(),equalTo(200));
        assertThat(response.contentType(),equalTo("application/json"));
        assertThat(response.path("count"), is(25));
        assertThat(response.path("items.country_id"),hasItems("AR","AU","BE","BR","CA"));
        assertThat(response.path("items.country_name"),hasItems("Argentina","Australia","Belgium","Brazil","Canada"));
    }
}
