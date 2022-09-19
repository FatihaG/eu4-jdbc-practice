package apiShort;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanTestsWithParameter2 {

    /* Given accep tuype is Json
    and id is 18
    when sends get request to /api/spattans/{id}
    then response status code 200
    and response content-type json
    and "Allen" should be in payload
     */
   // String spartanBaseUrl="http://3.219.28.87:8000";
    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI="http://3.219.28.87:8000";
    }

    @Test
    public void pathTest1(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 18)
                .when().get("/api/spartans/{id}");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
        Assert.assertTrue(response.body().asString().contains("Allen"));



    }

    @Test
    public void queryParam1(){
        //import static yazip sonuna yildiz koy restassured in

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "j")
                .when().get("/api/spartans/search");

        //import static yaz yilidz koy assetiona
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));




    }

    @Test
    public void queryParams2(){
        //creating map for query param

        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("gender","Female");
        paramsMap.put("nameContains","J");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(paramsMap)
                .when().get("/api/spartans/search");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));


    }





}
