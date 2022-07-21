package com.cybertek.tests.day11_put_request;
import com.cybertek.tests.ORDSTestBase;
import com.cybertek.tests.pojo.countries_us.Region;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ORDSUpdateExistingRegionTest extends ORDSTestBase {

/**
 * given accept type json
 * and content type  json
 * when i send put request to /regions/999
 * with jsonBody: {
 *     "region_id" : 681,
 *     "region_name" : "Teste Region"
 * }
 * then status code should be 200
 * and content type should be Json
 * and json response should contain updated values
 */
    @Test
    public void updateExistingRegionTest(){
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("region_id",681);
        requestMap.put("region_name","Teste Region");

       Map<String,Object> responseMap  = given().accept(ContentType.JSON).and().contentType(ContentType.JSON).and().body(requestMap).when().put("/regions/681").then().assertThat().statusCode(200).and().contentType("application/json").and().extract().body().as(Map.class);
        System.out.println(requestMap);
        System.out.println(responseMap);

        //verify json response should contain updated values
        assertThat(requestMap.get("region_id"), is(responseMap.get("region_id")));
        assertThat(requestMap.get("region_name"), is(responseMap.get("region_name")));

    }

    @Test
    public void updateExistingRegionPojoTest(){

        //create pojo instance for put requeset
        Region reqRegion = new Region();
        reqRegion.setRegionId(999);
        reqRegion.setRegionName("Teste Region");

       Region resRegion = given().accept(ContentType.JSON).and().contentType(ContentType.JSON).and().body(reqRegion).when().put("/regions/681").then().assertThat().statusCode(200).and().contentType("application/json").and().extract().body().as(Region.class);

       //verify json response should contain updated values
        assertThat(reqRegion.getRegionId(), is(reqRegion.getRegionId()));
        assertThat(reqRegion.getRegionName(), is(resRegion.getRegionName()));

    }



}
