package com.cybertek.utilities;

import com.cybertek.tests.pojo.Spartan;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class RestUtils {

    /**
     * accepts spartan ID and returns pojo object containing spartan info
     */

    public static Spartan getSpartan(int spartanId){
      Spartan spartan =  given().accept(ContentType.JSON).and().pathParam("id",spartanId).when().get(ConfigurationReader.getProperty("spartan.url")+"/api/spartans/{id}").then().assertThat().statusCode(200).and().extract().body().as(Spartan.class);
        return spartan;
    }
}
