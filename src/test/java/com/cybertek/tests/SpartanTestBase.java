package com.cybertek.tests;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class SpartanTestBase {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI= ConfigurationReader.getProperty("spartan.url");
    }
}
