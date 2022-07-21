package com.cybertek.tests.day05_jsonPath;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class ORDSGetRequestWithJsonPathTest extends ORDSTestBase {


    /**
     * Given accept is Json
     * When I send get request to ords/hr/employees/103
     * Then status code is 200
     * And content type is "application/json"
     * And employee_id is 103
     * And first_name is "Alexander"
     * And last_name is "Hunold"
     * And salary is "9000"
     */

    @Test
    public void jsonPathSingleEmployeeInfo(){
        Response response = given().accept(ContentType.JSON).and().get("/employees/103");

        System.out.println("status code = " + response.statusCode());
        Assertions.assertEquals(200,response.statusCode());

        System.out.println("content type header = " + response.contentType());
        Assertions.assertEquals("application/json",response.contentType());

        response.prettyPrint();
        //assign json response body to JsonPath object
        JsonPath json = response.jsonPath(); //this puts all info from emp103 to jsonpath so its converting response body to jsonpath

        //read values from jsonPath object
        int empID = json.getInt("employee_id");
        String firstName = json.getString("first_name");
        String lastName = json.getString("last_name");
        int salary = json.getInt("salary");

        System.out.println("salary = " + salary);
        System.out.println("lastName = " + lastName);
        System.out.println("firstName = " + firstName);
        System.out.println("empID = " + empID);

        Assertions.assertEquals(103,empID);
        Assertions.assertEquals("Alexander",json.getString("first_name"));
        Assertions.assertEquals("Hunold",lastName);
        Assertions.assertEquals(9000,salary);
    }

    @DisplayName("Get oreds/hr/employees and using jsonPath filters")
    @Test
    public void jsonPathFilterTest(){

        Response response = given().accept(ContentType.JSON).and().queryParam("limit",500).when().get("/employees");

        System.out.println("status code = " + response.statusCode());
        Assertions.assertEquals(200,response.statusCode());

        JsonPath json = response.jsonPath();

        //employees that work in department 90
        List<String> empList = json.getList("items.findAll{it.department_id==90}.first_name");
        System.out.println(empList);

        //names of employees who are "IT_PROG"

        List<String> itProgList = json.getList("items.findAll{it.job_id=='IT_PROG'}.first_name");
        System.out.println(itProgList);

        //emp ids of employees whose salary is more than 5000
        List<String> empSalary5000 = json.getList("items.findAll{it.salary>=5000}.employee_id");
        System.out.println(empSalary5000);

        //find the person firstname with maximum salary
        String firstNameMaxSalary = json.getString("items.max{it.salary}.first_name");
        System.out.println(firstNameMaxSalary);
        String firstNameMinSalary = json.getString("items.min{it.salary}.first_name");
        System.out.println(firstNameMinSalary);


    }
}
