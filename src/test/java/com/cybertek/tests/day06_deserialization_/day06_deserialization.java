package com.cybertek.tests.day06_deserialization_;

import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  static io.restassured.RestAssured.*;

public class day06_deserialization extends SpartanTestBase {

    /**
     * Given accept type is json
     * When i send get request to /api/spartans/24
     * then status code is 200
     * and content type is json
     * and id is 24, name is Julio, gender is Male, phone is 9393139934
     */
    @Test
    public void singleSpartanToMapTest(){
        Response response  = given().accept(ContentType.JSON).when().get("/api/spartans/24");
        Map<String,Object> spartanMap = response.as(Map.class);
        System.out.println("spartanMap = " + spartanMap);
        System.out.println("id = " + spartanMap.get("id"));
        Assertions.assertEquals(24,spartanMap.get("id"));

        System.out.println("name = " + spartanMap.get("name"));
        Assertions.assertEquals("Julio",spartanMap.get("name"));

        System.out.println("phone = " + spartanMap.get("phone"));
        Assertions.assertEquals(9393139934L,spartanMap.get("phone"));

        System.out.println("gender = " + spartanMap.get("gender"));
        Assertions.assertEquals("Male", spartanMap.get("gender"));

        //create new map with expected values and compare 2 maps. not neccessary just diff approach
        Map<String,Object> expecged = new HashMap<>();
        expecged.put("id",24);
        expecged.put("name","Julio");
        expecged.put("gender","Male");
        expecged.put("phone",9393139934L);
        Assertions.assertEquals(expecged,spartanMap);

    }

    @Test
    public void allSpartansToMapListTest(){
        Response response = given().accept(ContentType.JSON).when().get("/api/spartans");

        List<Map<String,Object>> spartansList = response.as(List.class);
        System.out.println("spartansList = " + spartansList);

        System.out.println("first spartan info = " + spartansList.get(0));
        System.out.println("second spartan info = " + spartansList.get(1));
        System.out.println("third spartans id = " + spartansList.get(2).get("id"));

        //print everyones info like id
        for (Map<String,Object> each:spartansList){
            System.out.println(each.get("id"));
        }
    }

}
