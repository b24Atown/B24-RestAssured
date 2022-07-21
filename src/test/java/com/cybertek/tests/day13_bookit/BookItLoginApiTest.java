package com.cybertek.tests.day13_bookit;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class BookItLoginApiTest {

    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("bookit.base.url");
    }

    /**
     * given accept type is json
     * and query params: email = lfinnisz@yolasite.com
     * & password = lissiefinnis
     * When i send get request to /sign
     * then status code is 200
     * and response json body contains the token
     */

    @Test
    public void loginAsTeamLeaderTest(){
        given().accept(ContentType.JSON).and().queryParams("email","lfinnisz@yolasite.com","password","lissiefinnis").when().get("/sign").then().assertThat().statusCode(200).and().body("accessToken",not(emptyString())).log().all();

    }
}
