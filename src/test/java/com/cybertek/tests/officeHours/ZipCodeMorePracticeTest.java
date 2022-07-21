package com.cybertek.tests.officeHours;
import com.cybertek.tests.pojo.zipCode.Place;
import com.cybertek.tests.pojo.zipCode.ZIpInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class ZipCodeMorePracticeTest {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://api.zippopotam.us";
    }

    //pojo class for zipcode info json
    @Test
    public void zipCodePojoTest(){
        Response response = given().accept(ContentType.JSON).when().get("us/12189");
        assertThat(response.statusCode(),is(200));
        assertThat(response.contentType(),is("application/json"));
        //deserialization of json response body. Jackson json parser library
        ZIpInfo zIpInfo = response.as(ZIpInfo.class);
        System.out.println("zipinfo = " + zIpInfo);
        assertThat(zIpInfo.getPostCode(),is("12189"));
        assertThat(zIpInfo.getCountry(), is("United States"));
        assertThat(zIpInfo.getCountryAbbreviation(), is("US"));

        Place place = zIpInfo.getPlaces().get(0);
        assertThat(place.getPlaceName(), is("Watervliet"));
        assertThat(place.getLatitude(), is("42.7298"));
        assertThat(place.getLongitude(), is("-73.7123"));
        assertThat(place.getStateAbbreviation(), is("NY"));
        assertThat(place.getState(), is("New York"));

    }

    @Test
    public void zipCodeHamcrestChainingTest(){
        given().accept(ContentType.JSON).when().get("/us/20171").then().statusCode(200).and().contentType(ContentType.JSON).and().body("'post code'",equalTo("20171"),"country", equalTo("United States"), "'country abbreviation'",equalTo("US"),"places[0].'place name'", is("Herndon"), "places[0].longitude",is("-77.3928"),"places[0].state",is("Virginia"),"places[0].'state abbreviation'", is("VA"), "places[0].latitude", is("38.9252"));
    }
}
