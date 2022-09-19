package apiShort;

import Day6_POJO.Spartan;
import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


public class Put9 {
    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI="http://3.219.28.87:8000";
    }

    @Test
    public void PUTrequest(){
        //different ways to send json body
        //-string
        //- using collection
        //- PoJo
        //using map

        Map<String,Object> putMap= new HashMap<>();
        putMap.put("name","FatihaPut");
        putMap.put("gender","Male");
       // putMap.put("phone",223344556L);
        //we gonna send request body with updated value,and content type headher
        Response response = given().contentType(ContentType.JSON)
                .and().pathParam("id", 84)
                .and().body(putMap)
                .when().patch("/api/spartans/{id}");

        Assert.assertEquals(response.statusCode(),204);


    }





}
