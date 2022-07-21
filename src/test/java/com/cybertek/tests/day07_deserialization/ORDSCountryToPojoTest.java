package com.cybertek.tests.day07_deserialization;

import com.cybertek.tests.ORDSTestBase;
import com.cybertek.tests.pojo.countries_us.Countries;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class ORDSCountryToPojoTest extends ORDSTestBase {

    @Test
    public void countryToPojoTest(){
        Response response = given().accept(ContentType.JSON).when().get("/countries/US");
        System.out.println("status code = " + response.statusCode());
        Assertions.assertEquals(HttpStatus.SC_OK,response.statusCode());
        System.out.println(("content type = " + response.contentType()));
        Assertions.assertEquals("application/json",response.contentType());

        Countries country = response.as(Countries.class);
        System.out.println(country.getCountry_id());
        System.out.println(country.getCountry_name());
        System.out.println(country.getRegion_id());


    }
}
