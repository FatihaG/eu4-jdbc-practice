package apitests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class SpartanTestWithParameters {

    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI = "http://34.228.41.120:8000";

    }
    /*
    Given accept type is json
    and id parameter value is 5
    when user sends GET request to /api/spartans/{id}
    Then response status code should be 200
    and response content-type: application/json;charset=UTF-8
    And "Blythe" should be in response payload
     */

    @Test
    public void getSpartanID_positive_pathParam(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Blythe"));


    }

    /*
    given accept type is json
    and id parameter value is 500
    when user sends Get request to /api/spartans/{id}
    then response status code should be 404
    and response content-type: application/json;charset=UTF-8
    and spartan not found message should be in response payload
     */

    @Test
    public void getSpartanID_negative_pathParam(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),404);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Not Found"));

    }

    /*
    given accept type is json
    and query parameter values are;
    gender|female
    nameContains|e
    when user sends Get request to /api/spartans/search
    then response status code should be 200
    and response content-type: application/json;charset=UTF-8
    and "Female" should be in response payload
    and "Janette" should be in response payload
         */

    @Test

    public void positiveTestWithQueryParam(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender","Female")
                .and().queryParam("nameContains","e")
                .when().get("/api/spartans/search");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));


    }


    @Test
    public void positiveTestWithQueryParamWithMap(){
        Map<String,Object> querymap= new HashMap<>();
        querymap.put("gender","Female");
        querymap.put("nameContains","e");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(querymap)
                .when().get("/api/spartans/search");


        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
    }



}
