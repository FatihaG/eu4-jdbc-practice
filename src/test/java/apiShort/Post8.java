package apiShort;


import Day6_POJO.Spartan;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
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

public class Post8 {

    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI="http://3.219.28.87:8000";
    }


    @Test
    public void postWithString(){
        //sending json body as String
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"gender\": \"Female\",\n" +
                        "  \"name\": \"Fatiha\",\n" +
                        "  \"phone\": 1234567890\n" +
                        "\n" +
                        "}").when().post("/api/spartans/");
       //response.prettyPrint();

        //verification
        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");
        //verify success message
        assertEquals(response.path("success"),"A Spartan is Born!");
        //verify request body
        JsonPath json= response.jsonPath();
        assertEquals(json.getString("data.name"),"Fatiha");
        assertEquals(json.getString("data.gender"),"Female");
        assertEquals(json.getLong("data.phone"),1234567890);

    }

    @Test
    public void PostMethodWithMap(){
        //ceate map to be used as a body post request
        Map<String,Object> spartanfatiha= new HashMap<>();

        spartanfatiha.put("name","Fatihamap");
        spartanfatiha.put("gender","Female");
        spartanfatiha.put("phone",1234567890);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(spartanfatiha)
                .when().post("/api/spartans/");


        assertEquals(response.statusCode(),201);

        response.prettyPrint();


    }

    @Test
    public void postWithPoJO(){
        //create Spartan object and used as a body for post request
        Spartan spartan = new Spartan();
        spartan.setName("Tsrafil");
        spartan.setGender("Male");
        spartan.setPhone(234578920L);

//        Spartan ayse = new Spartan();
//        ayse.setName("Ayse");
//        ayse.setGender("Male");
//        ayse.setPhone(123456789L);

        System.out.println(spartan.toString());

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartan)
                .when().post("/api/spartans/");

       Assert.assertEquals(response.statusCode(),201);



    }




}
