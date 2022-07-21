package com.cybertek.tests.day05_jsonPath;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ORDSGetEmployeesPathTest extends ORDSTestBase {

    //IP/ords/hr/employees?q={"job_id":"IT_PROG"}

    @Test
    public void getAllITProgrammersTest(){
        //query param with hashmap
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("q","{\"job_id\":\"IT_PROG\"}");

        Response response = RestAssured.given().accept(ContentType.JSON).and().queryParams(paramMap).when().get("/employees");

        System.out.println("status code = " + response.statusCode());
        response.prettyPrint();

        //print first employees emp id, first name, last name, email
        System.out.println("first emp id = " + response.path("items[0].employee_id"));
        System.out.println("first emp first_name = " + response.path("items[0].first_name"));
        System.out.println("first emp last name = " + response.path("items[0].last_name"));
        System.out.println("first emp email = " + response.path("items[0].email"));

        //you want to email all IT_PROGs, save all emails into List of string

        List<String> itProgEmail = response.path("items.email");
        System.out.println("all emails = " + itProgEmail);

        //you want to text all IT_PROGS, save all phones into List of string
        List<String> allPhones = response.path("items.phone_number");
        System.out.println("all phones = " + allPhones);

        //verify that 590.423.4567 is among phone numbers
        Assertions.assertTrue(allPhones.contains("590.423.4567"));

    }

}
