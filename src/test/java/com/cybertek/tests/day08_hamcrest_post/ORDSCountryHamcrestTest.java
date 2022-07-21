package com.cybertek.tests.day08_hamcrest_post;

import com.cybertek.tests.ORDSTestBase;
import com.cybertek.tests.pojo.countries_us.Countries;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSCountryHamcrestTest extends ORDSTestBase {

    /**
     * send get request to /ords/hr/countries/ZW
     * verify status code is 200
     * header is json
     * and extract body as country  pojo object
     */

    @Test
    public void getCountryToPojoTest(){
        Countries coutry = given().accept(ContentType.JSON).when().get("/countries/ZW").then().assertThat().statusCode(200).and().contentType("application/json").and().extract().body().as(Countries.class);
        System.out.println("country = " + coutry);

    }

}
