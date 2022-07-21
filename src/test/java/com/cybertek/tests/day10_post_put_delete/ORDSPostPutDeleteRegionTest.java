package com.cybertek.tests.day10_post_put_delete;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ORDSPostPutDeleteRegionTest extends ORDSTestBase {

    /**
     * given accept is json
     * and content type is json
     * when i send post request to "/regions"
     * With json: {
     *     "region_id":681,
     *     "region_name":"Test Region"
     * }
     * Then status code is 681
     * content type is json
     * region id is 100
     * region_name is Test Region
     */

    @Test
    public void postARegionTest(){
        //delete region by id, before posting:
        deleteRegion(681);
        Map<String,Object> regionRequestMap = new LinkedHashMap<>();
        regionRequestMap.put("region_id",681);
        regionRequestMap.put("region_name","Test Region");


        Map<String,Object> regionResponseMap = given().accept(ContentType.JSON).and().contentType(ContentType.JSON).and().body(regionRequestMap).when().post("/regions/").then().assertThat().statusCode(201).and().contentType(ContentType.JSON).and().extract().body().as(Map.class);

        System.out.println("regionResponseMap = " + regionResponseMap);
        Assertions.assertEquals(regionRequestMap.get("region_id"), regionResponseMap.get("region_id"));
        Assertions.assertEquals(regionRequestMap.get("region_name"),regionResponseMap.get("region_name"));

        // lets also send a get request to verify everything.
        Map<String,Object> getRequestMap = given().accept(ContentType.JSON).when().get("/regions/681").then().statusCode(200).and().contentType(ContentType.JSON).and().extract().body().as(Map.class);

        //verify getRequestMap details match regionRequestMap of post
        Assertions.assertEquals(regionRequestMap.get("region_id"), getRequestMap.get("region_id"));
        Assertions.assertEquals(regionRequestMap.get("region_name"),getRequestMap.get("region_name"));


    }
    public void deleteRegion(int regionId){
        delete("/regions/" + regionId);
    }


}
