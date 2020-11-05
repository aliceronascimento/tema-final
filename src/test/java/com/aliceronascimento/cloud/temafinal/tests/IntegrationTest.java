package com.aliceronascimento.cloud.temafinal.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import org.junit.Test;

public class IntegrationTest {

    @Test
    public void calculateSumWebTest() {

        given().when().get("http://localhost:8080/tema-06/calculator?num1=7&num2=4&operation=SUM")
                .then()
                .body(containsString("Operation: 7.0 + 4.0 = 11.0"));
    }

    @Test
    public void calculateSubtractionWebTest() {

        given().when().get("http://localhost:8080/tema-06/calculator?num1=4&num2=1&operation=SUB")
                .then()
                .body(containsString("Operation: 4.0 - 1.0 = 3.0"));
    }

    @Test
    public void calculateMultiplicationWebTest() {

        given().when().get("http://localhost:8080/tema-06/calculator?num1=3&num2=2&operation=MULT")
                .then()
                .body(containsString("Operation: 3.0 * 2.0 = 6.0"));
    }

    @Test
    public void calculateDivisionWebTest() {
        given().when().get("http://localhost:8080/tema-06/calculator?num1=12&num2=2&operation=DIV")
                .then()
                .body(containsString("Operation: 12.0 / 2.0 = 6.0"));
    }

    @Test
    public void calculatePowWebTest() {
        given().when().get("http://localhost:8080/tema-06/calculator?num1=12&num2=2&operation=POW")
                .then()
                .body(containsString("Operation: 12.0 ^ 2.0 = 144.0"));
    }

    @Test
    public void getHistoryWebTest() {
        given().when().get("http://localhost:8080/tema-06/calculator?history")
                .then()
                .body(containsString("Operation: 3.0 * 2.0 = 6.0, \n" +
                        "Operation: 12.0 ^ 2.0 = 144.0, \n" +
                        "Operation: 7.0 + 4.0 = 11.0, \n" +
                        "Operation: 12.0 / 2.0 = 6.0, \n" +
                        "Operation: 4.0 - 1.0 = 3.0"));
    }

    @Test
    public void calculateDivisionByzeroWebTest() {
        given().when().get("http://localhost:8080/tema-06/calculator?num1=12&num2=0&operation=DIV")
                .then().statusCode(500);
    }
}