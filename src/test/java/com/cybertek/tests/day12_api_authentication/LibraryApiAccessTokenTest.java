package com.cybertek.tests.day12_api_authentication;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class LibraryApiAccessTokenTest {


    /**
     * given accept is json
     * and form paramerters email and password
     * with values "studen30@library", "Sdet2022*"
     * when i send post request to https://library2.cybertekschool.com/rest/v1/login
     * then status code 200
     * and i can extract the access token
     */

    @Test
    public void getLibraryTokenTest(){

      String token =  given().accept(ContentType.JSON).and().formParams("email","student30@library","password","Sdet2022*").when().post("https://library2.cybertekschool.com/rest/v1/login").then().assertThat().statusCode(200).and().extract().body().path("token");
        System.out.println("token = " + token);

    }

    String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiMTYwIiwiZnVsbF9uYW1lIjoiVGVzdCBTdHVkZW50IDMwIiwiZW1haWwiOiJzdHVkZW50MzBAbGlicmFyeSIsInVzZXJfZ3JvdXBfaWQiOiIzIn0sImlhdCI6MTY0NDYxNDEwNiwiZXhwIjoxNjQ3MjA2MTA2fQ.-DYps4rmdC3IZ4GnlMFWj3MK6EjhyMXCrv9tVsUNAmY";

    @Test
    public void getBooksForBorrowing(){
        Response response = given().accept(ContentType.JSON).and().header("x-library-token", accessToken).when().get("https://library2.cybertekschool.com/rest/v1/get_book_list_for_borrowing");
        System.out.println("status code = " + response.statusCode());

    }

}
