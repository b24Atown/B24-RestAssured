package com.cybertek.tests.day07_deserialization;

import com.cybertek.tests.SpartanTestBase;
import com.cybertek.tests.pojo.AllSpartans;
import com.cybertek.tests.pojo.Spartan;
import com.cybertek.tests.pojo.SpartanSearch;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class AllSpartansToPojoTest extends SpartanTestBase {

    @Test
    public void getAllSpartansTest(){
        Response response = given().accept(ContentType.JSON).when().get("/api/spartans");
        System.out.println("status code " + response.statusCode());

        //AllSpartans allSpartans = response.as(AllSpartans.class);
        List<Spartan> allSpartans = response.jsonPath().getList("",Spartan.class);
        System.out.println(allSpartans.get(0));
        System.out.println("total spartans count = " + allSpartans.size());

        //loop through the list and print each spartan info in separate line:

        for (Spartan eachSpartan: allSpartans){
            if(eachSpartan.getGender().equals("Male")){
                System.out.println(eachSpartan);
            }
        }

    }

    @Test
    public void searchSpartanToPojoTest(){
        Response response = given().accept(ContentType.JSON).and().queryParam("nameContains","ai").and().queryParam("gender","Female").when().get("/api/spartans/search");

        //De-serialization. jsonbody to spartanSearch java object
        System.out.println("status code = " + response.statusCode());
        SpartanSearch spartanSearch = response.as(SpartanSearch.class);
        System.out.println("total element = " + spartanSearch.getTotalElement());
        System.out.println("spartan info content = "+spartanSearch.getContent());
        System.out.println("spartan count = " + spartanSearch.getContent().size());

        //store Jaimie info into seperate variable;
        Spartan first = spartanSearch.getContent().get(0);
        System.out.println("Jaimie info = " + first);
        System.out.println("name = " + first.getName());
        System.out.println("id = " + first.getId());
        System.out.println("gender = " + first.getGender());
        System.out.println("phone = " + first.getPhone());
        Assertions.assertEquals(13,first.getId());
        Assertions.assertEquals("Jaimie",first.getName());
        Assertions.assertEquals("Female", first.getGender());
        Assertions.assertEquals(7842554879L,first.getPhone());

    }
}
